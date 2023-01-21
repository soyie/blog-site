package org.example.Accounts;

import org.example.databases.Database;

import java.sql.SQLException;
import java.util.UUID;

public class Login {
    private String Password;
    private String Email;


    public void setEmail(String email) {
        Email = email;
    }


    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public boolean logIn(){
        Database data = new Database();
        data.gettingAccount(Email, Password);
        return true;
    }
}
