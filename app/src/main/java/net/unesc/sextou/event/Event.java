package net.unesc.sextou.event;


import net.unesc.sextou.database.SqlTable;

public class Event implements SqlTable {
    private String title;
    private String content;

    public Event() {
    }

    public Event(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
