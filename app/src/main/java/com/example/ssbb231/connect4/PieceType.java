package com.example.ssbb231.connect4;

/**
 * Created by ssbb231 on 12/1/17.
 */

public enum PieceType
{
    BLACK("B", -50), RED("R", 50), NONE("-", 0);

    public String face;
    public int value;

    PieceType(String face, int value)
    {
        this.face = face;
        this.value = value;
    }
}
