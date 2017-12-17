package com.example.ssbb231.connect4;

import android.view.View;

/**
 * Created by ssbb231 on 12/16/17.
 */

public class NewPlayerAchievement implements Achievement {

    private View view;
    private int imageID;
    private String newPlayer;

    public NewPlayerAchievement(View view, int imageID, String message) {
        this.view = view;
        this.imageID = imageID;
        this.newPlayer = message;
    }

    public NewPlayerAchievement(View view, String player) {
        this(view, R.drawable.ic_action_star, player);
    }

    @Override
    public View getView() {
        return view;
    }

    @Override
    public int getImageID() {
        return imageID;
    }

    @Override
    public String getMessage() {
        return newPlayer;
    }


}
