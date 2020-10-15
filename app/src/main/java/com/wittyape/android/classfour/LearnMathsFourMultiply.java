package com.wittyape.android.classfour;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.wittyape.android.R;

import java.util.ArrayList;

public class LearnMathsFourMultiply extends Fragment implements SeekBar.OnSeekBarChangeListener {

    public LearnMathsFourMultiply() {

    }

    private TextView textView;
    private SeekBar seekBar;
    private ListView listView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.learnmathsfourmultiply, container, false);

        initViews(view);

        return view;
    }

    private void initViews(View view) {
        textView = view.findViewById(R.id.text_view_multiplication_table_header_four);
        listView = view.findViewById(R.id.list_view_multiplication_table_four);
        seekBar = view.findViewById(R.id.seek_bar_multiplication_four);

        seekBar.setMax(10);
        seekBar.setProgress(1);
        seekBar.setOnSeekBarChangeListener(this);
        onProgressChanged(seekBar, 1, true);
    }

    //Seekbar listeners
    @Override
    public void onProgressChanged(SeekBar seekBar, int i, boolean b) {

        ArrayList<String> tableList = new ArrayList<>();

        for (int loopCounter = 1; loopCounter <= 10; loopCounter++)
            tableList.add(i + " x " + loopCounter + " = " + (loopCounter * i));

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, tableList);
        listView.setAdapter(arrayAdapter);

        textView.setText("Table of " + i);

    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {

    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {

    }
}
