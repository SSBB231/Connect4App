package com.example.ssbb231.connect4;

import junit.framework.Assert;

import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

/**
 * Created by ssbb231 on 12/10/17.
 */
public class MyMinmaxTest {

    @Test
    public void testMinMaxForWin()
    {
        Board board = new Board();
        Minmax minmax = new MM(5);

        int i = 0;
        while(!board.isWin()) {
            if (i % 2 == 0)
                board.putPiece(new Random().nextInt(7), PieceType.RED);
            else
                board.putPiece(minmax.getMove(board), PieceType.BLACK);
            System.out.println(board+"\n");
            i++;
        }
    }

    @Test
    public void testMinMaxForSmartMove()
    {
        Board board = new Board();
        Minmax minmax = new MyMinmax(2);

        for (int i = 0; i < 2; i++) {
            board.putPiece(i, PieceType.RED);
        }

        int col = minmax.getMove(board);

        Assert.assertEquals(4, col);
    }



}