package com.wittyape.android.leaderboard;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.wittyape.android.R;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardFragment extends Fragment {

    public LeaderboardFragment() {

    }

    ProgressBar progressBar;

    private ListView listView;
    LeaderboardListAdapter myListAdapter;
    ArrayList<LeaderboardModel> leaderboardArrayList;

    private FirebaseFirestore firebaseFirestore;
    private CollectionReference collectionReference;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        String className = getArguments().getString("class");

        firebaseFirestore = FirebaseFirestore.getInstance();
        collectionReference = firebaseFirestore.collection(className);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        progressBar = view.findViewById(R.id.progress_bar_leaderboard_class_one_menu);
        progressBar.setVisibility(View.VISIBLE);
        listView = view.findViewById(R.id.list_view_leaderboard_class_one);
        leaderboardArrayList = new ArrayList<>();

        initData();

        return view;
    }

    private void initData() {

        leaderboardArrayList.clear();

        collectionReference
                .get()
                .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                    @Override
                    public void onSuccess(QuerySnapshot queryDocumentSnapshots) {

                        for (QueryDocumentSnapshot snapshot : queryDocumentSnapshots) {
                            LeaderboardModel leader = snapshot.toObject(LeaderboardModel.class);
                            leaderboardArrayList.add(leader);
                        }

                        Collections.sort(leaderboardArrayList);

                        if (leaderboardArrayList.size() > 10)
                            leaderboardArrayList.subList(10, leaderboardArrayList.size()).clear();

                        progressBar.setVisibility(View.GONE);

                        myListAdapter = new LeaderboardListAdapter(getActivity(), leaderboardArrayList);
                        listView.setAdapter(myListAdapter);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getActivity(), "Could not load leaderboard at this moment", Toast.LENGTH_SHORT).show();
                        progressBar.setVisibility(View.GONE);
                    }
                });

    }

}
