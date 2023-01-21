package org.example.structuring;

public class Storyinfo {
    private String Writer;

    private String Genres;
    private String Topic;
    private String Date;
    private String Time;
    private String Story;
    private int UpVotes;
    private int DownVote;
    public Storyinfo(String Topic, String Story, String Genres, String Writer, int Number_of_UpVotes, int Number_of_DownVotes, String Date, String Time){
        this.Writer = Writer;
        this.Genres = Genres;
        this.Topic = Topic;
        this.Date = Date;
        this.Time = Time;
        this.Story = Story;
        this.UpVotes = Number_of_UpVotes;
        this.DownVote = Number_of_DownVotes;
    }
    public Storyinfo() {

    }

    public String getWriter() {
        return Writer;
    }

    public String getGenres() {
        return Genres;
    }

    public String getTopic() {
        return Topic;
    }

    public String getDate() {
        return Date;
    }

    public String getTime() {
        return Time;
    }

    public String getStory() {
        return Story;
    }

    public int getUpVotes() {
        return UpVotes;
    }

    public int getDownVote() {
        return DownVote;
    }


}
