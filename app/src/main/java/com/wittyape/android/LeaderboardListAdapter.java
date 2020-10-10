package com.wittyape.android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class LeaderboardListAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<LeaderboardModel> dataModelList;

    public LeaderboardListAdapter(Context context, ArrayList<LeaderboardModel> dataModelList) {
        this.context = context;
        this.dataModelList = dataModelList;
    }

    @Override
    public int getCount() {
        return dataModelList.size();
    }

    @Override
    public Object getItem(int i) {
        return dataModelList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View rootView = inflater.inflate(R.layout.list_item_leaderboard, null);

        TextView nameTextView = rootView.findViewById(R.id.item_name_leaderboard);
        TextView scoreTextView = rootView.findViewById(R.id.item_score_leaderboard);

        LeaderboardModel data = (LeaderboardModel) getItem(i);

        nameTextView.setText(data.getName());
        scoreTextView.setText(data.getScore());

        return rootView;
    }

}
