package com.wittyape.android;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpFragment extends Fragment implements View.OnClickListener, AdapterView.OnItemSelectedListener {

    public SignUpFragment() {

    }

    private Button buttonSignUp;
    private TextInputLayout textInputLayoutName;
    private TextInputLayout textInputLayoutEmail;
    private TextInputLayout textInputLayoutPassword;
    private Spinner spinner;
    private String spinnerData;

    ProgressBar progressBar;

    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;

    public static final String COLLECTION_NAME = "users";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_sign_up, container, false);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        buttonSignUp = view.findViewById(R.id.button_sign_up_fragment);
        textInputLayoutName = view.findViewById(R.id.text_input_layout_profile_sign_up);
        textInputLayoutEmail = view.findViewById(R.id.text_input_layout_email_sign_up);
        textInputLayoutPassword = view.findViewById(R.id.text_input_layout_password_sign_up);
        spinner = view.findViewById(R.id.spinner_class_selector_sign_up);
        progressBar = view.findViewById(R.id.progress_bar_sign_up);

        progressBar.setVisibility(View.GONE);

        spinner.setOnItemSelectedListener(this);
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

        registerUser(userName, userEmail, userPass, spinnerData);

    }

    private void registerUser(final String userName, String userEmail, String userPass, final String userClass) {

        progressBar.setVisibility(View.VISIBLE);

        firebaseAuth.createUserWithEmailAndPassword(userEmail, userPass)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()) {

                            String userId = firebaseAuth.getCurrentUser().getUid();

                            DocumentReference documentReference = firebaseFirestore
                                    .collection(COLLECTION_NAME)
                                    .document(userId);

                            Map<String, Object> userData = new HashMap<>();
                            userData.put("name", userName);
                            userData.put("class", userClass);

                            documentReference.set(userData)
                                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {

                                            progressBar.setVisibility(View.GONE);
                                            startActivity(new Intent(getActivity(), MainActivity.class));
                                            getActivity().finish();

                                        }
                                    });
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        if (e instanceof FirebaseAuthUserCollisionException) {
                            textInputLayoutEmail.setError("Email already in use");
                        } else {
                            textInputLayoutEmail.setError(null);
                        }

                        progressBar.setVisibility(View.GONE);
                    }
                });
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

        if (spinnerData.isEmpty()) {
            Toast.makeText(getActivity(), "Choose your class", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getSelectedItem().toString().equals("Select")) {
            spinnerData = "";
            return;
        }

        spinnerData = adapterView.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    private static boolean isValidEmail(String email) {
        return !TextUtils.isEmpty(email) && android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
