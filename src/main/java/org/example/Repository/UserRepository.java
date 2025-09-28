package org.example.Repository;

import org.example.Modle.User;

public interface UserRepository {
    public User login(String email,String pasword );
}
