package com.wittyape.android.classone;

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

public class EnglishClassOneFragment extends Fragment implements View.OnClickListener {

    public EnglishClassOneFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_english_class_one, container, false);

        Button animalBaby = view.findViewById(R.id.button_baby_name_class_one);
        Button antonyms = view.findViewById(R.id.button_opposite_class_one);
        Button adjective = view.findViewById(R.id.button_adjective_class_one);

        animalBaby.setOnClickListener(this);
        antonyms.setOnClickListener(this);
        adjective.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getActivity(), TabActivity.class);

        switch (view.getId()) {
            case R.id.button_baby_name_class_one:
                intent.putExtra("userClass", "onebabies");
                break;
            case R.id.button_opposite_class_one:
                intent.putExtra("userClass", "oneopposite");
                break;
            case R.id.button_adjective_class_one:
                intent.putExtra("userClass", "oneadjectives");
                break;
        }

        startActivity(intent);
    }

}
