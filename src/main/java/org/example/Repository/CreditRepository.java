package org.example.Repository;

import org.example.Modle.Credit;
import org.example.Modle.CreditStatus;
import org.example.Modle.CurrencyType;
import org.example.dao.JDBC;
import org.example.Modle.CurrencyType.*;

import javax.swing.*;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;

public class CreditRepository {


    public boolean creditRequest(double linked_account , BigDecimal amount, BigDecimal interest_rate, BigDecimal interestRateMonth, int duration_months ){

        String sql = "INSERT INTO credit (amount, interest_rate, duration_months, linked_account,interestRateMonth ) VALUES (?,?,?,?,?)";

        try (Connection connection = JDBC.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setBigDecimal(1,amount );
            stmt.setBigDecimal(2, interest_rate);
            stmt.setInt(3, duration_months);
            stmt.setDouble(4, linked_account);

            stmt.setBigDecimal(5, interestRateMonth);

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
                        CurrencyType.valueOf(rs.getString("currency")),
                        rs.getBigDecimal("interestRateMonth")
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
}
