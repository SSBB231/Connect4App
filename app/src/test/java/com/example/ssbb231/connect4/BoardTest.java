package com.example.ssbb231.connect4;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ssbb231 on 12/2/17.
 */

public class BoardTest {
    @Test
    public void isValidMove()
    {
        Board board = new Board();
        Assert.assertFalse(board.isValidMove(-1, 0));
    }

    @Test
    public void validMoveOnTaken()
    {
        Board board = new Board();
        board.putPiece(5, 0, new Piece());
        Assert.assertFalse(board.isValidMove(5, 0));
    }

    @Test
    public void validMoveOnTop()
    {
        Board board = new Board();
        board.putPiece(5, 0, new Piece());
        Assert.assertTrue(board.isValidMove(4, 0));
    }

    @Test
    public void utilityOnePiece()
    {
        Player human = new HumanPlayer();
        Board board = new Board();
        board.putPiece(5, 6, new Piece());
        Assert.assertEquals(10, board.utility(human));
    }

    @Test
    public void utilityRedWin()
    {
        Player human = new HumanPlayer();
        Board board = new Board();
        board.putPiece(5, 6, new Piece());
        board.putPiece(5, 5, new Piece());
        board.putPiece(5, 4, new Piece());
        board.putPiece(5, 3, new Piece());
        Assert.assertEquals(Integer.MAX_VALUE, board.utility(human));
    }

    @Test
    public void utilityBlackWin()
    {
        Player human = new HumanPlayer("Player 2", PieceType.BLACK, false);
        Board board = new Board();
        board.putPiece(5, 6, new Piece(PieceType.BLACK));
        board.putPiece(5, 5, new Piece(PieceType.BLACK));
        board.putPiece(5, 4, new Piece(PieceType.BLACK));
        board.putPiece(5, 3, new Piece(PieceType.BLACK));
        Assert.assertEquals(Integer.MIN_VALUE, board.utility(human));
    }
}
