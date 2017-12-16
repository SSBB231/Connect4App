package com.example.ssbb231.connect4;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by ssbb231 on 12/10/17.
 */
public class MyMinmaxTest {

    @Test
    public void testMinMaxForWin()
    {
        Board board = new Board();
        Minmax minmax = new TryMM1(7);
        Minmax minmax2 = new TryMM_MAX(7);

        int i = 6;
        while(!board.isWin() && !board.isFull()) {
            if (i % 2 == 0) {
//                System.out.println("------------- P1 Makes Move: " + i/2 + "----------------------");
                board.putPieceTypeAtCol(minmax2.getMove(board), PieceType.RED);
            }
            else
                board.putPieceTypeAtCol(minmax.getMove(board), PieceType.BLACK);
            System.out.println(board+"\n");
            i--;

            if(i < 0)
                i = 7;
        }

        if(i % 2 == 1)
        {
            System.out.println("Winner is P1");
        }
        else
        {
            System.out.println("Winner is P2");
        }
    }

    @Test
    public void testMinMaxForSmartMove()
    {
        Board board = new Board();
        Minmax minmax = new TryMM1(7);

        for (int i = 0; i < 3; i++) {
            board.putPieceTypeAtCol(i, PieceType.RED);
        }

        int col = minmax.getMove(board);

        Assert.assertEquals(3, col);
    }



}