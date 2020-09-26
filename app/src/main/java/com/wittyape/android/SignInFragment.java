package com.wittyape.android;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputLayout;

public class SignInFragment extends Fragment implements View.OnClickListener {

    public SignInFragment() {

    }

    Button buttonSignIn;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.sign_in_fragment, container, false);

        buttonSignIn = view.findViewById(R.id.button_sign_in_fragment);
        textInputLayoutEmail = view.findViewById(R.id.text_input_layout_email_sign_in);
        textInputLayoutPassword = view.findViewById(R.id.text_input_layout_password_sign_in);

        buttonSignIn.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {
        String userEmail = textInputLayoutEmail.getEditText().getText().toString().trim();
        String userPass = textInputLayoutPassword.getEditText().getText().toString().trim();

        if (!isValid(userEmail, userPass)) {
            return;
        }

        Log.d("debug_shashi", userEmail);
        Log.d("debug_shashi", userPass);
    }

    private boolean isValid(String userEmail, String userPass) {

        if (userEmail.isEmpty()) {
            textInputLayoutEmail.setError("Cannot be empty");
            return false;
        } else if (!isValidEmail(userEmail)) {
            textInputLayoutEmail.setError("Invalid email");
        } else {
            textInputLayoutEmail.setError(null);
        }

        if (userPass.isEmpty()) {
            textInputLayoutPassword.setError("Cannot be empty");
            return false;
        } else {
            textInputLayoutPassword.setError(null);
        }

        return true;
    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
