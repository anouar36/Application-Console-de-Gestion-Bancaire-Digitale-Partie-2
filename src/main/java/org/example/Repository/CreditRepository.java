package org.example.Repository;

import org.example.dao.JDBC;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.Date;

public class CreditRepository {


    public boolean creditRequest(String rib , String name , String descreption, Date dateStarted, Date dateEnd){
        String sql = "INSERT INTO credits (accountId, name,descreption,dateStarted,dateEnd) VALUES (?,?,?,?,?)";

        try(Connection connection = JDBC.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1,rib);
            stmt.setString(2,name);
            stmt.setString(3,descreption);
            stmt.setDate(4,new java.sql.Date(dateStarted.getTime()));
            stmt.setDate(5,new java.sql.Date(dateEnd.getTime()));

            stmt.executeUpdate();
            return true;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;

        }
    }
}
