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
        Minmax minmax = new MM(9);

        int i = 0;
        while(!board.isWin() && !board.isFull()) {
            if (i % 2 == 0)
                board.putPieceTypeAtCol(i/2, PieceType.RED);
            else
                board.putPieceTypeAtCol(minmax.getMove(board), PieceType.BLACK);
            System.out.println(board+"\n");
            i++;
            i%=8;
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
        Minmax minmax = new MyMinmax(2);

        for (int i = 0; i < 2; i++) {
            board.putPieceTypeAtCol(i, PieceType.RED);
        }

        int col = minmax.getMove(board);

        Assert.assertEquals(4, col);
    }



}