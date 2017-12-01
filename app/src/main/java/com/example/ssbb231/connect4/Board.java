package com.example.ssbb231.connect4;

/**
 * Created by eyouabmesfin on 11/29/17.
 */

public class Board {
    //field
    private Piece board[][]; // 6X7

    //methods
    public Board() { //constructor
        this.board = new Piece[6][7];
    }

    public void putPiece(int row, int col, Piece p) {

    }

    public Piece removePiece(int row, int col) {
        return null;
    }

    public Piece[][] getBoard() {
        return this.board;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                builder.append(String.format("%3s ", board[i][j].toString()));
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public boolean isValidMove(int row, int col)
    {
        if(!withinBounds(row, col))
            return false;
        else if(board[row][col].getType() != PieceType.NONE)
        {
            return false;
        }
        else if(row < board.length-1 && board[row+1][col].getType() == PieceType.NONE)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean withinBounds(int row, int col)
    {
        return (row >= 0 && row < board.length) && (col >= 0 && col < board[0].length);
    }

    public boolean isWin() {
        return false;
    }

    public int utility() {
        return 0;
    }
}
