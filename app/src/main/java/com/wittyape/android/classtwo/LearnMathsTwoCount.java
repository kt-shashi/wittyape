package com.wittyape.android.classtwo;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wittyape.android.R;
import com.wittyape.android.helperclasses.NumberWordConverter;

public class LearnMathsTwoCount extends Fragment {

    public LearnMathsTwoCount() {

    }

    private ListView listView;
    private String[] countingData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.learnmathstwocount, container, false);

        getCountingData();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, countingData);

        listView = view.findViewById(R.id.list_view_count_two);
        listView.setAdapter(arrayAdapter);

        return view;

    }

    private void getCountingData() {

        countingData = new String[100];
        NumberWordConverter numberWordConverter = new NumberWordConverter();

        for (int countNumber = 0; countNumber < 100; countNumber++) {

            String word = numberWordConverter.convert(countNumber + 1);
            countingData[countNumber] = (countNumber + 1) + " -> " + word.toUpperCase();

        }

    }
}

