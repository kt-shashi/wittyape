package com.wittyape.android;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.wittyape.android.classone.HomeClassOneFragment;
import com.wittyape.android.classone.MathsClassOneFragment;
import com.wittyape.android.classtwo.HomeClassTwoFragment;
import com.wittyape.android.classtwo.MathsClassTwoFragment;
import com.wittyape.android.helpfeedback.AboutFragment;
import com.wittyape.android.helpfeedback.ScoringSystem;
import com.wittyape.android.helpfeedback.TermsConditionFragment;
import com.wittyape.android.leaderboard.LeaderboardFragment;
import com.wittyape.android.login.LoginActivity;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, AdapterView.OnItemSelectedListener {

    private static final String TAG = "debug_shashi";
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    DrawerLayout drawerLayout;

    ProgressBar progressBar;

    TextInputLayout textInputLayoutUserName;
    Spinner spinnerClass;
    String spinnerClassData = "";

    ProgressBar progressBarUpdate;
    ProgressBar progressBarLogout;

    private FirebaseAuth firebaseAuth;
    private FirebaseAuth.AuthStateListener authStateListener;
    private FirebaseFirestore firebaseFirestore;

    private String userClass = "";

    public static final String COLLECTION_NAME = "users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar_main_activity);
        setSupportActionBar(toolbar);

        initViews();

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();
        authStateListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                getUserClass();
            }
        };

        toggle = new ActionBarDrawerToggle(this,
                drawerLayout,
                toolbar,
                R.string.open, R.string.close);
        toggle.getDrawerArrowDrawable().setColor(getResources().getColor(R.color.colorPrimary));
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

    }

    private void getUserClass() {
        progressBar.setVisibility(View.VISIBLE);

        FirebaseUser user = firebaseAuth.getCurrentUser();

        if (user != null) {

            firebaseFirestore.collection(COLLECTION_NAME)
                    .document(firebaseAuth.getCurrentUser().getUid())
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                        @Override
                        public void onSuccess(DocumentSnapshot documentSnapshot) {

                            //Check if the document exists
                            if (documentSnapshot.exists()) {

                                String getUserClassFromDatabase = documentSnapshot.getString("class");

                                if (!getUserClassFromDatabase.isEmpty()) {
                                    userClass = getUserClassFromDatabase;
                                }

                                Log.d(TAG, "onSuccess: " + userClass);

                                progressBar.setVisibility(View.GONE);

                                if (userClass.equals("Class1")) {
                                    //Set Home LeaderboardFragment by default
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.frame_layout_main_activity, new HomeClassOneFragment())
                                            .commit();
                                } else if (userClass.equals("Class2")) {
                                    //Set Home LeaderboardFragment by default
                                    getSupportFragmentManager()
                                            .beginTransaction()
                                            .replace(R.id.frame_layout_main_activity, new HomeClassTwoFragment())
                                            .commit();
                                }

                            } else {
                                progressBar.setVisibility(View.GONE);
                                Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                            }
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressBar.setVisibility(View.GONE);
                            Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                        }
                    });

        }
    }

    private void initViews() {
        navigationView = findViewById(R.id.naviagtion_drawer_main_activity);
        drawerLayout = findViewById(R.id.drawer_layout_main_activity);

        progressBar = findViewById(R.id.progress_bar_main);
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        Log.d(TAG, "onNavigationItemSelected: In change fragment");

        Fragment fragment = null;

        if (userClass.equals("Class1")) {

            switch (item.getItemId()) {

                case R.id.menu_item_home:
                    fragment = new HomeClassOneFragment();
                    break;

                case R.id.menu_item_maths:
                    fragment = new MathsClassOneFragment();
                    break;

                case R.id.menu_item_leaderboard:
                    fragment = setLeaderBoardFragment();
                    break;

                case R.id.menu_item_scoring_system:
                    fragment = new ScoringSystem();
                    break;

                case R.id.menu_item_about:
                    fragment = new AboutFragment();
                    break;

                case R.id.menu_item_terms_condition:
                    fragment = new TermsConditionFragment();
                    break;

                case R.id.menu_item_exit:
                    finish();

            }

        } else if (userClass.equals("Class2")) {

            switch (item.getItemId()) {

                case R.id.menu_item_home:
                    fragment = new HomeClassTwoFragment();
                    break;

                case R.id.menu_item_maths:
                    fragment = new MathsClassTwoFragment();
                    break;

                case R.id.menu_item_leaderboard:
                    fragment = setLeaderBoardFragment();
                    break;

                case R.id.menu_item_scoring_system:
                    fragment = new ScoringSystem();
                    break;

                case R.id.menu_item_about:
                    fragment = new AboutFragment();
                    break;

                case R.id.menu_item_terms_condition:
                    fragment = new TermsConditionFragment();
                    break;

                case R.id.menu_item_exit:
                    finish();

            }

        }

        if (fragment == null) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_SHORT).show();
            return true;
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

    private Fragment setLeaderBoardFragment() {
        LeaderboardFragment leaderboardFragment = new LeaderboardFragment();
        Bundle arguments = new Bundle();

        if (userClass.equals("Class1")) {
            arguments.putString("class", "scoreclass1");
        } else if (userClass.equals("Class2")) {
            arguments.putString("class", "scoreclass2");
        }

        leaderboardFragment.setArguments(arguments);

        return leaderboardFragment;
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
                editUserProfile();
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    private void editUserProfile() {

        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View layoutView = layoutInflater.inflate(R.layout.profile_dialog_layout, null);

        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(this).setView(layoutView);
        alertDialog.setCancelable(true);
        final AlertDialog testDialog = alertDialog.create();
        testDialog.show();

        textInputLayoutUserName = layoutView.findViewById(R.id.text_input_layout_profile_main);
        spinnerClass = layoutView.findViewById(R.id.spinner_class_selector_update);
        Button buttonUpdate = layoutView.findViewById(R.id.button_update_name_profile);
        Button buttonLogout = layoutView.findViewById(R.id.button_sign_out_main);
        progressBarUpdate = layoutView.findViewById(R.id.progress_bar_profile_update);
        progressBarLogout = layoutView.findViewById(R.id.progress_bar_profile_sign_out);

        progressBarUpdate.setVisibility(View.GONE);
        progressBarLogout.setVisibility(View.GONE);

        getUserName();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String newTask = textInputLayoutUserName.getEditText().getText().toString();

                if (newTask.isEmpty()) {
                    textInputLayoutUserName.setError("Cannot be empty");
                    return;
                } else {
                    textInputLayoutUserName.setError(null);
                }

                if (spinnerClassData.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Choose your class", Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBarUpdate.setVisibility(View.VISIBLE);

                String userClassChanged = spinnerClassData;

                saveNewData(newTask, userClassChanged);
                testDialog.dismiss();  // to dismiss

            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                logoutUser();
            }
        });

        spinnerClass.setOnItemSelectedListener(this);

    }

    private void saveNewData(String newUserName, String userClassChanged) {

        Map<String, Object> data = new HashMap<>();
        data.put("name", newUserName);
        data.put("class", userClassChanged);

        CollectionReference collectionReference = firebaseFirestore.collection(COLLECTION_NAME);

        collectionReference
                .document(firebaseAuth.getCurrentUser().getUid())
                .update(data)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {

                        progressBarUpdate.setVisibility(View.GONE);
                        LinearLayout linearLayout = findViewById(R.id.linear_layout_main_activity);
                        Snackbar snackbar = Snackbar.make(linearLayout, "Updated", Snackbar.LENGTH_LONG);
                        snackbar.show();

                        getUserClass();

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        progressBarUpdate.setVisibility(View.GONE);
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void getUserName() {

        CollectionReference collectionReference = firebaseFirestore.collection(COLLECTION_NAME);

        collectionReference.document(firebaseAuth.getCurrentUser().getUid()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {

                        if (documentSnapshot.exists()) {

                            String name = documentSnapshot.getString("name");
                            textInputLayoutUserName.getEditText().setText(name);

                            spinnerClassData = documentSnapshot.getString("class");

                            if (spinnerClassData.equals("Class1")) {
                                spinnerClass.setSelection(1);
                            } else if (spinnerClassData.equals("Class2")) {
                                spinnerClass.setSelection(2);
                            }

                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(MainActivity.this, "Something went wrong", Toast.LENGTH_SHORT).show();
                    }
                });

    }

    private void logoutUser() {
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(MainActivity.this, LoginActivity.class));
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();

        firebaseAuth.addAuthStateListener(authStateListener);

    }

    @Override
    protected void onStop() {
        super.onStop();

        if (authStateListener != null) {
            firebaseAuth.removeAuthStateListener(authStateListener);
        }

    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (adapterView.getSelectedItem().toString().equals("Select")) {
            spinnerClassData = "";
            return;
        }

        spinnerClassData = adapterView.getSelectedItem().toString();
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}