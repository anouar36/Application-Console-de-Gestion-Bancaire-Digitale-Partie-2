package org.example.Repository;

import org.example.Modle.*;
import org.example.dao.JDBC;

import javax.swing.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AuthRepository implements UserRepository {

    private User user;




    public User login(String email, String password){
        String sql = "SELECT * FROM \"user\" WHERE \"email\" = ? AND \"password\" = ?;";


        try(Connection conection = JDBC.getConnection()){
            PreparedStatement stmt = conection.prepareStatement(sql);
            stmt.setString(1, email);
            stmt.setString(2,password);
            ResultSet rs  = stmt.executeQuery();

            if(rs.next()){
            int id = rs.getInt("id");
            String emAil = rs.getString("email");
            String roleStr = rs.getString("role");
            Role role = Role.valueOf(roleStr);
            String name = rs.getString("name");
            if(role== Role.ADMIN){
                this.user = new Admin(name,emAil,password,role);
            }else if(role == Role.TELLER){
                this.user = new Treller(name,emAil,password,role);
            }else if(role == Role.MANAGER){
                this.user = new Manager(name,emAil,password,role);
            }else{
                this.user = new Audioter(name,emAil,password,role);
            }
            }else{
               return null;
            }


        }catch (Exception e){
            System.out.println(e.getMessage());
        }

        return this.user;
    }

    public boolean regester(String name,String email,String password , Role role){
        String sql ="INSERT INTO \"user\"(\"name\" ,\"email\", \"password\", \"role\") VALUES (?,?,?,?::user_role)";
        ResultSet rs=null;

        try(Connection connection = JDBC.getConnection()) {
            PreparedStatement stmst = connection.prepareStatement(sql);

            stmst.setString(1,name);
            stmst.setString(2,email);
            stmst.setString(3,password);
            stmst.setString(4,role.name());

             int rowsInserted = stmst.executeUpdate();
             return rowsInserted > 0;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return false;
        }
    }
}
