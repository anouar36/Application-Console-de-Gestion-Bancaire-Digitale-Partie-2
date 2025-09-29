package org.example.View;

import org.example.Controller.AccountController;
import org.example.Controller.AuthController;
import org.example.Controller.ClientController;
import org.example.Controller.CreditController;
import org.example.Modle.*;

import java.math.BigDecimal;
import java.util.Scanner;

public class Main {
    private static Scanner scanner = new Scanner(System.in);
    private static User user;
    private static AuthController authController = new AuthController();
    private static ClientController clientController = new ClientController();
    private static AccountController accountController = new AccountController();
    private static CreditController creditController = new CreditController();


    public static void main(String[] args) {
        while (true) {

            System.out.println("===================================");
            System.out.println("      WELCOME TO BANK SYSTEM       ");
            System.out.println("===================================");

            System.out.println("pleas entre your email user:");
            String email = scanner.next();

            System.out.println("pleas entre your password user:");
            String password = scanner.next();

            String userAuth = authController.login(email, password);
            System.out.println("===================================");
            System.out.println("      " + userAuth + "      ");
            System.out.println("===================================");

            if (userAuth.toUpperCase().contains("ADMIN")) {
                adminMenu();
            } else {
                System.out.println("SORRY YOU ARE OUT AT SYSTEM!\n");
            }

        }
    }

    //admin menu
    public static void adminMenu() {

        while (true) {
            System.out.println("1. show profile");
            System.out.println("2. Add Employee");
            System.out.println("3. Creat Client");
            System.out.println("4. Creat Account");
            System.out.println("5. credit Request");

            System.out.println("Please entre your choice: ");
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
                    break;
                case 7:
                    break;
                case 8:
                    break;
                case 9:
                    break;
                case 10:
                    break;
                case 11:
                    break;
                default:
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


            System.out.print("Enter Currency: 1.DOLLAR, 2.DIRHAM, 3.EURO : ");
            int currencyInt = scanner.nextInt();
            index ++;

            String rs = creditController.creditRequest(linkedAccount, amount, durationMonths, currencyInt);
            System.out.println(rs);
        }
    }

    public static void validationCredit(){

    }





}
