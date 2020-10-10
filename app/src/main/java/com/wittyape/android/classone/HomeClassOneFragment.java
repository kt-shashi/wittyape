package com.wittyape.android.classone;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wittyape.android.LeaderboardModel;
import com.wittyape.android.R;
import com.wittyape.android.TabActivity;

import java.util.ArrayList;
import java.util.Collections;

public class HomeClassOneFragment extends Fragment implements View.OnClickListener {

    public HomeClassOneFragment() {

    }

    private TextView textViewWelcome;
    private TextView textViewScore;
    private ProgressBar progressBar;

    private ImageButton countingClicked;
    private ImageButton additionClicked;
    private ImageButton subtractionClicked;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseFirestore firebaseFirestore;

    private ProgressBar progressBarLeaderboard;
    private ArrayList<LeaderboardModel> leaderArrayList;

    private TextView textViewNameL;
    private TextView textViewScoreL;

    public static final String COLLECTION_NAME = "users";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_home_class_one, container, false);

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
        textViewWelcome = view.findViewById(R.id.text_view_welcome_home_fragment);
        textViewScore = view.findViewById(R.id.text_view_score_home_fragment);
        progressBar = view.findViewById(R.id.progress_bar_home_class_one);
        progressBar.setVisibility(View.GONE);

        countingClicked = view.findViewById(R.id.image_button_counting_home_class_one);
        additionClicked = view.findViewById(R.id.image_button_addition_home_class_one);
        subtractionClicked = view.findViewById(R.id.image_button_subtraction_home_class_one);
        countingClicked.setOnClickListener(this);
        additionClicked.setOnClickListener(this);
        subtractionClicked.setOnClickListener(this);

        textViewNameL = view.findViewById(R.id.text_view_name_leader);
        textViewScoreL = view.findViewById(R.id.text_view_score_leader);
        progressBarLeaderboard = view.findViewById(R.id.progress_bar_leaderboard_class_one);
        leaderArrayList = new ArrayList<>();
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
                                String userScore = documentSnapshot.getString("score").toString();

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

                                setLeaderBoard();

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
            case R.id.image_button_counting_home_class_one:
                intent.putExtra("userClass", "countingone");
                break;
            case R.id.image_button_addition_home_class_one:
                intent.putExtra("userClass", "addone");
                break;
            case R.id.image_button_subtraction_home_class_one:
                intent.putExtra("userClass", "subtractone");
                break;
        }

        startActivity(intent);

    }

    private void setLeaderBoard() {
        Log.d("debug_shashi", "leaderboared in");
        initLeaderData();

        progressBarLeaderboard.setVisibility(View.GONE);
    }

    private void initLeaderData() {

        firebaseFirestore.collection("scoreclass1")
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        leaderArrayList.clear();

                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            LeaderboardModel leader = snapshot.toObject(LeaderboardModel.class);
                            leaderArrayList.add(leader);

                            Log.d("debug_shashi", leader.getName());
                            Log.d("debug_shashi", leader.getScore());
                        }

                        Collections.sort(leaderArrayList);

                        if (leaderArrayList.size() > 5)
                            leaderArrayList.subList(5, leaderArrayList.size()).clear();

                        Log.d("debug_shashi", String.valueOf(leaderArrayList.size()));

                        textViewNameL.setText("");
                        textViewScoreL.setText("");

                        for (int i = 0; i < leaderArrayList.size(); i++) {
                            LeaderboardModel leader = leaderArrayList.get(i);
                            textViewNameL.append(leader.getName() + "\n");
                            textViewScoreL.append(leader.getScore() + "\n");
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Failed to load", Toast.LENGTH_SHORT).show();
                        progressBarLeaderboard.setVisibility(View.GONE);
                    }
                });
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
