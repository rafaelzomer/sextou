package net.unesc.sextou.event;


import java.io.Serializable;

public class EventType implements Serializable {
    private String title;

    private int icon;

    public EventType(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }
}
