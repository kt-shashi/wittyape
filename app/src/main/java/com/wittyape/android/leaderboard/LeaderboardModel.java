package com.wittyape.android.leaderboard;

public class LeaderboardModel implements Comparable<LeaderboardModel> {

    private String name;
    private String score;

    public LeaderboardModel() {

    }

    public LeaderboardModel(String name, String score) {
        this.name = name;
        this.score = score;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public int compareTo(LeaderboardModel user2) {
        if (Integer.parseInt(getScore()) > Integer.parseInt(user2.getScore()))
            return -1;
        if (Integer.parseInt(getScore()) < Integer.parseInt(user2.getScore()))
            return 1;
        return 0;
    }
}
