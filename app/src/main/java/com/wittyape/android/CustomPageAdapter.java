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
import com.wittyape.android.classtwo.LearnMathsTwoCount;
import com.wittyape.android.classtwo.PracticeMathsTwoCount;

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

        } else {
            return null;
        }

    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
