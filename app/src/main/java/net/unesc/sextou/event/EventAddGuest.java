package net.unesc.sextou.event;

import android.os.Bundle;

import net.unesc.sextou.R;
import net.unesc.sextou.activity.DefaultActivity;
import net.unesc.sextou.login.LoginActivity;

public class EventAddGuest extends DefaultActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_addguest);
        getSupportActionBar().setTitle("Novo Evento");
    }

    @Override
    public Class getPreviousActivity() {
        return EventNewActivity.class;
    }
}
