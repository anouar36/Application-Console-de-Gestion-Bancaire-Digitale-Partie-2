    package org.example.Repository;

    import org.example.Modle.Account;
    import org.example.Modle.AccountType;
    import org.example.Modle.Client;
    import org.example.Modle.CurrencyType;
    import org.example.Service.AccountService;
    import org.example.dao.JDBC;

    import java.math.BigDecimal;
    import java.sql.Connection;
    import java.sql.PreparedStatement;
    import java.sql.ResultSet;
    import java.sql.SQLException;
    import java.util.ArrayList;
    import java.util.HashMap;
    import java.util.Random;

    public class AccountRepository {

        private ClientRepository clientRepository;

        public AccountRepository() {
            this.clientRepository = new ClientRepository();
        }

        public Account creatAccount(BigDecimal balance , AccountType type , Client client, int id){
            String sql= "INSERT INTO \"account\" (account_number,balance,type,client) VALUES (?,?,?::account_type,?)";

            String rib =generateRib ();

            try(Connection connection = JDBC.getConnection()){
                PreparedStatement  stmt = connection.prepareStatement(sql);

                stmt.setString(1,rib);
                stmt.setBigDecimal(2,balance);
                stmt.setString(3,type.name());
                stmt.setInt(4,id);

                int rs = stmt.executeUpdate();

                if(rs == 0){
                    throw new SQLException("Creating account failed, no rows affected.");
                }else{
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
        public boolean checkClientAcounts(String fromAccount, String toAccount) {

            String sql = "SELECT COUNT(*) FROM account WHERE account_number = ? OR account_number = ?";

            try (Connection connection = JDBC.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, fromAccount);
                stmt.setString(2, toAccount);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        int count = rs.getInt(1);

                        return count == 2;
                    }
                }

            } catch (Exception e) {
                System.out.println("Error checking accounts: " + e.getMessage());
            }

            return false;
        }
        public HashMap<String, ArrayList<Account>> listAccounts() {
            HashMap<String, ArrayList<Account>> accountsMap = new HashMap<>();
            String sql = "SELECT a.id as idClient, a.account_number, a.balance, a.type, c.name, c.email, c.address " +
                    "FROM account a JOIN client c ON a.client = c.id;";

            try (Connection connection = JDBC.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int clientId = rs.getInt("idClient");
                    String accountNumber = rs.getString("account_number");
                    BigDecimal balance = rs.getBigDecimal("balance");
                    String typeStr = rs.getString("type");
                    AccountType type = AccountType.valueOf(typeStr);
                    String clientName = rs.getString("name");
                    String clientEmail = rs.getString("email");
                    String clientAddress = rs.getString("address");


                    Client client = new Client();
                    client.setId(clientId);
                    client.setName(clientName);
                    client.setEmail(clientEmail);
                    client.setAddress(clientAddress);

                    Account account = new Account(accountNumber, balance, type, client);
                    String key = clientName + " (" + clientId + ")";
                    accountsMap.putIfAbsent(key, new ArrayList<>());
                    accountsMap.get(key).add(account);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return accountsMap;
        }

        public boolean addSalary() {
            String sql = "UPDATE account SET balance = balance + 5000";
            Connection connection = null;
            PreparedStatement stmt = null;

            try {
                connection = JDBC.getConnection();
                connection.setAutoCommit(false);

                stmt = connection.prepareStatement(sql);
                int rowsUpdated = stmt.executeUpdate();

                connection.commit();
                return true;

            } catch (SQLException e) {
                e.printStackTrace();

                if (connection != null) {
                    try {
                        connection.rollback();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                return false;

            } finally {
                if (stmt != null) {
                    try {
                        stmt.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }

                if (connection != null) {
                    try {
                        connection.setAutoCommit(true);
                        connection.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

        public boolean accountAmount(String accountNumber) {
            String sql = "SELECT balance FROM account WHERE account_number = ?";
            try (Connection connection = JDBC.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, accountNumber);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    BigDecimal balance = rs.getBigDecimal("balance");

                    if (balance.compareTo(BigDecimal.ZERO) > 0) {
                        return true;
                    } else {
                        return false;
                    }
                } else {
                    return false;
                }

            } catch (SQLException e) {
                return false;
            }
        }


        public boolean accountCredit(String accountNumber) {
            String sql = "SELECT amount, my_payments FROM credit WHERE linked_account = ?";

            try (Connection connection = JDBC.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, accountNumber);
                ResultSet rs = stmt.executeQuery();

                if (rs.next()) {
                    BigDecimal amount = rs.getBigDecimal("amount");
                    BigDecimal myPayments = rs.getBigDecimal("my_payments");

                    if (amount.compareTo(myPayments) > 0) {
                        return false;
                    } else {
                        return true;
                    }
                } else {
                    // No credit found for this account
                    return true;
                }

            } catch (SQLException e) {
                return false;
            }
        }


        public boolean closeAccount(String accountNumber) {
            String sql = "DELETE FROM account WHERE account_number = ?";
            try (Connection connection = JDBC.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql)) {

                stmt.setString(1, accountNumber);
                int rows = stmt.executeUpdate();

                if (rows > 0) {
                    System.out.println("[INFO] Account " + accountNumber + " closed successfully.");
                    return true;
                } else {
                    System.out.println("[WARN] No account found to close with number: " + accountNumber);
                    return false;
                }

            } catch (SQLException e) {
                System.err.println("[ERROR] closeAccount(): " + e.getMessage());
                return false;
            }
        }


    }
