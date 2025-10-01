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

        public Account creatAccount(BigDecimal balance , AccountType type , Client client, String currency, int id){
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

            String sql = "SELECT a.id as idClient, a.account_number, a.balance, a.type, c.name " +
                    "FROM account a " +
                    "JOIN client c ON a.client = c.id;";

            try (Connection connection = JDBC.getConnection();
                 PreparedStatement stmt = connection.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    int clientId = rs.getInt("idClient");
                    Client client = clientRepository.getClientById(clientId);
                    String accountNumber = rs.getString("account_number");
                    BigDecimal balance = rs.getBigDecimal("balance");
                    AccountType type =AccountType.valueOf(rs.getString("type"));
                    String clientName = rs.getString("name");

                    Account account = new Account(
                            accountNumber,
                            balance,
                            type,
                            client
                    );


                    String key = clientName + " (" + clientId + ")";

                    accountsMap.putIfAbsent(key, new ArrayList<>());
                    accountsMap.get(key).add(account);
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

            return accountsMap;
        }
        // ضيف هادي في AccountRepository.java
// بدل الميثود القديمة addSalary()

        public boolean addSalary() {
            String sql = "UPDATE account SET balance = balance + 5000";
            Connection connection = null;
            PreparedStatement stmt = null;

            try {
                connection = JDBC.getConnection();
                // إيقاف AutoCommit باش نتحكمو في الـ Transaction
                connection.setAutoCommit(false);

                stmt = connection.prepareStatement(sql);
                int rowsUpdated = stmt.executeUpdate();

                // عمل Commit بعد التحديث
                connection.commit();
                System.out.println("✅ تمت إضافة الراتب لـ " + rowsUpdated + " حساب");
                return true;

            } catch (SQLException e) {
                System.err.println("❌ خطأ في إضافة الراتب: " + e.getMessage());
                e.printStackTrace();

                // إلغاء التغييرات في حالة الخطأ
                if (connection != null) {
                    try {
                        connection.rollback();
                        System.out.println("⚠️ تم التراجع عن التغييرات");
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                }
                return false;

            } finally {
                // ⭐ مهم جداً: إغلاق الموارد باش ما يكونش Memory Leak
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
    }
