package net.unesc.sextou.event;

import android.os.Bundle;
import android.widget.EditText;

import net.unesc.sextou.R;
import net.unesc.sextou.activity.DefaultActivity;
import net.unesc.sextou.login.LoginActivity;
import net.unesc.sextou.register.RegisterActivity;
import net.unesc.sextou.utils.CustomInputDate;

public class EventNewActivity extends DefaultActivity {
    private EditText dateEvent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.event_new);
        getSupportActionBar().setTitle("Novo Evento");

        dateEvent = findViewById(R.id.dateEvent);
        CustomInputDate.make(EventNewActivity.this, dateEvent);
    }

    @Override
    public Class getPreviousActivity() {
        return LoginActivity.class;
    }
}
