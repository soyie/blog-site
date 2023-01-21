package org.example.structuring;

import org.example.databases.Database;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;

public class blogStructure {
    private String Writer;
    private String Genres;
    private String Topic;
    private LocalDate Date;
    private LocalTime Time;
    private String Story;
    private int UpVotes;
    private int DownVotes;

    public String getStory() {
        return Story;
    }
    public void setStory(String story) {
        Story = story;
    }
    public String getGenres() {
        return Genres;
    }

    public void setGenres(String genres) {
        Genres = genres;
    }

    public int getUpVotes() {
        return UpVotes;
    }

    public void setUpVotes(int upVotes) {
        UpVotes = upVotes;
    }

    public int getDownVotes() {
        return DownVotes;
    }

    public void setDownVotes(int downVotes) {
        DownVotes = downVotes;
    }

    public String getWriter() {
        return Writer;
    }

    public void setWriter(String writer) {
        Writer = writer;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    public LocalDate getDate() {
        return Date;
    }

    public void setDate(LocalDate date) {
        Date = date;
    }

    public LocalTime getTime() {
        return Time;
    }

    public void setTime(LocalTime time) {
        Time = time;
    }

    public void SaveStory() throws SQLException {
        Database save = new Database();
        save.WriteStory(getTopic(), getStory(), getGenres(), getWriter(), getUpVotes(), getDownVotes(), getDate(), getTime(), null);
    }
}
