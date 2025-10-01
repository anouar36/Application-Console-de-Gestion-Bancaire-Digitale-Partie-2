package org.example.Repository;

import org.example.dao.JDBC;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BankRepository {

    public boolean addFee(BigDecimal fee) {
        String updateSql = "UPDATE bank " +
                "SET amount = amount + ? " +
                "WHERE account_rib = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(updateSql)) {

            preparedStatement.setBigDecimal(1, fee);
            preparedStatement.setString(2, "RIB987654321");

            int rowsAffected = preparedStatement.executeUpdate();
            return rowsAffected > 0;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }



}
