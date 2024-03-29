package com.example.ssbb231.connect4;

/**
 * Created by eyouabmesfin on 11/29/17.
 */

public class Piece {

    //field
    private PieceType pieceVal;

    public Piece(PieceType t)
    {
        this.pieceVal = t;
    }

    public Piece()
    {
        this(PieceType.NONE);
    }

    public PieceType getType()
    {
        return this.pieceVal;
    }

    public boolean equals(Piece p)
    {
        return this.pieceVal == p.pieceVal;
    }

    public void setType(PieceType t)
    {
        this.pieceVal = t;
    }

    public String toString()
    {
        return pieceVal.face;
    }
}
