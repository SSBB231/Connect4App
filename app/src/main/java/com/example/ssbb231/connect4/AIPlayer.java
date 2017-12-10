package com.example.ssbb231.connect4;

import java.util.concurrent.ExecutorService;

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

        minmax = new MyMinmax(5);
    }

    @Override
    public void setMove(int move) {

    }

}
