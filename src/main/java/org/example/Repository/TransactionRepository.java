package org.example.Repository;

import org.example.Modle.TransactionType;
import org.example.dao.JDBC;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class TransactionRepository {

    public boolean deposit(BigDecimal amount, String accountNumber) {
        String updateSql = "UPDATE account SET balance = balance + ? WHERE account_number = ?";

        try (Connection connection = JDBC.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {
                updateStmt.setBigDecimal(1, amount);
                updateStmt.setString(2, accountNumber);

                int rowsUpdated = updateStmt.executeUpdate();

                if (rowsUpdated > 0) {
                    connection.commit();
                    return true;
                } else {
                    connection.rollback();
                    return false;
                }

            } catch (Exception e) {
                connection.rollback();
                System.out.println("Error deposit: " + e.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
            return false;
        }
    }

    public boolean withdraw(BigDecimal amount, String accountNumber) {
        String selectSql = "SELECT balance FROM account WHERE account_number = ?";
        String updateSql = "UPDATE account SET balance = balance - ? WHERE account_number = ?";

        try (Connection connection = JDBC.getConnection()) {
            connection.setAutoCommit(false);

            try (PreparedStatement selectStmt = connection.prepareStatement(selectSql);
                 PreparedStatement updateStmt = connection.prepareStatement(updateSql)) {

                selectStmt.setString(1, accountNumber);
                ResultSet rs = selectStmt.executeQuery();

                if (!rs.next()) {
                    System.out.println("Account not found!");
                    return false;
                }

                BigDecimal currentBalance = rs.getBigDecimal("balance");
                if (currentBalance.compareTo(amount) < 0) {
                    System.out.println("Insufficient balance!");
                    return false;
                }

                updateStmt.setBigDecimal(1, amount);
                updateStmt.setString(2, accountNumber);
                int rowsUpdated = updateStmt.executeUpdate();
                if (rowsUpdated == 0) {
                    connection.rollback();
                    return false;
                }

                connection.commit();
                return true;

            } catch (Exception e) {
                connection.rollback();
                System.out.println("Error during withdrawal: " + e.getMessage());
                return false;
            }

        } catch (Exception e) {
            System.out.println("Connection error: " + e.getMessage());
            return false;
        }
    }

    public boolean addToHistorique(BigDecimal amount, String clientRib, String receiverRib, String transactionType) {
        String insertSql = "INSERT INTO historique (amount, client_rib, receiver_rib, transaction_type) " +
                "VALUES (?, ?, ?, ?::transaction_type)";
        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(insertSql);
            stmt.setBigDecimal(1, amount);
            stmt.setString(2, clientRib);
            stmt.setString(3, receiverRib); // null if not applicable
            stmt.setString(4, transactionType);
            stmt.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println("Error inserting into historique: " + e.getMessage());
            return false;
        }
    }

    public boolean transferHistorique(BigDecimal amount, String fromAccount, String toAccount, TransactionType type) {
        String insertHistoriqueSql = "INSERT INTO historique (amount, client_rib, receiver_rib, transaction_type) " +
                "VALUES (?, ?, ?, ?::transaction_type)";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement insertStmt = connection.prepareStatement(insertHistoriqueSql)) {

            connection.setAutoCommit(false);

            insertStmt.setBigDecimal(1, amount);
            insertStmt.setString(2, fromAccount);
            insertStmt.setString(3, toAccount);
            insertStmt.setString(4, type.name());

            insertStmt.executeUpdate();
            connection.commit();

            return true;

        } catch (Exception e) {
            System.out.println("Error during transfer: " + e.getMessage());
            return false;
        }
    }


    //public boolean transferExternal(BigDecimal balance , String SenderRIB,String ReceiverRIB ){}


}
