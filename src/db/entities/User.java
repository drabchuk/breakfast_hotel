package db.entities;

public class User {
    private String email;
    private String pass;
    private String firstName;
    private String secondName;

    public User() {
    }

    public User(String email, String pass, String firstName, String secondName) {
        this.email = email;
        this.pass = pass;
        this.firstName = firstName;
        this.secondName = secondName;
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
}