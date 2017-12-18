package com.example.ssbb231.connect4;

import android.content.Context;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

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
    Context context;

    public Achievements(Context context)
    {
        this.context = context;
        this.achievements = new ArrayList<>();
    }

    @Override
    public void update(Observable observable, Object o) {

//        Toast.makeText(context, "Displaying Snackbar", Toast.LENGTH_SHORT).show();

        if (o != null)
        {
            Achievement achievement = (Achievement)o;
            addAchievement(achievement);
            displaySnackBar(achievement);
        }
        else
        {
//            Toast.makeText(context, "WAS NULL", Toast.LENGTH_SHORT).show();
        }
    }

    public void addAchievement(Achievement achievement)
    {
        if(achievements != null)
            achievements.add(achievement);
    }

    private void displaySnackBar(Achievement achievement)
    {
//        Toast.makeText(context, "Displaying Snackbar", Toast.LENGTH_SHORT).show();
        Snackbar snackbar = Snackbar.make(achievement.getView(), "   "+achievement.getMessage(), Snackbar.LENGTH_LONG);
        View snackbarLayout = snackbar.getView();
        TextView textView = (TextView)snackbarLayout.findViewById(android.support.design.R.id.snackbar_text);
        textView.setCompoundDrawablesRelativeWithIntrinsicBounds(R.drawable.ic_action_star, 0, 0, 0);
        snackbar.show();
    }
}
