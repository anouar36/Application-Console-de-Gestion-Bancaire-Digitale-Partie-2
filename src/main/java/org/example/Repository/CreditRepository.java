package org.example.Repository;

import org.example.Modle.Credit;
import org.example.Modle.CreditStatus;
import org.example.Modle.CurrencyType;
import org.example.dao.JDBC;
import org.example.Modle.CurrencyType.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;

public class CreditRepository {


    public boolean creditRequest(String linked_account , BigDecimal amount, BigDecimal interest_rate, BigDecimal interestRateMonth, int duration_months,Date dateStart ){

        String sql = "INSERT INTO credit (amount, interest_rate, duration_months, linked_account,interestRateMonth, date_starte ) VALUES (?,?,?,?,?,?)";

        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setBigDecimal(1,amount );
            stmt.setBigDecimal(2, interest_rate);
            stmt.setInt(3, duration_months);
            stmt.setString(4, linked_account);

            stmt.setBigDecimal(5, interestRateMonth);
            stmt.setTimestamp(6, new java.sql.Timestamp(dateStart.getTime()));
            stmt.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println("Error inserting credit: " + e.getMessage());
            return false;
        }
    }
    public ArrayList<Credit> getcreditsRequest(){
        String sql = "SELECT * FROM credit WHERE credit.status = \'INACTIVE\' ";

        try(Connection connection = JDBC.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            ArrayList<Credit> credits = new ArrayList<>();
            while (rs.next()){
                Credit credit = new Credit(
                        rs.getInt("id"),
                        rs.getBigDecimal("amount"),
                        rs.getBigDecimal("interest_rate"),
                        rs.getInt("duration_months"),
                        CreditStatus.valueOf(rs.getString("status")) ,
                        rs.getString("linked_account"),
                        rs.getBigDecimal("interestRateMonth"),
                        rs.getBigDecimal("my_payments")
                );
                credits.add(credit);
            }
            return credits;
        }catch (Exception e){
            System.out.println(e.getMessage());
            return  null;
        }
    }
    public boolean validationCredit(String rib){
                String sql = "UPDATE  credit set status = 'ACTIVE' WHERE linked_account = ? ";
                try(Connection connection = JDBC.getConnection()){
                    PreparedStatement stmt = connection.prepareStatement(sql);
                    stmt.setString(1,rib);
                    int rs =  stmt.executeUpdate();
                    if(rs>0){
                        return  true;
                    }
                    return  false;


                }catch (Exception e){
                    System.out.println(e.getMessage());
                    return  false;

                }
    }
    public Credit getCreditByIdAccount(String idAccount) {
        String sql = "SELECT * FROM credit WHERE linked_account = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, idAccount);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Credit credit = new Credit(
                        rs.getInt("id"),
                        rs.getBigDecimal("amount"),
                        rs.getBigDecimal("interest_rate"),
                        rs.getInt("duration_months"),
                        CreditStatus.valueOf(rs.getString("Status")),
                        rs.getString("linked_account"),
                        rs.getBigDecimal("interestRateMonth"),
                        rs.getBigDecimal("my_payments")
                );
                return credit;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
    public boolean changeStatus(int creditId, CreditStatus newStatus) {
        String sql = "UPDATE credit SET Status = ?::credit_status WHERE id = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, newStatus.name()); // "APPROVED" مثلا
            stmt.setInt(2, creditId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }


    public boolean deductions(BigDecimal myPayment, String accountId) {
        String sql = "UPDATE credit SET my_paymentS = ? WHERE linked_account = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setBigDecimal(1, myPayment);
            stmt.setString(2, accountId);

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }


}
