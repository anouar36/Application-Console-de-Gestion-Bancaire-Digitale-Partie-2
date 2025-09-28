package org.example.Service;

import org.example.Modle.Role;
import org.example.Modle.User;
import org.example.Repository.AuthRepository;


public class AuthService {

    private AuthRepository authRepository;

    public AuthService() {
        this.authRepository=new AuthRepository();
    }

    public User login(String email , String password){

        return this.authRepository.login(email,password);

    }

    public boolean regester(String name, String email, String password, Role role){
        return this.authRepository.regester(name,email,password,role);
    }
}
