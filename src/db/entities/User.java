package db.entities;

import model.UserRole;

public class User {
    private String email;
    private String pass;
    private String firstName;
    private String secondName;
    private UserRole role;

    public User() {
    }

    public User(String email, String pass, String firstName, String secondName) {
        this.email = email;
        this.pass = pass;
        this.firstName = firstName;
        this.secondName = secondName;
        this.role = UserRole.AUTHORIZED;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
}