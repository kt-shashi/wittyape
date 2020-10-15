package com.wittyape.android.classfour;

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

public class MathsClassFourFragment extends Fragment implements View.OnClickListener {

    public MathsClassFourFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maths_class_four, container, false);

        Button addition = view.findViewById(R.id.button_addition_class_four);
        Button subtraction = view.findViewById(R.id.button_subtraction_class_four);
        Button multiplication = view.findViewById(R.id.button_multiplication_class_four);
        Button roman = view.findViewById(R.id.button_roman_class_four);

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
            case R.id.button_addition_class_four:
                intent.putExtra("userClass", "addfour");
                break;
            case R.id.button_subtraction_class_four:
                intent.putExtra("userClass", "subtractfour");
                break;
            case R.id.button_multiplication_class_four:
                intent.putExtra("userClass", "multiplyfour");
                break;
            case R.id.button_roman_class_four:
                intent.putExtra("userClass", "romanfour");
                break;
        }

        startActivity(intent);
    }

}
