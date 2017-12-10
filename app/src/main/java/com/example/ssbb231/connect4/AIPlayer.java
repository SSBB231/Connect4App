package com.example.ssbb231.connect4;

/**
 * Created by ssbb231 on 12/10/17.
 */

public class AIPlayer extends Player {

    private Minmax minmax;

    public AIPlayer(String name, int wins, int totalGamesPlayed, PieceType pieceType, int move, boolean player1) {
        super(name, wins, totalGamesPlayed, pieceType, move, player1);
    }

    public AIPlayer()
    {
        super("Computer", PieceType.BLACK);
    }

    @Override
    public void setMove(int move) {

    }

}
