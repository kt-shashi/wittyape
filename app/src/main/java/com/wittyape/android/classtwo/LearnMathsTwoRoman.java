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
import com.wittyape.android.helperclasses.RomanNumber;

public class LearnMathsTwoRoman extends Fragment {

    public LearnMathsTwoRoman() {

    }

    private ListView listView;
    private String[] countingData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.learnmathstworoman, container, false);

        getRomanData();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, countingData);

        listView = view.findViewById(R.id.list_view_roman_two);
        listView.setAdapter(arrayAdapter);

        return view;

    }

    private void getRomanData() {

        countingData = new String[20];

        for (int romanNumber = 0; romanNumber < 20; romanNumber++) {

            String word = RomanNumber.toRoman(romanNumber + 1);
            countingData[romanNumber] = (romanNumber + 1) + " -> " + word.toUpperCase();

        }

    }

}
