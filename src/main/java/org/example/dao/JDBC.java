package org.example.dao;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.stream.Collectors;

public class JDBC {
    private static Connection connection = null ;

    public static Connection getConnection() {
        try{
            Dotenv dotenv =Dotenv.load();
            String url= dotenv.get("DB_URL") ;
            String user=dotenv.get("DB_USER") ;
            String password =dotenv.get("DB_PASSWORD");
            connection = DriverManager.getConnection(url,user,password);
            if (connection != null){
                Class.forName("org.postgresql.Driver");
            }else{
                System.out.println("non connection");
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return connection;
    }
}
