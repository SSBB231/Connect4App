package com.example.ssbb231.connect4;

/**
 * Created by eyouabmesfin on 11/29/17.
 */

public class Board {
    //field
    private Piece board[][]; // 6X7

    //methods
    public Board(){ //constructor
        this.board = new Piece[6][7];
    }
    public void putPiece(int row, int col, Piece p){

    }
    public Piece removePiece(int row, int col){
        return null;
    }
    public Piece[][] getBoard(){
        return this.board;
    }
    public String toString(){
        return "";
    }
    public boolean isValidMove(int row, int col){
        return false;
    }
    public boolean isWin(){
        return false;
    }
    public int utility(){
        return 0;
    }
}
