package com.wittyape.android;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.wittyape.android.classone.LearnMathsOneAdd;
import com.wittyape.android.classone.LearnMathsOneCount;
import com.wittyape.android.classone.LearnMathsOneSubtract;
import com.wittyape.android.classone.PracticeMathsOneAdd;
import com.wittyape.android.classone.PracticeMathsOneCount;
import com.wittyape.android.classone.PracticeMathsOneSubtract;
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

        } else {
            return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
