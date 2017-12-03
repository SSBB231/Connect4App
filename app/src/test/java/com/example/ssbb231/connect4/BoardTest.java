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
        Assert.assertFalse(board.isValidMove(4, 0));
    }

    @Test
    public void utilityOnePiece()
    {
        Player human = new HumanPlayer();
        Board board = new Board();
        board.putPiece(5, 6, new Piece());
        Assert.assertEquals(-500, board.utility(human));
    }
}
