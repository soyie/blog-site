package org.example.databases;


import org.example.Accounts.userInfo;
import org.example.structuring.Storyinfo;

import java.sql.*;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.UUID;


public class Database {
    public String sql;
    String jdbcUrl = "jdbc:sqlite:Stories.db";

    public Statement createConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl);
        Statement statement = connection.createStatement();
        return statement;
    }


    public void createDatabase() {
        try {
            sql = "create table if not Exists Users (Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Name, Surname, Email, Password, UUID)";
            createConnection().executeUpdate(sql);

            sql = "create table if not Exists Stories(Id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, Topic, Story, Genre, Writer, Number_of_UpVotes, Number_of_DownVotes, Date, Time, Image)";
            createConnection().executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }


    public void CreatingAccount(String Name, String surname, String Email, String Password, UUID uuid) throws SQLException {
        sql = "insert into Users (Name, Surname, Email, Password, UUID) values ('"+Name+"','"+surname+"','"+Email+"','"+Password+"','"+uuid+"')";
        createConnection().executeUpdate(sql);
    }

    public void WriteStory(String Topic, String Genre, String Story, String Writer, int Number_of_UpVotes, int Number_of_DownVotes, LocalDate Date, LocalTime Time, String Image) throws SQLException {
        sql = "insert into Stories(Topic, Story, Genre, Writer, Number_of_UpVotes, Number_of_DownVotes, Date, Time, Image) values ('"+Topic+"','"+Story+"','"+Genre+"','"+Writer+"','"+Number_of_UpVotes+"','"+Number_of_DownVotes+"','"+Date+"','"+Time+"','"+Image+"')";
        createConnection().executeUpdate(sql);
    }


    public userInfo gettingAccount(String Email, String Password){
        String name = null;
        String surname = null;
        String email = null;
        String password = null;
        UUID userId = null;
        try (final Connection connection = DriverManager.getConnection( jdbcUrl)) {
            try (final Statement stmt = connection.createStatement()) {
                PreparedStatement p = connection.prepareStatement("Select * from Users  where Email = '"+Email+"' and Password = '"+Password+"'");
                ResultSet rs = p.executeQuery();

                while (rs.next()) {

                    name = rs.getString("Name");
                    surname = rs.getString("surname");
                    email = rs.getString("Email");
                    password = rs.getString("Password");
                    userId = UUID.fromString(rs.getString("UUID"));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            return new userInfo(null, null, null, null, null);
        }
        return new userInfo(name, surname, email, password, userId);
    }


    public Storyinfo gettingStories(String writer, String topic){
        String Topic = null;
        String Story = null;
        String Genres = null;
        String Writer = null;
        int Number_of_UpVotes = 0;
        int Number_of_DownVotes = 0;
        String Date = null;
        String Time = null;
        try (final Connection connection = DriverManager.getConnection( jdbcUrl)) {
            try (final Statement stmt = connection.createStatement()) {
                PreparedStatement p = connection.prepareStatement("Select * from Stories where Writer = '"+writer+"' and Topic = '"+topic+"'");
                ResultSet rs = p.executeQuery();

                while (rs.next()) {

                    Topic = rs.getString("Topic");
                    Story = rs.getString("Story");
                    Genres = rs.getString("Genre");
                    Writer = rs.getString("Writer");
                    Number_of_UpVotes = Integer.parseInt(rs.getString("Number_Of_UpVotes"));
                    Date = rs.getString("Date");
                    Time = rs.getString("Time");
                    Number_of_DownVotes = Integer.parseInt(rs.getString("Number_Of_DownVotes"));
                }

            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        } catch (SQLException throwables) {
            return new Storyinfo(null, null, null, null, 0, 0, null, null);
        }
        return new Storyinfo(Topic, Story, Genres, Writer, Number_of_UpVotes, Number_of_DownVotes, Date, Time);
    }



    public void updateAccount(String Name, String Email, String Password, UUID uuid) {

    }

    public void deleteAccount(UUID uuid) {

    }

    public Object gettingAllStories(){
        ArrayList<Storyinfo> stories = new ArrayList<Storyinfo>();
        String Topic = null;
        String Story = null;
        String Genres = null;
        String Writer = null;
        int Number_of_UpVotes = 0;
        int Number_of_DownVotes = 0;
        String Date = null;
        String Time = null;
        try (final Connection connection = DriverManager.getConnection( jdbcUrl)) {
            try (final Statement stmt = connection.createStatement()) {
                PreparedStatement p = connection.prepareStatement("Select * from Stories");
                ResultSet rs = p.executeQuery();

                while (rs.next()) {

                    Topic = rs.getString("Topic");
                    Story = rs.getString("Story");
                    Genres = rs.getString("Genre");
                    Writer = rs.getString("Writer");
                    Number_of_UpVotes = Integer.parseInt(rs.getString("Number_Of_UpVotes"));
                    Date = rs.getString("Date");
                    Time = rs.getString("Time");
                    Number_of_DownVotes = Integer.parseInt(rs.getString("Number_Of_DownVotes"));
//                    System.out.println("story "+Story);
//                    System.out.println("genre "+Genres);
                    stories.add(new Storyinfo(Topic, Genres, Story, Writer, Number_of_UpVotes, Number_of_DownVotes, Date, Time));
                    System.out.println(new Storyinfo(Topic, Story, Genres, Writer, Number_of_UpVotes, Number_of_DownVotes, Date, Time));
//                    System.out.println("T "+Topic+ " S "+Story+ " G "+Genres+ " W "+Writer+ " NU "+Number_of_UpVotes+ " ND "+Number_of_DownVotes+ " D "+Date+ " T "+Time);
                }

            } catch (SQLException throwables) {
                return new Storyinfo(null, null, null, null, 0, 0, null, null);
            }
        } catch (SQLException throwables) {
            return new Storyinfo(null, null, null, null, 0, 0, null, null);
        }
        return stories;
    }
}
