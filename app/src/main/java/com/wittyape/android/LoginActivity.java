package com.wittyape.android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textViewLoginMessage;
    private Button buttonLogin;
    private boolean isClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        initViews();

        //To set up the default login method as Sign-in
        onClick(buttonLogin);
    }

    private void initViews() {
        textViewLoginMessage = findViewById(R.id.text_view_login_login_activity);
        buttonLogin = findViewById(R.id.button_login_login_activity);

        buttonLogin.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_login_login_activity:
                updateFragment();
                break;
        }
    }

    private void updateFragment() {

        if (!isClicked) {
            showSignInfragment();

            textViewLoginMessage.setText(getString(R.string.don_t_have_an_account_yet));
            buttonLogin.setText(getString(R.string.sign_up));
            isClicked = true;
        } else {
            showSignUpfragment();

            textViewLoginMessage.setText(getString(R.string.already_have_an_account));
            buttonLogin.setText(getString(R.string.sign_in));
            isClicked = false;
        }

    }

    private void showSignInfragment() {
        SignInFragment signInFragment = new SignInFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_login_fragment_container, signInFragment)
                .commit();
    }

    private void showSignUpfragment() {
        SignUpFragment signUpFragment = new SignUpFragment();

        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_login_fragment_container, signUpFragment)
                .commit();
    }

}