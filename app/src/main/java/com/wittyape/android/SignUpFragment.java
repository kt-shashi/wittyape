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

public class SignUpFragment extends Fragment implements View.OnClickListener {

    public SignUpFragment() {

    }

    Button buttonSignUp;
    TextInputLayout textInputLayoutName;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.sign_up_fragment, container, false);

        buttonSignUp = view.findViewById(R.id.button_sign_up_fragment);
        textInputLayoutName = view.findViewById(R.id.text_input_layout_profile_sign_up);
        textInputLayoutEmail = view.findViewById(R.id.text_input_layout_email_sign_up);
        textInputLayoutPassword = view.findViewById(R.id.text_input_layout_password_sign_up);

        buttonSignUp.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {

        String userName = textInputLayoutName.getEditText().getText().toString().trim();
        String userEmail = textInputLayoutEmail.getEditText().getText().toString().trim();
        String userPass = textInputLayoutPassword.getEditText().getText().toString().trim();

        if (!isValid(userName, userEmail, userPass)) {
            return;
        }

        Log.d("debug_shashi", userName);
        Log.d("debug_shashi", userEmail);
        Log.d("debug_shashi", userPass);

    }

    private boolean isValid(String userName, String userEmail, String userPass) {

        if (userName.isEmpty()) {
            textInputLayoutName.setError("Cannot be empty");
            return false;
        } else {
            textInputLayoutName.setError(null);
        }

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
