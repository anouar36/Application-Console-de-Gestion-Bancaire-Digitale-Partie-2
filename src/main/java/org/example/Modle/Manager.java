package org.example.Modle;

public class Manager extends User {

    public Manager(String name, String email, String password,Role role) {
        super(name, email, password, role);
    }

    @Override
    public String getName() {
        return super.getName();
    }

    @Override
    public String getEmail() {
        return super.getEmail();
    }

    @Override
    public void setName(String name) {
        super.setName(name);
    }

    @Override
    public Role getRole() {
        return super.getRole();
    }

    @Override
    public void setEmail(String email) {
        super.setEmail(email);
    }

    @Override
    public void setPassword(String password) {
        super.setPassword(password);
    }

    @Override
    public void setRole(Role role) {
        super.setRole(role);
    }

    @Override
    public User login() {
        return null;
    }
}
