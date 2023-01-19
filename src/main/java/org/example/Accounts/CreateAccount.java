package org.example.Accounts;

import org.example.databases.Database;

import java.sql.SQLException;
import java.util.UUID;

public class CreateAccount {
    private String Email;
    private String Password;
    private UUID UserId;
    private String Name;
    private String Surname;


    public void setSurname(String surname) {
        Surname = surname;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setUserId(UUID userId) {
        UserId = userId;
    }

    public boolean CreateAccount(){
        Database data = new Database();
        try {
            data.CreatingAccount(Name, Surname, Email, Password, UserId);
            return true;
        } catch (SQLException e) {
            return false;
        }
    }

}
