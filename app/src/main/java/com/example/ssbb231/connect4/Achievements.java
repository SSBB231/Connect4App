package com.example.ssbb231.connect4;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by ssbb231 on 12/16/17.
 */

public class Achievements implements Observer
{

    @Override
    public void update(Observable observable, Object o) {

        if (o != null)
        {
            Achievement achievement = (Achievement)o;
        }
    }
}
