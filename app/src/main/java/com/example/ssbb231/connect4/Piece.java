package com.example.ssbb231.connect4;

/**
 * Created by eyouabmesfin on 11/29/17.
 */

public class Piece {

    //field
    private PieceType pieceVal;
    //constructors
    public Piece(){
        this.pieceVal = PieceType.NONE;
    }
    public Piece(PieceType p){
        this.pieceVal = p;
    }
    public PieceType getType()
    {
        return this.pieceVal;
    }
    public boolean equals(Piece p){
        if(this.getType().equals(p.getType())){
            return true;
        }
        return false;
    }
    public String toString()
    {
        return pieceVal.face;
    }
}
