package org.example.Repository;

import org.example.Modle.CurrencyType;
import org.example.dao.JDBC;
import org.example.Modle.CurrencyType.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;

public class CreditRepository {


    public boolean creditRequest(double linked_account , BigDecimal amount, CurrencyType currencyType, BigDecimal interest_rate, BigDecimal interestRateMonth, int duration_months ){

        String sql = "INSERT INTO credit (amount, interest_rate, duration_months, linked_account, currency) VALUES (?,?,?,?,?::currencytype)";

        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setBigDecimal(1,amount );
            stmt.setBigDecimal(2, interest_rate);
            stmt.setInt(3, duration_months);
            stmt.setDouble(4, linked_account);
            stmt.setString(5, currencyType.name());

            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error inserting credit: " + e.getMessage());
            return false;
        }
    }
}
