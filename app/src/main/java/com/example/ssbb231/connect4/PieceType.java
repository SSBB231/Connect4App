package com.example.ssbb231.connect4;

/**
 * Created by ssbb231 on 12/1/17.
 */

public enum PieceType
{
    BLACK("B", -50, false), RED("R", 50, true), NONE("-", 0, false);

    public String face;
    public int value;
    public boolean first;

    PieceType(String face, int value, boolean first)
    {
        this.face = face;
        this.value = value;
        this.first = first;
    }
}
