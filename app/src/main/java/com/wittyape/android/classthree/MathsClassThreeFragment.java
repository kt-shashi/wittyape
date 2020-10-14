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

public class MathsClassThreeFragment extends Fragment implements View.OnClickListener {

    public MathsClassThreeFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maths_class_three, container, false);

        Button addition = view.findViewById(R.id.button_addition_class_three);
        Button subtraction = view.findViewById(R.id.button_subtraction_class_three);
        Button multiplication = view.findViewById(R.id.button_multiplication_class_three);
        Button roman = view.findViewById(R.id.button_roman_class_three);

        addition.setOnClickListener(this);
        subtraction.setOnClickListener(this);
        multiplication.setOnClickListener(this);
        roman.setOnClickListener(this);

        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(getActivity(), TabActivity.class);

        switch (view.getId()) {
            case R.id.button_addition_class_three:
                intent.putExtra("userClass", "addthree");
                break;
            case R.id.button_subtraction_class_three:
                intent.putExtra("userClass", "subtractthree");
                break;
            case R.id.button_multiplication_class_three:
                intent.putExtra("userClass", "multiplythree");
                break;
            case R.id.button_roman_class_three:
                intent.putExtra("userClass", "romanthree");
                break;
        }

        startActivity(intent);
    }

}
