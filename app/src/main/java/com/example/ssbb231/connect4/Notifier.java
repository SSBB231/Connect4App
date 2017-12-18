package com.example.ssbb231.connect4;

import java.util.Observable;

/**
 * Created by ssbb231 on 12/17/17.
 */

public class Notifier extends Observable {

    public void changed()
    {
        setChanged();
    }

}
