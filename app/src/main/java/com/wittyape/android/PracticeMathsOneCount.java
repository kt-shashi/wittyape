package com.wittyape.android;


import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.Random;

public class PracticeMathsOneCount extends Fragment implements View.OnClickListener {

    public PracticeMathsOneCount() {

    }

    private TextView textViewTimer;
    private TextView textViewScore;

    private int correctScore = 0;
    private int totalQuestions = 0;

    private TextView textViewQuestion;

    private ArrayList<Integer> answersList;
    private int locationOfCorrectAnswer;

    private Button buttonAnswer00;
    private Button buttonAnswer01;
    private Button buttonAnswer10;
    private Button buttonAnswer11;

    private Button buttonStartStop;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.practicemathsonecount, container, false);

        initViews(view);

        return view;

    }

    private void initViews(View view) {

        textViewTimer = view.findViewById(R.id.text_view_timer_couting_class_one);
        textViewScore = view.findViewById(R.id.text_view_score_couting_class_one);

        textViewQuestion = view.findViewById(R.id.text_view_question_counting_class_one);

        buttonAnswer00 = view.findViewById(R.id.button_count_00_class_one);
        buttonAnswer01 = view.findViewById(R.id.button_count_01_class_one);
        buttonAnswer10 = view.findViewById(R.id.button_count_10_class_one);
        buttonAnswer11 = view.findViewById(R.id.button_count_11_class_one);

        buttonStartStop = view.findViewById(R.id.button_start_count_class_one);

        buttonAnswer00.setOnClickListener(this);
        buttonAnswer01.setOnClickListener(this);
        buttonAnswer10.setOnClickListener(this);
        buttonAnswer11.setOnClickListener(this);
        buttonStartStop.setOnClickListener(this);

        answersList = new ArrayList<>();
    }

    @Override
    public void onClick(View view) {

        if (view.getId() == R.id.button_start_count_class_one) {

            preCountingTimer();
            setScore();
            setQuestion();
            startCountDown();

        } else {

            if (Integer.valueOf(((Button) view).getText().toString()) == answersList.get(locationOfCorrectAnswer)) {
                answerClickAnimation(view, true);

                correctScore++;
                totalQuestions++;
                setScore();

                setQuestion();
            } else {
                answerClickAnimation(view, false);

                totalQuestions++;
                setScore();

                setQuestion();
            }

        }
    }

    private void setQuestion() {

        answersList.clear();
        Random random = new Random();

        int firstNumber = random.nextInt(30);
        int secondNumber = firstNumber + 1;

        String question = "What comes after " + Integer.toString(firstNumber) + " ?";
        textViewQuestion.setText(question);

        locationOfCorrectAnswer = random.nextInt(4);

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer) {
                answersList.add(secondNumber);
            } else {
                int wrongAnswer = random.nextInt(31);

                while (wrongAnswer == secondNumber)
                    wrongAnswer = random.nextInt(31);

                answersList.add(wrongAnswer);
            }

        }

        buttonAnswer00.setText(Integer.toString(answersList.get(0)));
        buttonAnswer01.setText(Integer.toString(answersList.get(1)));
        buttonAnswer10.setText(Integer.toString(answersList.get(2)));
        buttonAnswer11.setText(Integer.toString(answersList.get(3)));
    }

    private void preCountingTimer() {

        textViewTimer.setText("Timer");
        textViewQuestion.setText("Score");

        buttonAnswer00.setEnabled(true);
        buttonAnswer01.setEnabled(true);
        buttonAnswer10.setEnabled(true);
        buttonAnswer11.setEnabled(true);

        buttonStartStop.setVisibility(View.GONE);

        correctScore = 0;
        totalQuestions = 0;

    }

    private void startCountDown() {

        new CountDownTimer(10100, 1000) {

            @Override
            public void onTick(long l) {

                long timeLeft = l / 1000;

                if (timeLeft <= 5) {
                    textViewTimer.setTextColor(Color.RED);
                }

                textViewTimer.setText(String.valueOf(l / 1000) + "s");
            }

            @Override
            public void onFinish() {
                textViewTimer.setTextColor(Color.BLACK);
                postCountingTimer();
            }

        }.start();

    }

    private void postCountingTimer() {
        textViewTimer.setText("Time-up");
        textViewQuestion.setText("-");

        buttonAnswer00.setText("-");
        buttonAnswer01.setText("-");
        buttonAnswer10.setText("-");
        buttonAnswer11.setText("-");

        buttonAnswer00.setEnabled(false);
        buttonAnswer01.setEnabled(false);
        buttonAnswer10.setEnabled(false);
        buttonAnswer11.setEnabled(false);

        buttonStartStop.setVisibility(View.VISIBLE);
        buttonStartStop.setText("Retry");

        correctScore = 0;
        totalQuestions = 0;
    }

    private void setScore() {
        textViewScore.setText(String.valueOf(correctScore) + "/" + String.valueOf(totalQuestions));
    }

    private void answerClickAnimation(final View view, final boolean isCorrect) {
        Animation shake = AnimationUtils.loadAnimation(getActivity(), R.anim.shake_animation);
        view.setAnimation(shake);

        shake.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                if (isCorrect)
                    view.setBackgroundColor(getResources().getColor(R.color.colorGreen));
                else
                    view.setBackgroundColor(getResources().getColor(R.color.colorRed));
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                view.setBackgroundResource(R.drawable.design_button_answer_count);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

}
