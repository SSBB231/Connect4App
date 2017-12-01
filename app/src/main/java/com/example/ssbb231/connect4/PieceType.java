package com.example.ssbb231.connect4;

/**
 * Created by ssbb231 on 12/1/17.
 */

public enum PieceType
{
    BLACK("B"), RED("R"), NONE("-");

    public String face;

    PieceType(String face)
    {
        this.face = face;
    }
}
