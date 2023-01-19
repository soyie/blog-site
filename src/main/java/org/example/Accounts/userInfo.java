package org.example.Accounts;

import java.util.UUID;

public class userInfo {
    private String Name;
    private String Surname;
    private String Email;
    private String Password;
    private UUID UserId;
    public userInfo(String Name, String Surname, String Email, String Password, UUID uuid){
        this.Name = Name;
        this.Surname = Surname;
        this.Email = Email;
        this.Password = Password;
        this.UserId = uuid;
    }

    public userInfo() {

    }

    public String getSurname() {
        return Surname;
    }
    public String getName() {
        return Name;
    }

    public String getEmail() {
        return Email;
    }

    public String getPassword() {
        return Password;
    }

    public UUID getUserId() {
        return UserId;
    }
}
