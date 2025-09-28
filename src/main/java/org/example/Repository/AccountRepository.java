package org.example.Repository;

import org.example.Modle.Account;
import org.example.Modle.AccountType;
import org.example.Modle.Client;
import org.example.Service.AccountService;
import org.example.dao.JDBC;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public class AccountRepository {

    public Account creatAccount(BigDecimal balance , AccountType type , Client client,String currency,int id){
        String sql= "INSERT INTO \"account\" (account_number,balance,type,client,currency) VALUES (?,?,?::account_type,?,?)";

        String rib =generateRib ();

        try(Connection connection = JDBC.getConnection()){
            PreparedStatement  stmt = connection.prepareStatement(sql);

            stmt.setString(1,rib);
            stmt.setBigDecimal(2,balance);
            stmt.setString(3,type.name());
            stmt.setInt(4,id);
            stmt.setString(5,currency);

            int rs = stmt.executeUpdate();

            if(rs == 0){
                throw new SQLException("Creating account failed, no rows affected.");
            }else{
                //String accountId, BigDecimal balanc, AccountType type, Client owner
                Account account = new Account(rib,balance,type, client);
                return  account;
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }

    }
    public static String generateRib() {
        Random rnd = new Random();
        String rib = "";
        for (int i = 0; i < 23; i++) {
            rib += rnd.nextInt(10);
        }
        return rib;
    }
}
