package com.example.ssbb231.connect4;

/**
 * Created by ssbb231 on 12/16/17.
 */

public class AIGame implements Game {

    private Player first;
    private Player second;

    private Player currentPlayer;

    private Board board;

    public AIGame(String first)
    {
        this.board = new Board();
        this.first = new HumanPlayer(first, PieceType.RED);
        this.second = new AIPlayer(board);

        this.currentPlayer = this.first;
    }

    @Override
    public boolean isOver() {
        return board.isWin() || board.isFull();
    }

    @Override
    public void setPlayer1(Player player1) {
        this.first = player1;
    }

    @Override
    public void setPlayer2(Player player2) {
        this.second = player2;
    }

    @Override
    public Player getPlayer1() {
        return first;
    }

    @Override
    public Player getPlayer2() {
        return second;
    }

    @Override
    public void incrementNumberOfWins(Player player) {
        player.incrementWins();
    }

    @Override
    public void incrementNumberOfGames(Player plays) {
        plays.setTotalGamesPlayed(plays.getTotalGamesPlayed()+1);
    }

    @Override
    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    @Override
    public void switchPlayers() {
        if(currentPlayer.getPieceType() == PieceType.RED)
            currentPlayer = second;
        else
            currentPlayer = first;
    }

    @Override
    public int putPieceForCurrentPlayer(int col) {
        currentPlayer.setMove(col);
        int madeMove = board.putPieceTypeAtCol(currentPlayer.getMove(), currentPlayer.getPieceType());

        return madeMove;
    }

    @Override
    public Board getBoard() {
        return board;
    }

    @Override
    public String getBoardString() {
        return board.toString();
    }
}
