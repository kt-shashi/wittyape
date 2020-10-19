package com.wittyape.android.classthree;

import android.app.ProgressDialog;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wittyape.android.R;

import java.io.IOException;

public class LearnEnglishThreeAnimalSound extends Fragment implements View.OnClickListener {

    public LearnEnglishThreeAnimalSound() {

    }

    private Button buttonCat;
    private Button buttonCow;
    private Button buttonCrow;
    private Button buttonDog;
    private Button buttonDuck;
    private Button buttonWolf;

    private MediaPlayer mediaPlayer;
    private ProgressDialog dialog;

    private String URL_CAT = "https://firebasestorage.googleapis.com/v0/b/wittyape-android.appspot.com/o/cat_meow.mp3?alt=media&token=8989de6b-6e7d-4105-aaf8-7f7b88858c61";
    private String URL_COW = "https://firebasestorage.googleapis.com/v0/b/wittyape-android.appspot.com/o/cow_moo.mp3?alt=media&token=b52de61f-793f-4700-a75f-a8cd1a7117cf";
    private String URL_CROW = "https://firebasestorage.googleapis.com/v0/b/wittyape-android.appspot.com/o/crow_caw.mp3?alt=media&token=a2631c5c-90bf-4100-baa9-b06901ad2865";
    private String URL_DOG = "https://firebasestorage.googleapis.com/v0/b/wittyape-android.appspot.com/o/dog_bark.mp3?alt=media&token=f5352bca-4c36-48d6-8393-7b7ed77799f1";
    private String URL_DUCK = "https://firebasestorage.googleapis.com/v0/b/wittyape-android.appspot.com/o/duck_quack.mp3?alt=media&token=e36643c2-2685-4191-a044-6a408839a645";
    private String URL_WOLF = "https://firebasestorage.googleapis.com/v0/b/wittyape-android.appspot.com/o/wolf_howl.mp3?alt=media&token=f6b1bceb-231e-4ffe-82e5-6aa96306db9d";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learn_english_animal_sound_three, container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view) {

        buttonCat = view.findViewById(R.id.button_audio_cat);
        buttonCow = view.findViewById(R.id.button_audio_cow);
        buttonCrow = view.findViewById(R.id.button_audio_crow);
        buttonDog = view.findViewById(R.id.button_audio_dog);
        buttonDuck = view.findViewById(R.id.button_audio_duck);
        buttonWolf = view.findViewById(R.id.button_audio_wolf);

        buttonCat.setOnClickListener(this);
        buttonCow.setOnClickListener(this);
        buttonCrow.setOnClickListener(this);
        buttonDog.setOnClickListener(this);
        buttonDuck.setOnClickListener(this);
        buttonWolf.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        showProgressDialog();

        mediaPlayer = new MediaPlayer();

        switch (view.getId()) {
            case R.id.button_audio_cat:
                try {

                    buttonCat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_pause, 0, 0, 0);
                    mediaPlayer.setDataSource(URL_CAT);
                    playAudio(buttonCat);

                } catch (IOException e) {
                    e.printStackTrace();
                    buttonCat.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_play, 0, 0, 0);
                }
                break;
            case R.id.button_audio_cow:
                try {
                    buttonCow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_pause, 0, 0, 0);

                    mediaPlayer.setDataSource(URL_COW);
                    playAudio(buttonCow);

                } catch (IOException e) {
                    buttonCow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_play, 0, 0, 0);
                    e.printStackTrace();
                }
                break;
            case R.id.button_audio_crow:
                try {

                    buttonCrow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_pause, 0, 0, 0);

                    mediaPlayer.setDataSource(URL_CROW);
                    playAudio(buttonCrow);

                } catch (IOException e) {
                    e.printStackTrace();
                    buttonCrow.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_play, 0, 0, 0);
                }
                break;
            case R.id.button_audio_dog:
                try {

                    buttonDog.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_pause, 0, 0, 0);

                    mediaPlayer.setDataSource(URL_DOG);
                    playAudio(buttonDog);

                } catch (IOException e) {
                    e.printStackTrace();
                    buttonDog.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_play, 0, 0, 0);
                }
                break;
            case R.id.button_audio_duck:
                try {

                    buttonDuck.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_pause, 0, 0, 0);

                    mediaPlayer.setDataSource(URL_DUCK);
                    playAudio(buttonDuck);

                } catch (IOException e) {
                    e.printStackTrace();
                    buttonDuck.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_play, 0, 0, 0);
                }
                break;
            case R.id.button_audio_wolf:
                try {

                    buttonWolf.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_pause, 0, 0, 0);

                    mediaPlayer.setDataSource(URL_WOLF);
                    playAudio(buttonWolf);

                } catch (IOException e) {
                    e.printStackTrace();
                    buttonWolf.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_play, 0, 0, 0);
                }
                break;
        }
    }

    private void playAudio(final Button button) {
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {

                stopProgressDialog();

                if (mediaPlayer.isPlaying()) {
                    mediaPlayer.pause();
                } else {
                    mediaPlayer.start();
                    button.setCompoundDrawablesWithIntrinsicBounds(R.drawable.icon_audio_play, 0, 0, 0);
                }

            }
        });

        mediaPlayer.prepareAsync();
    }

    private void showProgressDialog() {
        dialog = new ProgressDialog(getActivity());
        dialog.setMessage("Audio Playing, please wait...");
        dialog.show();
    }

    private void stopProgressDialog() {
        if (dialog.isShowing()) {
            dialog.dismiss();
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        if (mediaPlayer != null) {
            mediaPlayer.pause();
            mediaPlayer.release();
        }
    }
}
