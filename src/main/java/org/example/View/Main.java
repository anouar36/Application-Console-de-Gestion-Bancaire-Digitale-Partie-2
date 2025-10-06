package org.example.View;

import org.example.Controller.*;
import org.example.Modle.*;
import org.example.Service.SalaryJob;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static User user;
    private static AuthController authController = new AuthController();
    private static ClientController clientController = new ClientController();
    private static AccountController accountController = new AccountController();
    private static CreditController creditController = new CreditController();
    private static TransactionController transactionController = new TransactionController();
    private static BankController bankController = new BankController();
    private static  RapportController rapportController = new RapportController();



    public static void main(String[] args) throws Exception {
        SalaryJob.startScheduler();

        while (true) {

            System.out.println("===================================");
            System.out.println("      WELCOME TO BANK SYSTEM       ");
            System.out.println("===================================");

            System.out.println("pleas entre your email user:");
            String email = scanner.next();

            System.out.println("pleas entre your password user:");
            String password = scanner.next();

            User userAuth = authController.login(email, password);
            user = userAuth;


            if (userAuth == null) {
                System.out.println("SORRY YOU ARE OUT AT SYSTEM!\n");


            }else{

                System.out.println("===================================");
                System.out.println("      " + "LOGIN SUCCESSFUL! ✅\n WELCOME " + userAuth.getRole() + " " + userAuth.getName().toUpperCase() + " TO THE SYSTEM" + "      ");
                System.out.println("===================================");

                switch (userAuth.getRole()) {
                    case Role.ADMIN  -> adminMenu();
                    case Role.AUDITOR-> auditorMenu();
                    case Role.MANAGER-> managerMenu();
                    case Role.TELLER ->  tellerMenu();

                }
            }

        }





    }

    //admin menu
    public static void adminMenu() {
        while (true) {
            System.out.println("========= ADMIN MENU =========");
            System.out.println("1  . Show Profile");
            System.out.println("2  . Add Employee");
            System.out.println("3  . Create Client");
            System.out.println("4  . Create Account");
            System.out.println("5  . Credit Request");
            System.out.println("6  . Validation Credit : (" + getcount() + ")");
            System.out.println("7  . Deposit");
            System.out.println("8  . Withdraw");
            System.out.println("9  . Transfer Internal");
            System.out.println("10 . Transfer External");
            System.out.println(" 11 . List Accounts");
            System.out.println("12 . Update Client Profile");
            System.out.println("13 . Close Account");
            System.out.println("14 . Transaction History");
            System.out.println("15 . Credit Follow-up");
            System.out.println("16 . Repayment Credit");
            System.out.println("17 . Reports & Statistics");
            System.out.println("18 . Fees Management");
            System.out.println("19 . Logout");
            System.out.println("20 . Exit");
            System.out.println("Please enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    showProfile();
                    navigate();
                    break;
                case 2:
                    addEmployee();
                    navigate();
                    break;
                case 3:
                    creatClient();
                    navigate();
                    break;
                case 4:
                    creatAccont();
                    navigate();
                    break;
                case 5:
                    creditRequest();
                    navigate();
                    break;
                case 6:
                    validationCredit();
                    navigate();
                    break;
                case 7:
                    deposit();
                    navigate();
                    break;
                case 8:
                    withdraw();
                    navigate();
                    break;
                case 9:
                    transferInternal();
                    navigate();
                    break;
                case 10:
                    transferExternal();
                    navigate();
                    break;
                case 11:
                    listAccounts();
                    navigate();
                    break;
                case 12:
                    updateClientProfile();
                    navigate();
                    break;
                case 13:
                    closeAccount();
                    navigate();
                    break;
                case 14:
                    transactionHistory();
                    navigate();
                    break;
                case 15:
                    //creditFollowUp();
                    navigate();
                    break;
                case 16:
                    //repaymentCredit();
                    navigate();
                    break;
                case 17:
                    reportsAndStatistics();
                    navigate();
                    break;
                case 18:
                    //feesManagement();
                    navigate();
                    break;
                case 19:
                    //logout();
                    return;
                case 20:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again!");
            }
        }
    }
    //teller  menu
    public static void tellerMenu() {
        while (true) {
            System.out.println("========= TELLER MENU =========");
            System.out.println("1  . Show Profile");
            System.out.println("2  . Create Client");
            System.out.println("3  . Create Account");
            System.out.println("4  . Deposit");
            System.out.println("5  . Withdraw");
            System.out.println("6  . Transfer Internal");
            System.out.println("7  . Credit Request");
            System.out.println("8  . Transaction History");
            System.out.println("9  . Logout");
            System.out.println("10 . Exit");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1: showProfile(); break;
                case 2: creatClient(); break;
                case 3: creatAccont(); break;
                case 4: deposit(); break;
                case 5: withdraw(); break;
                case 6: transferInternal(); break;
                case 7: creditRequest(); break;
                case 8: transactionHistory(); break;
                case 9: return; // logout
                case 10: System.exit(0); break;
                default: System.out.println("Invalid choice!");
            }
            navigate();
        }
    }
    //manager menu
    public static void managerMenu() {
        while (true) {
            System.out.println("========= MANAGER MENU =========");
            System.out.println("1  . Show Profile");
            System.out.println("2  . Create Client");
            System.out.println("3  . Create Account");
            System.out.println("4  . Deposit");
            System.out.println("5  . Withdraw");
            System.out.println("6  . Transfer Internal");
            System.out.println("7  . Transfer External");
            System.out.println("8  . Credit Request");
            System.out.println("9  . Validation Credit : (" + getcount() + ")");
            System.out.println("10 . Close Account");
            System.out.println("11 . Transaction History");
            System.out.println("12 . Reports & Statistics");
            System.out.println("13 . Logout");
            System.out.println("14 . Exit");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1: showProfile(); break;
                case 2: creatClient(); break;
                case 3: creatAccont(); break;
                case 4: deposit(); break;
                case 5: withdraw(); break;
                case 6: transferInternal(); break;
                case 7: transferExternal(); break;
                case 8: creditRequest(); break;
                case 9: validationCredit(); break;
                case 10: closeAccount(); break;
                case 11: transactionHistory(); break;
                case 12: /* reportsAndStatistics(); */ break;
                case 13: return; // logout
                case 14: System.exit(0); break;
                default: System.out.println("Invalid choice!");
            }
            navigate();
        }
    }
    //auditor menu
    public static void auditorMenu() {
        while (true) {
            System.out.println("========= AUDITOR MENU =========");
            System.out.println("1  . Show Profile");
            System.out.println("2  . List Accounts");
            System.out.println("3  . Transaction History");
            System.out.println("4  . Reports & Statistics");
            System.out.println("5  . Logout");
            System.out.println("6  . Exit");
            System.out.print("Please enter your choice: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1: showProfile(); break;
                case 2: listAccounts(); break;
                case 3: transactionHistory(); break;
                case 4: /* reportsAndStatistics(); */ break;
                case 5: return; // logout
                case 6: System.exit(0); break;
                default: System.out.println("Invalid choice!");
            }
            navigate();
        }


}



    public static void navigate() {
        System.out.println("If you want to continue, click B.");
        scanner.next();
    }
    public static void showProfile() {
        System.out.println("name : " + user.getName());
        System.out.println("name : " + user.getEmail());
        System.out.println("name : " + user.getPassword());
        System.out.println("name : " + user.getRole());

    }
    public static void addEmployee() {

        boolean index = true;
        while (index) {
            System.out.println("entre Nome : ");
            String name = scanner.next();
            System.out.println("entre email :");
            String email = scanner.next();
            System.out.println("entre password : ");
            String password = scanner.next();
            System.out.println("entre  role: \n1.AUDITOR\n2.TELLER\n3.MANAGER\n4.ADMIN");
            int roleInt = scanner.nextInt();

            String rs = authController.regester(name, email, password, roleInt);
            System.out.println(rs);
            if (rs.contains("added successfully!")) {
                index = false;
            }
        }
    }
    public static void creatClient() {
        int index = 0;
        String clike = "";
        while (true) {

            if (index >= 1) {
                System.out.println("for out click C ");
                clike = scanner.next();
            }
            if (clike.equals("c")) {
                break;
            }
            System.out.println("entre name: ");
            String name = scanner.next();
            System.out.println("entre email: ");
            String email = scanner.next();
            System.out.println("entre adress: ");
            String adress = scanner.next();



            Client res = clientController.addClient(name, email, adress);

            if (res != null) {
                System.out.println( name + " has been added successfully.");


            }else{
                index++;
                System.out.println("Client was not added successfully.");


            }
        }

    }
    public static void creatAccont() {
        int index = 0;
        String out = "";
        while (true) {
            if (index > 0) {
                System.out.println("if you want out click B");
                out = scanner.next();

            }

            System.out.println("Enter name of client:");
            String name = scanner.next();
            System.out.println("Enter email of client:");
            String email = scanner.next();
            Client client = clientController.getClient(name, email);
            if (client == null) {
                System.out.println("Enter address of client:");
                String address = scanner.next();
                client = clientController.addClient(name, email, address);

            }
            System.out.println("Entre balance of acount:");
            BigDecimal balance = scanner.nextBigDecimal();
            System.out.println("Entre type of acount : 1.Courant, 2.Épargne ,3.Crédit");
            int typeInt = scanner.nextInt();

            String result = accountController.creatAccount(balance, typeInt, client, client.getId());
            System.out.println(result);

            if (result.contains("Account created successfully") || out.equals("B")) {
                break;
            }
            index++;
        }
    }
    public static void creditRequest() {
        int index = 0;
        String out = "";
        while (true) {
            if (index != 0) {
                System.out.println("For out click B");
                out = scanner.next();
            }
            if (out.equals("B")) {
                break;
            }
            System.out.println("When you take a credit, 40% of your salary will be deducted.");
            System.out.println("================================================================");

            System.out.println("Enter RIB: ");
            String linkedAccount = scanner.next();

            System.out.println("Enter Salary: ");
            BigDecimal salary = scanner.nextBigDecimal();

            System.out.println("Enter Amount Credit: ");
            BigDecimal amount = scanner.nextBigDecimal();

            scanner.nextLine();
            System.out.println("Enter Date Start (yyyy-MM-dd): ");
            String dateInput = scanner.nextLine();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(dateInput, formatter);
            Date dateStart = Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant());

            index++;

            String rs = creditController.creditRequest(linkedAccount, amount, salary, dateStart);
            System.out.println(rs);
        }
    }

    public static void validationCredit(){
        ArrayList<Credit> credits =creditController.getcreditsRequest();
        credits.forEach(credit -> {
            System.out.println("Credit ID        : " + credit.getCreditId());
            System.out.println("Amount           : " + credit.getAmount());
            System.out.println("Linked Account   : " + credit.getLinkedAccount());
            System.out.println("Duration (Months): " + credit.getDurationMonths());
            System.out.println("Interest Rate    : " + credit.getInterestRate());
            System.out.println("Status           : " + credit.getStatus());
            System.out.println("--------------------------------------------------");
        });
        System.out.println("Enter RIB account to validate credit: ");
        String rib = scanner.next();
        String res = creditController.validationCredit(rib);
        System.out.println(res);

    }
    public static void deposit() {
        BigDecimal amount = null;

        while (amount == null) {
            try {
                System.out.print("Enter amount: ");
                amount = scanner.nextBigDecimal();
                scanner.nextLine();


                System.out.print("Enter RIB: ");
                String rib = scanner.nextLine();

                String rs = transactionController.deposit(amount,rib);
                System.out.println(rs);



            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
                scanner.nextLine();
                amount = null;
            }
        }
    }
    public static void withdraw() {
        BigDecimal amount = null;

        while (amount == null) {
            try {
                System.out.print("Enter amount to withdraw: ");
                amount = scanner.nextBigDecimal();
                scanner.nextLine();

                System.out.print("Enter RIB: ");
                String rib = scanner.nextLine();

                String success = transactionController.withdraw(amount, rib);
                System.out.println(success);

            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
                scanner.nextLine();
                amount = null;
            }
        }
    }
    public static void transferExternal() {
        BigDecimal amount = null;

        while (amount == null) {
            try {
                System.out.print("Enter amount to transfer: ");
                amount = scanner.nextBigDecimal();
                scanner.nextLine();

                System.out.print("Enter source RIB: ");
                String fromRib = scanner.nextLine();

                System.out.print("Enter destination RIB: ");
                String toRib = scanner.nextLine();

                String success = transactionController.transferExternal(amount, fromRib, toRib);
                System.out.println(success);

            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
                scanner.nextLine();
                amount = null;
            }
        }
    }
    public static void transferInternal() {
        BigDecimal amount = null;

        while (amount == null) {
            try {
                System.out.print("Enter amount to transfer: ");
                amount = scanner.nextBigDecimal();
                scanner.nextLine();

                System.out.print("Enter your source RIB: ");
                String fromRib = scanner.nextLine();

                System.out.print("Enter destination RIB (External Bank): ");
                String toRib = scanner.nextLine();


                String success = transactionController.transferInternal(amount, fromRib, toRib);
                System.out.println(success);

            } catch (Exception e) {
                System.out.println("Invalid input, please try again.");
                scanner.nextLine();
                amount = null;
            }
        }
    }
    public static void listAccounts() {
        HashMap<String, ArrayList<Account>> listAccounts = accountController.listAccounts();

        // Loop through each client
        for (String clientKey : listAccounts.keySet()) {
            System.out.println("Client: " + clientKey);

            // Loop through each account of this client
            ArrayList<Account> accounts = listAccounts.get(clientKey);
            for (Account account : accounts) {
                System.out.println("  Account Number: " + account.getAccountId());
                System.out.println("  Balance: " + account.getBalance());
                System.out.println("  Type: " + account.getType());
                System.out.println("-------------------------");
            }
        }
    }
    public static void updateClientProfile() {
        ArrayList<Client> clients = clientController.getAllClient();

        if (clients.isEmpty()) {
            System.out.println("⚠️ No clients found.");
            return;
        }

        while (true) {
            System.out.println("Entre name of Client !");
            String name = scanner.next();
            Client client = clients.stream().filter(n -> n.getName().equals(name)).findFirst().orElse(null);

            System.out.println("====================== Client List ======================");
            System.out.println("ID\tName\t\t\tAddress\t\tEmail");
            System.out.println("---------------------------------------------------------");


            System.out.println(
                    client.getId() + "\t" +
                            client.getName() + "\t\t" +
                            client.getAddress() + "\t\t" +
                            client.getEmail()
            );
            while (true) {
                System.out.println("=========================================================");
                System.out.println("1.For update name ");
                System.out.println("2.For update email");
                System.out.println("3.For update address");
                int updateInt = scanner.nextInt();
                switch (updateInt) {
                    case 1:
                        System.out.println("Entre new name :");
                        break;
                    case 2:
                        System.out.println("Entre new email :");
                        break;
                    case 3:
                        System.out.println("Entre new address :");
                        break;
                }
                String value = scanner.next();
                String res = clientController.updateClient(client.getId(), updateInt, value);
                System.out.println(res);
                System.out.println("For out clik B");
                String out = scanner.next();
                if (out.equals("B")) {
                    break;
                }
            }
            System.out.println("For out clik B");
            String out = scanner.next();
            if(out.equals("B")){
                break;
            }
        }

    }
    public static void closeAccount(){
        listAccounts();
        System.out.println("Entre RIB of acount to close !");
        String rib = scanner.next();
        String result = accountController.closeAccount(rib);
        System.out.println(result);
    }
    public static void transactionHistory() {
        ArrayList<BankHistorique> bankHistoriques = bankController.getAllBankHistorique();

        if (bankHistoriques.isEmpty()) {
            System.out.println("No bank history records found.");
            return;
        }

        for (BankHistorique bh : bankHistoriques) {
            System.out.println("ID: " + bh.getId());
            System.out.println("Client ID: " + bh.getIdClient());
            System.out.println("Account: " + bh.getLinkedAccount());
            System.out.println("Interest Month: " + bh.getInterestRateMonth());
            System.out.println("Interest Rate: " + bh.getInterestRate());
            System.out.println("My Payments: " + bh.getMyPayments());
            System.out.println("Created At: " + bh.getCreatedAt());
            System.out.println("-------------------------------");
        }
    }
    public static void reportsAndStatistics() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("========= CREATE CREDIT REPORT =========");

        System.out.print("Enter report title: ");
        String reportName = scanner.nextLine();

        System.out.print("Enter your name: ");
        String authorName = scanner.nextLine();

        System.out.print("Enter credit status (e.g. ACTIVE, LATE, CLOSED): ");
        String creditStatus = scanner.nextLine();

        System.out.print("Enter short description: ");
        String description = scanner.nextLine();

        LocalDate reportDate = LocalDate.now();

        // ✅ Create static text template for the report
        String reportText = "========= CREDIT REPORT =========\n" +
                "Report Title : " + reportName + "\n" +
                "Author       : " + authorName + "\n" +
                "Date         : " + reportDate + "\n" +
                "Status       : " + creditStatus + "\n" +
                "--------------------------------------\n" +
                "Summary : \n" + description + "\n\n" +
                "This report summarizes the current state of credit operations.\n" +
                "It provides details about loan performance, repayment status,\n" +
                "and client reliability metrics based on the entered credit status.\n" +
                "--------------------------------------\n" +
                "Generated automatically by the Digital Banking System.\n" +
                "========= END OF REPORT =========";

        // ✅ Create report object
        Rapport report = new Rapport(reportName, authorName, reportDate, creditStatus, description, reportText);

        // ✅ Save to database
        rapportController.saveReport(report);

        // ✅ Print confirmation
        System.out.println("\nReport successfully created and saved!");
        System.out.println("=======================================");
        System.out.println(reportText);
        System.out.println("=======================================");
    }

    public static int getcount(){
        Optional<ArrayList<Credit>> creditsOptional = Optional.ofNullable(creditController.getcreditsRequest());

        return creditsOptional.map(ArrayList::size).orElse(0);

    }

}







