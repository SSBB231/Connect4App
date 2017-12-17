package com.example.ssbb231.connect4;

import android.content.Context;
import android.support.design.widget.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by ssbb231 on 12/16/17.
 */

public class Achievements implements Observer
{
    public List<Achievement> achievements;

    public Achievements()
    {
        this.achievements = new ArrayList<>();
    }

    @Override
    public void update(Observable observable, Object o) {

        if (o != null)
        {
            Achievement achievement = (Achievement)o;
            addAchievement(achievement);
            displaySnackBar(achievement);
        }
    }

    public void addAchievement(Achievement achievement)
    {
        if(achievements != null)
            achievements.add(achievement);
    }

    private void displaySnackBar(Achievement achievement)
    {
        Snackbar snackbar = Snackbar.make(achievement.getView(), achievement.getMessage(), Snackbar.LENGTH_SHORT);

    }
}
