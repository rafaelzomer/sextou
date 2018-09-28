package net.unesc.sextou.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;

import java.util.Objects;

@SuppressLint("Registered")
public class DefaultActivity extends AppCompatActivity {

    public Class getPreviousActivity() {
        return null;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getPreviousActivity() != null) {
            Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onNavigateUp() {
        Class previous = getPreviousActivity();
        if (previous == null) {
            return true;
        }
        Intent intent = new Intent(this, previous);
        startActivity(intent);
        finish();
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Class previous = getPreviousActivity();
                if (previous == null) {
                    return true;
                }
                Intent intent = new Intent(this, previous);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}
