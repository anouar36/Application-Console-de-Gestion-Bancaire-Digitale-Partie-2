package org.example.View;

import org.example.Controller.*;
import org.example.Modle.*;
import org.example.Validation.SalaryJob;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.math.BigDecimal;
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



    public static void main(String[] args) {
        //while (true) {

            //     System.out.println("===================================");
            //     System.out.println("      WELCOME TO BANK SYSTEM       ");
            //    System.out.println("===================================");

            //     System.out.println("pleas entre your email user:");
            //     String email = scanner.next();

            //    System.out.println("pleas entre your password user:");
            //    String password = scanner.next();

            //     String userAuth = authController.login(email, password);
            //    System.out.println("===================================");
            //     System.out.println("      " + userAuth + "      ");
            //     System.out.println("===================================");

            //    if (userAuth.toUpperCase().contains("ADMIN")) {
                //         adminMenu();
                //    } else {
                //        System.out.println("SORRY YOU ARE OUT AT SYSTEM!\n");
                //     }

       //}
        try {
            // تشغيل Scheduler مع البرنامج
            SalaryJob.startScheduler();
        } catch (Exception e) {
            e.printStackTrace();
        }
            adminMenu();
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
            System.out.println("11 . List Accounts");
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
                    //closeAccount();
                    navigate();
                    break;
                case 14:
                    //transactionHistory();
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
                    //reportsAndStatistics();
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

            String res = clientController.addClient(name, email, adress);
            if (res.contains("Was not added successfully")) {
                index++;
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
                clientController.addClient(name, email, null);
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
        int index =0;
        String  out = "";
        while (true) {
            if(index!=0){
                System.out.println("For out click B");
                out="B";
            }
            if(out.equals("B")){
                break;
            }

            System.out.print("Enter RIB: ");
            double linkedAccount = scanner.nextDouble();

            System.out.print("Enter Amount: ");
            BigDecimal amount = scanner.nextBigDecimal();

            System.out.print("Enter the loan duration (in months): ");
            int durationMonths = scanner.nextInt();


            index ++;

            String rs = creditController.creditRequest(linkedAccount, amount, durationMonths);
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
            System.out.println("Currency Type    : " + credit.getCurrencyType());
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
    public static int getcount(){
        Optional<ArrayList<Credit>> creditsOptional = Optional.ofNullable(creditController.getcreditsRequest());

        return creditsOptional.map(ArrayList::size).orElse(0);

    }

}







