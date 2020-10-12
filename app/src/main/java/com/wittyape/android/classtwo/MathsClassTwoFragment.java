package com.wittyape.android.classtwo;

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

public class MathsClassTwoFragment extends Fragment implements View.OnClickListener {

    public MathsClassTwoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_maths_class_two, container, false);

        Button counting = view.findViewById(R.id.button_counting_class_two);
        Button addition = view.findViewById(R.id.button_addition_class_two);
        Button subtraction = view.findViewById(R.id.button_subtraction_class_two);
        Button multiplication = view.findViewById(R.id.button_multiplication_class_two);
        Button roman = view.findViewById(R.id.button_roman_class_two);

        counting.setOnClickListener(this);
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
            case R.id.button_counting_class_two:
                intent.putExtra("userClass", "countingtwo");
                break;
            case R.id.button_addition_class_two:
                intent.putExtra("userClass", "addtwo");
                break;
            case R.id.button_subtraction_class_two:
                intent.putExtra("userClass", "subtracttwo");
                break;
            case R.id.button_multiplication_class_two:
                intent.putExtra("userClass", "multiplytwo");
                break;
            case R.id.button_roman_class_two:
                intent.putExtra("userClass", "romantwo");
                break;
        }

        startActivity(intent);
    }
}
