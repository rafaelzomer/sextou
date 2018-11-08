package net.unesc.sextou.login;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;

import net.unesc.sextou.AppActivity;
import net.unesc.sextou.R;
import net.unesc.sextou.database.SqlCompare;
import net.unesc.sextou.register.RegisterActivity;

import java.util.Arrays;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private EditText mEmailView;
    private EditText mPasswordView;
    private UserDao userDao = new UserDao(LoginActivity.this);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        mEmailView = findViewById(R.id.email);

        List<User> users = userDao.select();
        Log.d("zomer=", "=" + users);

        mPasswordView = findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener((textView, id, keyEvent) -> {
            if (id == EditorInfo.IME_ACTION_DONE || id == EditorInfo.IME_NULL) {
                attemptLogin();
                return true;
            }
            return false;
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(view -> {
            attemptLogin();
        });

        Button mRegisterButton = findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(view -> {
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });
    }

    private void attemptLogin() {
        mEmailView.setError(null);
        mPasswordView.setError(null);

        String email = mEmailView.getText().toString();
        String password = mPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (!TextUtils.isEmpty(password) && !User.isPasswordValid(password)) {
            mPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = mPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(email)) {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!User.isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }
        User user = userDao.selectOne(
                Arrays.asList(
                        SqlCompare.of("email", email),
                        SqlCompare.of("password", password)
                )
        );

        if (cancel || user == null) {
            focusView.requestFocus();
        } else {
            Intent intent = new Intent(LoginActivity.this, AppActivity.class);
            startActivity(intent);
        }
    }
}

