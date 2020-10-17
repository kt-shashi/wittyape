package com.wittyape.android.classone;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wittyape.android.R;
import com.wittyape.android.helperclasses.QuestionModel;
import com.wittyape.android.leaderboard.LeaderboardModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class PracticeEnglishFragment extends Fragment implements View.OnClickListener {

    public PracticeEnglishFragment() {

    }

    private TextView textViewHeading;
    private TextView textViewQuestion;
    private TextInputLayout textInputLayoutAnswer;
    private Button buttonCheck;
    private Button buttonNext;
    private Button buttonRetry;
    private ArrayList<QuestionModel> questionList;

    private int questionCount = 0;
    private int solvedCount = 0;

    private FirebaseFirestore firestore;
    private CollectionReference collectionReference;
    private FirebaseAuth firebaseAuth;

    public static final String COLLECTION_NAME = "users";

    private String heading;

    boolean isAnswered = false;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String dbname = getArguments().getString("dbname");

        firebaseAuth = FirebaseAuth.getInstance();
        firestore = FirebaseFirestore.getInstance();
        collectionReference = firestore.collection(dbname);

        heading = dbname;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.practiceenglish, container, false);

        questionList = new ArrayList<>();
        initViews(view);
        setHeading();

        return view;
    }

    private void initViews(View view) {
        textViewHeading = view.findViewById(R.id.text_view_heading_english);
        textViewQuestion = view.findViewById(R.id.text_view_question_english);
        textInputLayoutAnswer = view.findViewById(R.id.text_input_layout_answer_english);
        buttonCheck = view.findViewById(R.id.button_check_english);
        buttonNext = view.findViewById(R.id.button_next_english);
        buttonRetry = view.findViewById(R.id.button_retry_english);

        buttonCheck.setOnClickListener(this);
        buttonNext.setOnClickListener(this);
        buttonRetry.setOnClickListener(this);

        buttonRetry.setVisibility(View.GONE);
        getData();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.button_retry_english:
                startNewRound();
                getData();
                break;
            case R.id.button_check_english:
                if (textInputLayoutAnswer.getEditText().getText().toString().trim().toLowerCase().equals(questionList.get(questionCount).getAnswer())) {
                    textInputLayoutAnswer.setError(null);
                    buttonCheck.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_correct, 0);
                    buttonCheck.setBackgroundResource(R.drawable.design_button_correct);
                    if (!isAnswered) {
                        solvedCount++;
                        isAnswered = true;
                    }
                } else {
                    textInputLayoutAnswer.setError(null);
                    buttonCheck.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.icon_wrong, 0);
                    buttonCheck.setBackgroundResource(R.drawable.design_button_wrong);
                    isAnswered = true;
                }
                break;
            case R.id.button_next_english:
                if (textInputLayoutAnswer.getEditText().getText().toString().equals("")) {
                    textInputLayoutAnswer.setError("Question not solved");
                    return;
                }
                isAnswered = false;
                buttonCheck.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
                buttonCheck.setBackgroundResource(R.drawable.design_button_login);
                textInputLayoutAnswer.getEditText().setText("");
                questionCount++;
                setQuestion();
                break;
        }
    }

    private void setQuestion() {
        if (questionCount >= questionList.size()) {
            textViewQuestion.setText("");
            showResult(solvedCount, questionCount);
            updateScoreInFirestore(solvedCount);
            practiceOver();
            return;
        }
        textViewQuestion.setText(questionList.get(questionCount).getQuestion());
    }

    private void practiceOver() {
        buttonRetry.setVisibility(View.VISIBLE);
        buttonCheck.setVisibility(View.GONE);
        buttonNext.setVisibility(View.GONE);

        questionCount = 0;
        solvedCount = 0;
    }

    private void startNewRound() {
        isAnswered = false;
        buttonRetry.setVisibility(View.GONE);
        buttonCheck.setVisibility(View.VISIBLE);
        buttonNext.setVisibility(View.VISIBLE);
    }

    private void setData(ArrayList<QuestionModel> dataList) {
        buttonRetry.setVisibility(View.GONE);
        questionList = dataList;

        setQuestion();
    }

    private void getData() {

        final ArrayList<QuestionModel> custom = new ArrayList<>();

        collectionReference
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {

                            QuestionModel questionData = snapshot.toObject(QuestionModel.class);
                            custom.add(questionData);

                        }

                        setData(custom);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void setHeading() {
        if (heading.equals("onebabies")) {
            textViewHeading.setText("Enter the baby name of the animal:");
        } else if (heading.equals("oneopposite")) {
            textViewHeading.setText("Enter the antonym of:");
        } else if (heading.equals("oneadjectives")) {
            textViewHeading.setText("Find the adjective in the sentence:");
        }
    }

    private void updateScoreInFirestore(final int finalScore) {

        final CollectionReference collectionReference = firestore.collection(COLLECTION_NAME);

        String userClassName = null;
        if (heading.equals("onebabies") || heading.equals("oneopposite") || heading.equals("oneadjectives")) {
            userClassName = "scoreclass1";
        }
        if (userClassName == null || userClassName.isEmpty())
            userClassName = "scoreclass1";

        final CollectionReference collectionReferenceScore = firestore.collection(userClassName);

        collectionReference
                .document(firebaseAuth.getCurrentUser().getUid())
                .get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        //Check if the document exists
                        if (documentSnapshot.exists()) {

                            String userScore = documentSnapshot.getString("score");
                            final String userName = documentSnapshot.getString("name");

                            if (userScore.isEmpty()) {
                                userScore = "0";
                            }

                            final int updatedScore = finalScore + Integer.parseInt(userScore);

                            Map<String, Object> data = new HashMap<>();
                            data.put("score", String.valueOf(updatedScore));

                            collectionReference
                                    .document(firebaseAuth.getCurrentUser().getUid())
                                    .update(data);

                            LeaderboardModel leaderboardModel = new LeaderboardModel(userName, String.valueOf(updatedScore));

                            collectionReferenceScore
                                    .document(firebaseAuth.getCurrentUser().getUid())
                                    .set(leaderboardModel);

                        }
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void showResult(int finalScore, int finalQuestions) {

        LayoutInflater layoutInflater = LayoutInflater.from(getActivity());
        View layoutView = layoutInflater.inflate(R.layout.dialog_result, null);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity()).setView(layoutView);
        alertDialog.setCancelable(true);
        final AlertDialog testDialog = alertDialog.create();
        testDialog.show();

        TextView textViewFinalScore = layoutView.findViewById(R.id.text_view_correct_ans_dialog);
        TextView textViewTotalQuestions = layoutView.findViewById(R.id.text_view_total_question_dialog);
        TextView textViewAccuracy = layoutView.findViewById(R.id.text_view_accuracy_dialog);

        textViewFinalScore.setText("Correct Answers : " + finalScore);
        textViewTotalQuestions.setText("Questions attempted : " + finalQuestions);

        if (finalScore == 0 || finalQuestions == 0) {
            textViewAccuracy.setText("Accuracy: 0%");
        } else {
            textViewAccuracy.setText("Accuracy: " + Integer.toString((finalScore * 100) / finalQuestions) + "%");
        }
    }
}
