package com.wittyape.android;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class LearnMathsOneCount extends Fragment {

    public LearnMathsOneCount() {

    }

    private ListView listView;
    private String[] countingData;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.learnmathsonecount, container, false);

        getCountingData();
        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, countingData);

        listView = view.findViewById(R.id.list_view);
        listView.setAdapter(arrayAdapter);

        return view;

    }

    private void getCountingData() {

        countingData = new String[30];
        NumberWordConverter numberWordConverter = new NumberWordConverter();

        for (int countNumber = 0; countNumber < 30; countNumber++) {

            String word = numberWordConverter.convert(countNumber + 1);
            countingData[countNumber] = (countNumber + 1) + " -> " + word.toUpperCase();

        }

    }

    public class NumberWordConverter {
        public final String[] units = {
                "", "one", "two", "three", "four", "five", "six", "seven",
                "eight", "nine", "ten", "eleven", "twelve", "thirteen", "fourteen",
                "fifteen", "sixteen", "seventeen", "eighteen", "nineteen"
        };

        public final String[] tens = {
                "",        // 0
                "",        // 1
                "twenty",  // 2
                "thirty",  // 3
                "forty",   // 4
                "fifty",   // 5
                "sixty",   // 6
                "seventy", // 7
                "eighty",  // 8
                "ninety"   // 9
        };

        public String convert(final int n) {
            if (n < 0) {
                return "minus " + convert(-n);
            }

            if (n < 20) {
                return units[n];
            }

            if (n < 100) {
                return tens[n / 10] + ((n % 10 != 0) ? " " : "") + units[n % 10];
            }

            if (n < 1000) {
                return units[n / 100] + " hundred" + ((n % 100 != 0) ? " " : "") + convert(n % 100);
            }

            if (n < 1000000) {
                return convert(n / 1000) + " thousand" + ((n % 1000 != 0) ? " " : "") + convert(n % 1000);
            }

            if (n < 1000000000) {
                return convert(n / 1000000) + " million" + ((n % 1000000 != 0) ? " " : "") + convert(n % 1000000);
            }

            return convert(n / 1000000000) + " billion" + ((n % 1000000000 != 0) ? " " : "") + convert(n % 1000000000);
        }
    }
}
