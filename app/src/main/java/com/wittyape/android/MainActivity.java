package com.wittyape.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar);

        initViews();

        toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        //Set Home Fragment by default
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_main_activity, new HomeClassOneFragment())
                .commit();
    }

    private void initViews() {
        navigationView = findViewById(R.id.naviagtion_drawer_main_activity);
        drawerLayout = findViewById(R.id.drawer_layout_main_activity);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Fragment fragment;

        switch (item.getItemId()) {

            case R.id.menu_item_home:
                fragment = new HomeClassOneFragment();
                break;

            case R.id.menu_item_maths:
                fragment = new MathsClassOneFragment();
                break;

            case R.id.menu_item_help:
                fragment = new HelpFeedbackFragment();
                break;

            case R.id.menu_item_exit:
                finish();

            default:
                fragment = new HomeClassOneFragment();
        }

        //setUp fragment
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.frame_layout_main_activity, fragment)
                .commit();

        //To automatically close the nag
        drawerLayout.closeDrawer(GravityCompat.START);

        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_main, menu);

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int menuId = item.getItemId();       //get the id if menu

        switch (menuId) {
            case R.id.menu_item_profile:
                Toast.makeText(this, "Profile", Toast.LENGTH_SHORT).show();
                break;
            case R.id.menu_item_logout:
                Toast.makeText(this, "Logout", Toast.LENGTH_SHORT).show();
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}