package net.unesc.sextou.register;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import net.unesc.sextou.R;
import net.unesc.sextou.activity.DefaultActivity;
import net.unesc.sextou.login.LoginActivity;
import net.unesc.sextou.login.User;
import net.unesc.sextou.login.UserDao;
import net.unesc.sextou.utils.CustomInputDate;

import java.util.Date;

public class RegisterActivity extends DefaultActivity {
    private UserDao userDao = new UserDao(RegisterActivity.this);
    private EditText mNameView;
    private EditText mBirthdayView;
    private EditText mEmailView;
    private EditText mPasswordView;
    private Button mRegisterButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register_activity);
        mEmailView = findViewById(R.id.email);
        mPasswordView = findViewById(R.id.password);
        mBirthdayView = findViewById(R.id.birthday);
        CustomInputDate.make(RegisterActivity.this, mBirthdayView);
        mNameView = findViewById(R.id.name);
        mRegisterButton = findViewById(R.id.register_button);
        mRegisterButton.setOnClickListener(e -> {
            mEmailView.setError(null);
            mPasswordView.setError(null);
            mBirthdayView.setError(null);
            mNameView.setError(null);

            String email = mEmailView.getText().toString();
            String password = mPasswordView.getText().toString();
            String name = mNameView.getText().toString();
            Date birthday = CustomInputDate.toDate(mBirthdayView.getText().toString());

            if (TextUtils.isEmpty(name)) {
                mNameView.setError(getString(R.string.error_field_required));
                mNameView.requestFocus();
                return;
            }
            if (birthday == null) {
                mBirthdayView.setError(getString(R.string.error_field_required));
                mBirthdayView.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                mPasswordView.setError(getString(R.string.error_field_required));
                mPasswordView.requestFocus();
                return;
            }
            if (!User.isPasswordValid(password)) {
                mPasswordView.setError(getString(R.string.error_invalid_password));
                mPasswordView.requestFocus();
                return;
            }
            if (TextUtils.isEmpty(email)) {
                mEmailView.setError(getString(R.string.error_field_required));
                mEmailView.requestFocus();
                return;
            }
            if (!User.isEmailValid(email)) {
                mEmailView.setError(getString(R.string.error_invalid_email));
                mEmailView.requestFocus();
                return;
            }
            User user = new User(email, password, name, birthday);
            userDao.insert(user);
            finish();
        });
    }

    @Override
    public Class getPreviousActivity() {
        return LoginActivity.class;
    }
}

