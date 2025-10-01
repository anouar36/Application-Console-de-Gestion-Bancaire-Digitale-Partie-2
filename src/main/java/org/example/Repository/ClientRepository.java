package org.example.Repository;

import org.example.Modle.Account;
import org.example.Modle.AccountType;
import org.example.Modle.Client;
import org.example.dao.JDBC;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClientRepository {



    public Integer addClient(String name, String email, String adress){

        String sql = "INSERT INTO \"client\" (\"name\",\"email\",\"address\") VALUES (?,?,?) ";

        try(Connection connection= JDBC.getConnection()) {
            PreparedStatement stmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            stmt.setString(1,name);
            stmt.setString(2,email);
            stmt.setString(3,adress);

            stmt.executeUpdate();

            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
            return null;

        }catch (Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Client getClient(String name , String email){
        String sql = "SELECT c.id AS client_id, c.name AS client_name, c.address, c.email, " +
                "a.account_number, a.balance, a.type " +
                "FROM client c " +
                "LEFT JOIN account a ON a.client = c.id " +
                "WHERE c.name = ? AND c.email = ?";

        try(Connection connection= JDBC.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);

            stmt.setString(1, name);
            stmt.setString(2, email);

            ResultSet rs = stmt.executeQuery();
            Client client  = null;
            List<Account> accounts = new ArrayList<>();

            while(rs.next()){
                if(client == null){
                    client = new Client();
                    client.setId(rs.getInt("client_id"));
                    client.setName(rs.getString("client_name"));
                    client.setAddress(rs.getString("address"));
                    client.setEmail(rs.getString("email"));
                }

                String accountId = rs.getString("account_number");
                if(accountId != null){

                    BigDecimal balance = rs.getBigDecimal("balance");
                    String typeStr = rs.getString("type");
                    AccountType type = AccountType.fromString(typeStr);

                    Account account = new Account(accountId, balance, type, client);

                    accounts.add(account);
                }
            }

            if(client != null){
                client.setAccounts(accounts);
            }

            return client;

        }catch(Exception e){
            System.out.println(e.getMessage());
            return null;
        }
    }
    public Client getClientById(int id){
        String sql = "SELECT * FROM client WHERE id = ?";

        try(Connection connection = JDBC.getConnection()){
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();
            if(rs.next()){
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("address"));
                client.setEmail(rs.getString("email"));
                return client;
            }

        }catch(Exception e){
            System.out.println(e.getMessage());
        }

        return null;
    }
    public ArrayList<Client> getAllClient() {
        ArrayList<Client> clients = new ArrayList<>();
        String sql = "SELECT * FROM client";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Client client = new Client();
                client.setId(rs.getInt("id"));
                client.setName(rs.getString("name"));
                client.setAddress(rs.getString("address"));
                client.setEmail(rs.getString("email"));
                clients.add(client);
            }

        } catch (Exception e) {
            System.out.println("Error fetching clients: " + e.getMessage());
        }

        return clients;
    }
    public boolean updateClient(int id, String column, String value) {
        String sql = "UPDATE client SET " + column + " = ? WHERE id = ?";

        try (Connection connection = JDBC.getConnection();
             PreparedStatement stmt = connection.prepareStatement(sql)) {

            stmt.setString(1, value); // set new value
            stmt.setInt(2, id);       // set client ID

            int rowsUpdated = stmt.executeUpdate();
            return rowsUpdated > 0; // true if at least one row was updated

        } catch (Exception e) {
            System.out.println("Error updating client: " + e.getMessage());
            return false;
        }
    }




}
