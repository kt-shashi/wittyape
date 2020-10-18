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

public class EnglishClassTwoFragment extends Fragment implements View.OnClickListener {

    public EnglishClassTwoFragment() {

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_english_class_two, container, false);

        Button articles = view.findViewById(R.id.button_article_class_two);
        Button plurals = view.findViewById(R.id.button_plural_class_two);

        articles.setOnClickListener(this);
        plurals.setOnClickListener(this);

        return view;

    }

    @Override
    public void onClick(View view) {

        Intent intent = new Intent(getActivity(), TabActivity.class);

        switch (view.getId()) {
            case R.id.button_article_class_two:
                intent.putExtra("userClass", "twoarticle");
                break;
            case R.id.button_plural_class_two:
                intent.putExtra("userClass", "twoplurals");
                break;
        }

        startActivity(intent);
    }

}
