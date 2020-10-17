package com.wittyape.android;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wittyape.android.classfour.LearnMathsFourAdd;
import com.wittyape.android.classfour.LearnMathsFourMultiply;
import com.wittyape.android.classfour.LearnMathsFourRoman;
import com.wittyape.android.classfour.LearnMathsFourSubtract;
import com.wittyape.android.classfour.PracticeMathsFourAdd;
import com.wittyape.android.classfour.PracticeMathsFourMultiply;
import com.wittyape.android.classfour.PracticeMathsFourRoman;
import com.wittyape.android.classfour.PracticeMathsFourSubtract;
import com.wittyape.android.classone.LearnEnglishOneAnimalBaby;
import com.wittyape.android.classone.LearnMathsOneAdd;
import com.wittyape.android.classone.LearnMathsOneCount;
import com.wittyape.android.classone.LearnMathsOneSubtract;
import com.wittyape.android.classone.PracticeEnglishFragment;
import com.wittyape.android.classone.PracticeMathsOneAdd;
import com.wittyape.android.classone.PracticeMathsOneCount;
import com.wittyape.android.classone.PracticeMathsOneSubtract;
import com.wittyape.android.classthree.LearnMathsThreeAdd;
import com.wittyape.android.classthree.LearnMathsThreeMultiply;
import com.wittyape.android.classthree.LearnMathsThreeRoman;
import com.wittyape.android.classthree.LearnMathsThreeSubtract;
import com.wittyape.android.classthree.PracticeMathsThreeAdd;
import com.wittyape.android.classthree.PracticeMathsThreeMultiply;
import com.wittyape.android.classthree.PracticeMathsThreeRoman;
import com.wittyape.android.classthree.PracticeMathsThreeSubtract;
import com.wittyape.android.classtwo.LearnMathsTwoAdd;
import com.wittyape.android.classtwo.LearnMathsTwoCount;
import com.wittyape.android.classtwo.LearnMathsTwoMultiply;
import com.wittyape.android.classtwo.LearnMathsTwoRoman;
import com.wittyape.android.classtwo.LearnMathsTwoSubtract;
import com.wittyape.android.classtwo.PracticeMathsTwoAdd;
import com.wittyape.android.classtwo.PracticeMathsTwoCount;
import com.wittyape.android.classtwo.PracticeMathsTwoMultiply;
import com.wittyape.android.classtwo.PracticeMathsTwoRoman;
import com.wittyape.android.classtwo.PracticeMathsTwoSubtract;
import com.wittyape.android.leaderboard.LeaderboardFragment;

class CustomPageAdapter extends FragmentPagerAdapter {

    private String userClass;
    private int tabCount;

    public CustomPageAdapter(@NonNull FragmentManager fm, int behavior, String userClass) {
        super(fm, behavior);

        this.userClass = userClass;
        tabCount = behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {

        if (userClass.equals("countingone")) {

            switch (position) {
                case 0:
                    return new LearnMathsOneCount();
                case 1:
                    return new PracticeMathsOneCount();
                default:
                    return null;
            }

        } else if (userClass.equals("addone")) {

            switch (position) {
                case 0:
                    return new LearnMathsOneAdd();
                case 1:
                    return new PracticeMathsOneAdd();
                default:
                    return null;
            }

        } else if (userClass.equals("subtractone")) {

            switch (position) {
                case 0:
                    return new LearnMathsOneSubtract();
                case 1:
                    return new PracticeMathsOneSubtract();
                default:
                    return null;
            }

        } else if (userClass.equals("countingtwo")) {

            switch (position) {
                case 0:
                    return new LearnMathsTwoCount();
                case 1:
                    return new PracticeMathsTwoCount();
                default:
                    return null;
            }

        } else if (userClass.equals("addtwo")) {

            switch (position) {
                case 0:
                    return new LearnMathsTwoAdd();
                case 1:
                    return new PracticeMathsTwoAdd();
                default:
                    return null;
            }

        } else if (userClass.equals("subtracttwo")) {

            switch (position) {
                case 0:
                    return new LearnMathsTwoSubtract();
                case 1:
                    return new PracticeMathsTwoSubtract();
                default:
                    return null;
            }

        } else if (userClass.equals("multiplytwo")) {

            switch (position) {
                case 0:
                    return new LearnMathsTwoMultiply();
                case 1:
                    return new PracticeMathsTwoMultiply();
                default:
                    return null;
            }

        } else if (userClass.equals("romantwo")) {

            switch (position) {
                case 0:
                    return new LearnMathsTwoRoman();
                case 1:
                    return new PracticeMathsTwoRoman();
                default:
                    return null;
            }

        } else if (userClass.equals("addthree")) {

            switch (position) {
                case 0:
                    return new LearnMathsThreeAdd();
                case 1:
                    return new PracticeMathsThreeAdd();
                default:
                    return null;
            }

        } else if (userClass.equals("subtractthree")) {

            switch (position) {
                case 0:
                    return new LearnMathsThreeSubtract();
                case 1:
                    return new PracticeMathsThreeSubtract();
                default:
                    return null;
            }

        } else if (userClass.equals("multiplythree")) {

            switch (position) {
                case 0:
                    return new LearnMathsThreeMultiply();
                case 1:
                    return new PracticeMathsThreeMultiply();
                default:
                    return null;
            }

        } else if (userClass.equals("romanthree")) {

            switch (position) {
                case 0:
                    return new LearnMathsThreeRoman();
                case 1:
                    return new PracticeMathsThreeRoman();
                default:
                    return null;
            }

        } else if (userClass.equals("addfour")) {

            switch (position) {
                case 0:
                    return new LearnMathsFourAdd();
                case 1:
                    return new PracticeMathsFourAdd();
                default:
                    return null;
            }

        } else if (userClass.equals("subtractfour")) {

            switch (position) {
                case 0:
                    return new LearnMathsFourSubtract();
                case 1:
                    return new PracticeMathsFourSubtract();
                default:
                    return null;
            }

        } else if (userClass.equals("multiplyfour")) {

            switch (position) {
                case 0:
                    return new LearnMathsFourMultiply();
                case 1:
                    return new PracticeMathsFourMultiply();
                default:
                    return null;
            }

        } else if (userClass.equals("romanfour")) {

            switch (position) {
                case 0:
                    return new LearnMathsFourRoman();
                case 1:
                    return new PracticeMathsFourRoman();
                default:
                    return null;
            }

        } else if (userClass.equals("onebabies")) {

            switch (position) {
                case 0:
                    return new LearnEnglishOneAnimalBaby();
                case 1:
                    PracticeEnglishFragment practiceEnglishFragment = new PracticeEnglishFragment();

                    Bundle arguments = new Bundle();
                    arguments.putString("dbname", "onebabies");
                    practiceEnglishFragment.setArguments(arguments);

                    return practiceEnglishFragment;
                default:
                    return null;
            }

        } else if (userClass.equals("oneopposite")) {

            switch (position) {
                case 0:
                    return new LearnEnglishOneAnimalBaby();
                case 1:
                    PracticeEnglishFragment practiceEnglishFragment = new PracticeEnglishFragment();

                    Bundle arguments = new Bundle();
                    arguments.putString("dbname", "oneopposite");
                    practiceEnglishFragment.setArguments(arguments);

                    return practiceEnglishFragment;
                default:
                    return null;
            }

        } else if (userClass.equals("oneadjectives")) {

            switch (position) {
                case 0:
                    return new LearnEnglishOneAnimalBaby();
                case 1:
                    PracticeEnglishFragment practiceEnglishFragment = new PracticeEnglishFragment();

                    Bundle arguments = new Bundle();
                    arguments.putString("dbname", "oneadjectives");
                    practiceEnglishFragment.setArguments(arguments);

                    return practiceEnglishFragment;
                default:
                    return null;
            }

        } else {
            return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
