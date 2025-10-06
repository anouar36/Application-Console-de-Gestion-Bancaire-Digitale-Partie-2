package org.example.Controller;

import org.example.Modle.Manager;
import org.example.Modle.Role;
import org.example.Modle.User;
import org.example.Service.AuthService;

import static org.example.Modle.Role.*;

public class AuthController {
    private AuthService authService;
    private static User user;

    public AuthController() {
        this.authService= new AuthService();


    }

    public String regester(String name, String email, String password, Integer roleInt){

        if(name.length()<4){
            return "please you have error input name";
        }
        if(email==null || !email.contains("@gmail.com") ){
            return "email is failed";
        }
        if(password.length()<6){
            return "Password must be at least 6 characters long.";
        }
        if(roleInt < 1 || roleInt > 4){

            return "please enter a valid number between 1 and 4";
        }

        Role role = switch (roleInt){
            case 1 -> AUDITOR;
            case 2 -> TELLER;
            case 3 -> MANAGER;
            case 4 -> ADMIN;
            default -> ADMIN;
        };

        boolean rs = this.authService.regester(name,email,password,role);

        if (rs) {
            return name+" "+ role+" ✅  added successfully!";
        }else {
            return "⚠  No user was added.";
        }


    }

    public User login(String email ,String password){
        User userAuth = authService.login(email, password);

        if (userAuth == null) {
            return null;
        } else {
            return user = userAuth;

        }
    }
}
