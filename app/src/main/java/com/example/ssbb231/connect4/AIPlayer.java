package com.example.ssbb231.connect4;

import java.util.concurrent.ExecutorService;

/**
 * Created by ssbb231 on 12/10/17.
 */

public class AIPlayer extends Player {

    private Minmax minmax;
    private Board board;

    public AIPlayer(Board board, String name, int wins, int totalGamesPlayed, PieceType pieceType, int move, boolean player1) {
        super(name, wins, totalGamesPlayed, pieceType, move, player1);
        this.board = board;
        minmax = new TryMM1(7);
    }

    public AIPlayer(Board board)
    {
        super("AndroidX7", PieceType.BLACK);
        this.board = board;
        minmax = new TryMM1(7);
    }

    @Override
    public void setMove(int move) {
        this.move = minmax.getMove(board);
    }

}
