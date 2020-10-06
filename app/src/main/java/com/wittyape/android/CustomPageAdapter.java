package com.wittyape.android;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

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
