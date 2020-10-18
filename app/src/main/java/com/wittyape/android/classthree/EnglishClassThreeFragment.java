package com.wittyape.android.classthree;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wittyape.android.R;
import com.wittyape.android.TabActivity;

public class EnglishClassThreeFragment extends Fragment implements View.OnClickListener {

    public EnglishClassThreeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_english_class_three, container, false);

        Button silent = view.findViewById(R.id.button_silent_class_three);
        Button sound = view.findViewById(R.id.button_sound_class_three);

        silent.setOnClickListener(this);
        sound.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getActivity(), TabActivity.class);

        switch (view.getId()) {
            case R.id.button_silent_class_three:
                intent.putExtra("userClass", "threesilent");
                break;
            case R.id.button_sound_class_three:
                intent.putExtra("userClass", "threesound");
                break;
        }

        startActivity(intent);
    }

}
