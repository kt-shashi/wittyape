package com.wittyape.android.login;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.wittyape.android.MainActivity;
import com.wittyape.android.R;

public class SignInFragment extends Fragment implements View.OnClickListener {

    public SignInFragment() {

    }

    Button buttonSignIn;
    TextInputLayout textInputLayoutEmail;
    TextInputLayout textInputLayoutPassword;

    ProgressBar progressBar;

    FirebaseAuth firebaseAuth;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_in, container, false);

        firebaseAuth = FirebaseAuth.getInstance();

        buttonSignIn = view.findViewById(R.id.button_sign_in_fragment);
        textInputLayoutEmail = view.findViewById(R.id.text_input_layout_email_sign_in);
        textInputLayoutPassword = view.findViewById(R.id.text_input_layout_password_sign_in);
        progressBar = view.findViewById(R.id.progress_bar_sign_in);
        progressBar.setVisibility(View.GONE);
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

        loginUser(userEmail, userPass);

    }

    private void loginUser(String userEmail, String userPass) {

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.signInWithEmailAndPassword(userEmail, userPass)
                .addOnSuccessListener(new OnSuccessListener<AuthResult>() {
                    @Override
                    public void onSuccess(AuthResult authResult) {

                        progressBar.setVisibility(View.GONE);
                        startActivity(new Intent(getActivity(), MainActivity.class));
                        getActivity().finish();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {

                        progressBar.setVisibility(View.GONE);

                        if (e instanceof FirebaseAuthInvalidCredentialsException) {
                            textInputLayoutPassword.setError("Invalid Password");
                        } else {
                            textInputLayoutPassword.setError(null);
                        }

                        if (e instanceof FirebaseAuthInvalidUserException) {
                            textInputLayoutEmail.setError("Email not in use");
                        } else {
                            textInputLayoutEmail.setError(null);
                        }
                    }
                });

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
