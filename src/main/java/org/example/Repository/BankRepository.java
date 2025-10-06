package org.example.Repository;

import org.example.Modle.BankHistorique;
import org.example.dao.JDBC;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

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
    public boolean bankHistorique(int idClient, String idAccount, BigDecimal interestRateMonth, BigDecimal interestRate, BigDecimal my_payments) {
        String sql = "INSERT INTO bank_historique (id_client, linked_account, interest_rate_month, interest_rate, my_payments) VALUES (?,?,?,?,?)";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setInt(1, idClient);
            stmt.setString(2, idAccount);
            stmt.setBigDecimal(3, interestRateMonth);
            stmt.setBigDecimal(4, interestRate);
            stmt.setBigDecimal(5, my_payments);

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ArrayList<BankHistorique> getAllBankHistorique() {
        ArrayList<BankHistorique> historiques = new ArrayList<>();
        String sql = "SELECT * FROM bank_historique";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                BankHistorique bh = new BankHistorique();
                bh.setId(rs.getInt("id"));
                bh.setIdClient(rs.getInt("id_client"));
                bh.setLinkedAccount(rs.getString("linked_account"));
                bh.setInterestRateMonth(rs.getBigDecimal("interest_rate_month"));
                bh.setInterestRate(rs.getBigDecimal("interest_rate"));
                bh.setMyPayments(rs.getBigDecimal("my_payments"));
                bh.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());

                historiques.add(bh);
            }

        } catch (SQLException e) {
            System.out.println("Error fetching bank historique: " + e.getMessage());
        }

        return historiques;
    }






}
