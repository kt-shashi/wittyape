package com.wittyape.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class MathsClassOneFragment extends Fragment implements View.OnClickListener {

    public MathsClassOneFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_maths_class_one, container, false);

        Button counting = view.findViewById(R.id.button_counting_class_one);
        Button addition = view.findViewById(R.id.button_addition_class_one);
        Button subtraction = view.findViewById(R.id.button_subtraction_class_one);

        counting.setOnClickListener(this);
        addition.setOnClickListener(this);
        subtraction.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button_counting_class_one:
                countingClicked();
                break;
            case R.id.button_addition_class_one:
                additionClicked();
                break;
            case R.id.button_subtraction_class_one:
                subTractionClicked();
                break;
        }
    }

    private void countingClicked() {
        Toast.makeText(getActivity(), "Counting", Toast.LENGTH_SHORT).show();
    }

    private void additionClicked() {
        Toast.makeText(getActivity(), "Addition", Toast.LENGTH_SHORT).show();
    }

    private void subTractionClicked() {
        Toast.makeText(getActivity(), "Sub", Toast.LENGTH_SHORT).show();
    }
}
