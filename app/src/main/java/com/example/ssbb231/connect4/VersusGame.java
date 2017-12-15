package com.example.ssbb231.connect4;

/**
 * Created by ssbb231 on 12/14/17.
 */

public class VersusGame implements Game{

    private Board board;
    private Player player1;
    private Player player2;

    private Player currentPlayer;

    public VersusGame(Board board, Player player1, Player player2) {
        this.board = board;
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
    }

    public VersusGame(Player player1, Player player2) {
        this(new Board(), player1, player2);
    }

    public VersusGame()
    {
        this(new HumanPlayer("P1", PieceType.RED), new HumanPlayer("P2", PieceType.BLACK));
    }

    public Board getBoard() {
        return board;
    }

    @Override
    public String getString() {
        return board.toString();
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Player getPlayer1() {
        return player1;
    }

    public Player getPlayer2() {
        return player2;
    }

    @Override
    public boolean isOver() {
        return board.isWin() || board.isFull();
    }

    @Override
    public void setPlayer1(Player player1) {
        this.player1 = player1;
    }

    @Override
    public void setPlayer2(Player player2) {
        this.player2 = player2;
    }

    @Override
    public void incrementNumberOfWins(Player player) {
        player.incrementWins();
    }

    @Override
    public void incrementNumberOfGames(Player player) {
        player.setTotalGamesPlayed(player.getTotalGamesPlayed()+1);
    }

    @Override
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    @Override
    public void switchPlayers() {
        if(currentPlayer.getPieceType() == PieceType.BLACK)
            currentPlayer = player1;
        else
            currentPlayer = player2;
    }

    @Override
    public void putPieceForCurrentPlayer(int col) {
        if (!this.isOver()) {
            board.putPieceTypeAtCol(col, currentPlayer.getPieceType());
        }
    }
}
