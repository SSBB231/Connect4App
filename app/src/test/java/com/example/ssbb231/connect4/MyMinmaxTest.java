package com.example.ssbb231.connect4;

import junit.framework.Assert;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Created by ssbb231 on 12/10/17.
 */
public class MyMinmaxTest {

    @Test
    public void testMinMaxForWin()
    {
        Board board = new Board();
        Minmax minmax = new MyMinmax(7);

        for (int i = 0; i < 3; i++) {
            board.putPiece(i, PieceType.BLACK);
        }

        int col = minmax.getMove(board);

        Assert.assertEquals(3, col);
    }

    @Test
    public void testMinMaxForSmartMove()
    {
        Board board = new Board();
        Minmax minmax = new MyMinmax(7);

        for (int i = 0; i < 2; i++) {
            board.putPiece(i, PieceType.RED);
        }

        int col = minmax.getMove(board);

        Assert.assertEquals(2, col);
    }



}