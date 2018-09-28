package net.unesc.sextou.register;

import android.os.Bundle;

import net.unesc.sextou.R;
import net.unesc.sextou.activity.DefaultActivity;
import net.unesc.sextou.login.LoginActivity;

import java.util.Objects;

public class RegisterActivity extends DefaultActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
    }

    @Override
    public Class getPreviousActivity() {
        return LoginActivity.class;
    }
}

