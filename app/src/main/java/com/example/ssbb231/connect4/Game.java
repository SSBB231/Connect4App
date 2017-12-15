package com.example.ssbb231.connect4;

/**
 * Created by ssbb231 on 12/14/17.
 */

public interface Game
{
    boolean isOver();
    void setPlayer1(Player player1);
    void setPlayer2(Player player2);
    void incrementNumberOfWins(Player player);
    void incrementNumberOfGames(Player plays);
    Player getCurrentPlayer();
    void switchPlayers();
    void putPieceForCurrentPlayer(int col);
    Board getBoard();
    String getString();
}
