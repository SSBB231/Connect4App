package com.example.ssbb231.connect4;

/**
 * Created by ssbb231 on 12/1/17.
 */

public enum PieceType
{
    BLACK("B", -50, Integer.MIN_VALUE), RED("R", 50, Integer.MAX_VALUE), NONE("-", 0, 0);

    public final String face;
    public final int value;
    public final int winValue;

    PieceType(String face, int value, int winValue)
    {
        this.face = face;
        this.value = value;
        this.winValue = winValue;
    }
}
