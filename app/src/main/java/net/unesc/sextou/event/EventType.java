package net.unesc.sextou.event;


import java.io.Serializable;

public class EventType implements Serializable {
    private String title;

    public EventType(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
