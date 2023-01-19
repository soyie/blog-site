package org.example.databases;

import io.javalin.apibuilder.EndpointGroup;
import org.example.Accounts.userInfo;
import org.jetbrains.annotations.NotNull;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.UUID;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.get;

public class Database {
    public String sql;
    String jdbcUrl = "jdbc:sqlite:Stories.db";

    public Statement createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl);
        Statement statement = connection.createStatement();
        System.out.println("connection READY");
        return statement;
    }


    public void createDatabase() {
        System.out.println("Table created");
        try {
            sql = "create table if not Exists Users (Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name, Surname, Email, Password, UUID)";
            createConnection().executeUpdate(sql);

            sql = "create table if not Exists Stories(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Topic, Story, Writer, Number_of_UpVotes, Number_of_DownVotes, Date, Time, Image)";
            createConnection().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void CreatingAccount(String Name, String surname, String Email, String Password, UUID uuid) throws SQLException {
        sql = "insert into Users (Name, Surname, Email, Password, UUID) values ('"+Name+"','"+surname+"','"+Email+"','"+Password+"','"+uuid+"')";
        createConnection().executeUpdate(sql);
    }


    public userInfo gettingAccount(String Name, String Password){
        String name = null;
        String surname = null;
        String email = null;
        String password = null;
        UUID userId = null;
        try (final Connection connection = DriverManager.getConnection( jdbcUrl)) {
            try (final Statement stmt = connection.createStatement()) {
                PreparedStatement p = connection.prepareStatement("Select * from Users  where Name = '"+Name+"' and Password = '"+Password+"'");
                ResultSet rs = p.executeQuery();

                while (rs.next()) {

                    name = rs.getString("Name");
                    surname = rs.getString("surname");
                    email = rs.getString("Email");
                    password = rs.getString("Password");
                    userId = UUID.fromString(rs.getString("UUID"));
                    System.out.println("\t \t" + name + "\t" + email + "\t" + password + "\t" + userId);
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return new userInfo(name, surname, email, password, userId);
    }




    public void updateAccount(String Name, String Email, String Password, UUID uuid) {

    }

    public void deleteAccount(UUID uuid) {

    }
}
