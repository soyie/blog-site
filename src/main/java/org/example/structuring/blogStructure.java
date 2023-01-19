package org.example.structuring;

import java.time.LocalDate;
import java.time.LocalTime;

public class blogStructure {
    private String Writer;
    private String Topic;
    private LocalDate Date;
    private LocalTime Time;
    private int UpVotes;
    private int DownVotes;



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


}
