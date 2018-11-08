package net.unesc.sextou.event;


import android.view.View;

import java.io.Serializable;
import java.util.function.Consumer;

public class EventType implements Serializable {
    private String title;
    private int icon;
    private boolean checked;
    private View.OnClickListener listener;

    public EventType(String title, int icon) {
        this.title = title;
        this.icon = icon;
    }

    public View.OnClickListener getListener() {
        return listener;
    }

    public void setListener(View.OnClickListener listener) {
        this.listener = listener;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
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
