package com.wittyape.android.classthree;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wittyape.android.R;
import com.wittyape.android.TabActivity;

public class HomeClassThreeFragment extends Fragment implements View.OnClickListener {

    public HomeClassThreeFragment() {

    }

    private TextView textViewWelcome;
    private TextView textViewScore;
    private ProgressBar progressBar;

    private ImageButton additionClicked;
    private ImageButton subtractionClicked;
    private ImageButton multiplicationClicked;
    private ImageButton romanClicked;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseFirestore firebaseFirestore;

    public static final String COLLECTION_NAME = "users";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home_class_three, container, false);

        initViews(view);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                setUserName();
            }
        };

        return view;
    }

    private void initViews(View view) {
        textViewWelcome = view.findViewById(R.id.text_view_welcome_home_fragment_three);
        textViewScore = view.findViewById(R.id.text_view_score_home_fragment_three);
        progressBar = view.findViewById(R.id.progress_bar_home_class_three);
        progressBar.setVisibility(View.GONE);

        additionClicked = view.findViewById(R.id.image_button_addition_home_class_three);
        subtractionClicked = view.findViewById(R.id.image_button_subtraction_home_class_three);
        multiplicationClicked = view.findViewById(R.id.image_button_multiplication_home_class_three);
        romanClicked = view.findViewById(R.id.image_button_roman_home_class_three);

        additionClicked.setOnClickListener(this);
        subtractionClicked.setOnClickListener(this);
        multiplicationClicked.setOnClickListener(this);
        romanClicked.setOnClickListener(this);
    }

    private void setUserName() {

        progressBar.setVisibility(View.VISIBLE);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {

            firebaseFirestore.collection(COLLECTION_NAME)
                    .document(firebaseAuth.getCurrentUser().getUid())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            //Check if the document exists
                            if (documentSnapshot.exists()) {

                                String userName = documentSnapshot.getString("name");
                                String userScore = documentSnapshot.getString("score");

                                if (userName.isEmpty()) {
                                    userName = "";
                                }
                                if (userScore.isEmpty()) {
                                    userScore = "";
                                }

                                userName = "Welcome, " + userName;
                                userScore = "Score: " + userScore;

                                textViewWelcome.setText(userName);
                                textViewScore.setText(userScore);
                                progressBar.setVisibility(View.GONE);

                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getActivity(), TabActivity.class);

        switch (view.getId()) {
            case R.id.image_button_addition_home_class_three:
                intent.putExtra("userClass", "addthree");
                break;
            case R.id.image_button_subtraction_home_class_three:
                intent.putExtra("userClass", "subtractthree");
                break;
            case R.id.image_button_multiplication_home_class_three:
                intent.putExtra("userClass", "multiplythree");
                break;
            case R.id.image_button_roman_home_class_three:
                intent.putExtra("userClass", "romanthree");
                break;
        }

        startActivity(intent);

    }

    @Override
    public void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(authStateListener);

    }

    @Override
    public void onStop() {
        super.onStop();

        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }

    }

}
