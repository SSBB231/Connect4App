package com.example.ssbb231.connect4;

import java.io.Serializable;

/**
 * Created by ssbb231 on 12/2/17.
 */

public abstract class Player implements Serializable{

    protected String name;
    protected boolean player1;
    protected int wins;
    protected int totalGamesPlayed;
    protected PieceType pieceType;
    protected int move;

    public Player(String name, int wins, int totalGamesPlayed, PieceType pieceType, int move, boolean player1) {
        this.name = name;
        this.wins = wins;
        this.totalGamesPlayed = totalGamesPlayed;
        this.pieceType = pieceType;
        this.move = move;
        this.player1 = player1;
    }

    public Player(String name, PieceType pieceType, boolean isFirst)
    {
        this(name, 0, 0, pieceType, -1, isFirst);
    }

    public Player(String name, PieceType pieceType)
    {
        this(name, 0, 0, pieceType, -1, pieceType.first);
    }

    public Player()
    {
        this("Player 1", PieceType.RED, true);
    }

    public String getName() {
        return name;
    }

    public boolean isPlayer1() {
        return player1;
    }

    public void setPlayer1(boolean player1) {
        this.player1 = player1;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getWins() {
        return wins;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public int getTotalGamesPlayed() {
        return totalGamesPlayed;
    }

    public void setTotalGamesPlayed(int totalGamesPlayed) {
        this.totalGamesPlayed = totalGamesPlayed;
    }

    public PieceType getPieceType() {
        return pieceType;
    }

    public void setPieceType(PieceType pieceType) {
        this.pieceType = pieceType;
    }

    public int getMove() {
        return move;
    }

    public abstract void setMove(int move);

    public void incrementWins()
    {
        this.wins++;
    }
}
