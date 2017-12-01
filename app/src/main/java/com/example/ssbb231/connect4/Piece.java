package com.example.ssbb231.connect4;

/**
 * Created by eyouabmesfin on 11/29/17.
 */

public class Piece {

    public enum PieceType {
        BLACK,
        RED;
    }
    //field
    public PieceType pieceVal;

    public PieceType getType() {
        return this.pieceVal;
    }
    public String toString(){
        return "";
    }
}
