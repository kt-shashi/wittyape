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

public class EnglishClassFourFragment extends Fragment implements View.OnClickListener {

    public EnglishClassFourFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_english_class_four, container, false);

        Button verb = view.findViewById(R.id.button_verbs_class_four);
        Button comparision = view.findViewById(R.id.button_comparision_class_four);

        verb.setOnClickListener(this);
        comparision.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getActivity(), TabActivity.class);

        switch (view.getId()) {
            case R.id.button_verbs_class_four:
                intent.putExtra("userClass", "fourverbs");
                break;
            case R.id.button_comparision_class_four:
                intent.putExtra("userClass", "fourdegree");
                break;
        }

        startActivity(intent);
    }

}
