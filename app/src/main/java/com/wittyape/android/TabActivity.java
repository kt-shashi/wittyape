package com.wittyape.android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;

import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;

public class TabActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private TabItem tabItemLearn, tabItemPractice;
    private ViewPager viewPager;

    private CustomPageAdapter customPageAdapter;

    private String userClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab);

        initViews();

        userClass = getIntent().getStringExtra("userClass");

        if (userClass == null || userClass.isEmpty()) {
            Log.d("shashi", "Null/Empty");
            userClass = "countingone";
        }

        customPageAdapter = new CustomPageAdapter(getSupportFragmentManager(), tabLayout.getTabCount(), userClass);
        viewPager.setAdapter(customPageAdapter);

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewPager.setCurrentItem(tab.getPosition());

                if (tab.getPosition() == 0 || tab.getPosition() == 1) {
                    customPageAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));

    }

    private void initViews() {
        tabLayout = findViewById(R.id.tab_layout_tab_activity);
        tabItemLearn = findViewById(R.id.tab_learn_tab_activity);
        tabItemPractice = findViewById(R.id.tab_practice_tab_activity);
        viewPager = findViewById(R.id.view_pager_tab_activity);
    }
}