package com.example.ssbb231.connect4;

/**
 * Created by ssbb231 on 12/2/17.
 */

public class HumanPlayer extends Player
{

    public HumanPlayer(String name, int wins, int totalGamesPlayed, PieceType pieceType, int move, boolean player1) {
        super(name, wins, totalGamesPlayed, pieceType, move, player1);
    }

    public HumanPlayer(String name, PieceType pieceType, boolean isFirst) {
        super(name, pieceType, isFirst);
    }

    public HumanPlayer()
    {
        super();
    }

    @Override
    public void setMove(int move) {
        this.move = move;
    }
}
