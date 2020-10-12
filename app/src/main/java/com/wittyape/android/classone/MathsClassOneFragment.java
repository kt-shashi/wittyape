package com.wittyape.android.classone;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wittyape.android.R;
import com.wittyape.android.TabActivity;

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

        Intent intent = new Intent(getActivity(), TabActivity.class);

        switch (view.getId()) {
            case R.id.button_counting_class_one:
                intent.putExtra("userClass", "countingone");
                break;
            case R.id.button_addition_class_one:
                intent.putExtra("userClass", "addone");
                break;
            case R.id.button_subtraction_class_one:
                intent.putExtra("userClass", "subtractone");
                break;
        }

        startActivity(intent);
    }

}
