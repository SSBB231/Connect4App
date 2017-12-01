package com.example.ssbb231.connect4;

/**
 * Created by eyouabmesfin on 11/29/17.
 */

public class Piece {

    //field
    private PieceType pieceVal;

    public PieceType getType()
    {
        return this.pieceVal;
    }

    public String toString()
    {
        if(this.pieceVal == PieceType.BLACK)
        {
            return "B";
        }
        else if(this.pieceVal == PieceType.RED)
        {
            return "R";
        }
        else
        {
            return "-";
        }
    }
}
