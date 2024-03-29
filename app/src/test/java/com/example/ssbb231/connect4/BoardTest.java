package com.example.ssbb231.connect4;

import junit.framework.Assert;

import org.junit.Test;

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
        board.putPiece(5, 0, new Piece(PieceType.RED));
        Assert.assertFalse(board.isValidMove(5, 0));
    }



    @Test
    public void testGreater()
    {
        Player first = new HumanPlayer("P1", PieceType.RED);
        Player second = new HumanPlayer("P2", PieceType.BLACK);

        Board board = new Board();
        board.putPieceTypeAtCol(0, first.getPieceType());

        int less = board.utility(first);

        board.putPieceTypeAtCol(1, first.getPieceType());
        int more = board.utility(first);

        board.putPieceTypeAtCol(0, second.getPieceType());

        System.out.println("Less: " + less);
        System.out.println("More: " + more);


        Assert.assertFalse(less == more);
    }

    @Test
    public void testWin()
    {
        Player first = new HumanPlayer("P1", PieceType.RED);
        Player second = new HumanPlayer("P2", PieceType.BLACK);

        Board board = new Board();

        board.putPieceTypeAtCol(0, first.getPieceType());
        board.putPieceTypeAtCol(0, second.getPieceType());

        board.putPieceTypeAtCol(1, first.getPieceType());
        board.putPieceTypeAtCol(4, second.getPieceType());

        board.putPieceTypeAtCol(2, first.getPieceType());
        board.putPieceTypeAtCol(4, second.getPieceType());

        board.putPieceTypeAtCol(3, first.getPieceType());

        System.out.println(board);

    }

    @Test
    public void invalidMoveInAir()
    {
        Board board = new Board();
        board.putPiece(5, 0, new Piece());
        Assert.assertFalse(board.isValidMove(3, 0));
    }

    @Test
    public void putPieceByCol1()
    {
        Board board = new Board();
        for (int i = 0; i < 10; i++) {
            board.putPieceTypeAtCol(0, PieceType.RED);
        }
        board.putPieceTypeAtCol(1, PieceType.RED);
        Assert.assertEquals(PieceType.RED, board.getBoard()[5][0].getType());
        Assert.assertEquals(PieceType.RED, board.getBoard()[4][0].getType());
        Assert.assertEquals(PieceType.RED, board.getBoard()[5][1].getType());
    }

    @Test
    public void putInAllCols()
    {
        Board board = new Board();
        for (int i = 0; i < board.getNumCols(); i++) {
            board.putPieceTypeAtCol(i, PieceType.RED);
        }
        board.putPieceTypeAtCol(1, PieceType.RED);
        for (int i = 0; i < board.getNumCols(); i++) {
            Assert.assertEquals(PieceType.RED, board.getBoard()[board.getNumRows()-1][i].getType());
        }

        Assert.assertEquals(PieceType.RED, board.getBoard()[4][1].getType());
    }

    @Test
    public void validMoveOnTop()
    {
        Board board = new Board();
        board.putPiece(5, 0, new Piece(PieceType.RED));
        Assert.assertTrue(board.isValidMove(4, 0));
    }

    @Test
    public void check4InARow()
    {
        Board board = new Board();
        Player first, second;

        first = new HumanPlayer("P1", PieceType.RED);
        second = new HumanPlayer("P2", PieceType.BLACK);

        int i;
        for (i = 0; i < 4; i++) {
            board.putPieceTypeAtCol(i, first.getPieceType());
        }

        Assert.assertTrue(board.check4Win(5, i-1, first.getPieceType()));
    }

    @Test
    public void check4InARowBrokenByBlack()
    {
        Board board = new Board();
        Player first, second;

        first = new HumanPlayer("P1", PieceType.RED);
        second = new HumanPlayer("P2", PieceType.BLACK);

        int i;
        for (i = 0; i < 3; i++) {
            board.putPieceTypeAtCol(i, first.getPieceType());
        }

        board.putPieceTypeAtCol(3, second.getPieceType());
        board.putPieceTypeAtCol(5, first.getPieceType());

        Assert.assertFalse(board.check4Win(5, board.getNumRows()-1, first.getPieceType()));
    }

    @Test
    public void checkWinBrokenByBlackHorizonatl()
    {
        Board board = new Board();
        Player first, second;

        first = new HumanPlayer("P1", PieceType.RED);
        second = new HumanPlayer("P2", PieceType.BLACK);

        int i;
        for (i = 0; i < 3; i++) {
            board.putPieceTypeAtCol(i, first.getPieceType());
        }

        board.putPieceTypeAtCol(3, second.getPieceType());
        board.putPieceTypeAtCol(5, first.getPieceType());

        Assert.assertFalse(board.checkWin(5, board.getNumRows()-1, first.getPieceType()));
    }

    @Test
    public void checkWinVertical()
    {
        Board board = new Board();
        Player first, second;

        first = new HumanPlayer("P1", PieceType.RED);

        int i;
        for (i = 0; i < 4; i++) {
            board.putPieceTypeAtCol(0, first.getPieceType());
        }

        Assert.assertTrue(board.checkWin(2, 0, first.getPieceType()));
    }

    @Test
    public void checkNonWin4Reds()
    {
        Board board = new Board();
        Player first, second;

        first = new HumanPlayer("P1", PieceType.RED);

        int i;
        for (i = 0; i < 3; i++) {
            board.putPieceTypeAtCol(i, first.getPieceType());
        }

        board.putPieceTypeAtCol(0, first.getPieceType());

        Assert.assertFalse(board.checkWin(4, 0, first.getPieceType()));
    }


    @Test
    public void check4InARowDiagonalDown()
    {
        Board board = new Board();

        Player first;
        first = new HumanPlayer("P1", PieceType.RED);

        int i;
        for (i = 0; i < 4; i++) {
            board.putPieceBypass(i, i, first.getPieceType());
        }

        Assert.assertTrue(board.check4Win(i-1, i-1, first.getPieceType()));
    }

    @Test
    public void check4InARowDiagonalUp()
    {
        Board board = new Board();

        Player first;
        first = new HumanPlayer("P1", PieceType.RED);

        board.putPieceBypass(3, 0, first.getPieceType());
        board.putPieceBypass(2, 1, first.getPieceType());
        board.putPieceBypass(1, 2, first.getPieceType());
        board.putPieceBypass(0, 3, first.getPieceType());

        Assert.assertTrue(board.check4Win(0, 3, first.getPieceType()));
    }

    @Test
    public void utilityOnePieceHorizontal()
    {
        Board board = new Board();
        board.putPiece(5, 6, new Piece(PieceType.RED));
        Assert.assertEquals(50, board.horizontalUtility());
    }

    @Test
    public void utilityOnePieceVertical()
    {
        Board board = new Board();
        board.putPieceTypeAtCol(5, 6, PieceType.RED);
        Assert.assertEquals(50, board.verticalUtility());
    }

    @Test
    public void utilityRedHorizontalWin()
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
    public void utilityBlackHorizontalWin()
    {
        Player human = new HumanPlayer("Player 2", PieceType.BLACK, false);
        Board board = new Board();
        board.putPieceTypeAtCol(5, 4, PieceType.BLACK);
        board.putPieceTypeAtCol(5, 3, PieceType.BLACK);
        board.putPieceTypeAtCol(5, 2, PieceType.BLACK);
        board.putPieceTypeAtCol(5, 1, PieceType.BLACK);
        Assert.assertEquals(Integer.MIN_VALUE, board.utility(human));
    }

    @Test
    public void utilityOneHorizontalRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPieceTypeAtCol(5, 4, human.getPieceType());
        Assert.assertEquals(50, board.horizontalUtility());
    }

    @Test
    public void utilityTwoHorizontalRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPieceTypeAtCol(5, 4, human.getPieceType());
        board.putPieceTypeAtCol(5, 3, human.getPieceType());
        Assert.assertEquals((int)Math.pow(50, 2), board.horizontalUtility());
    }

    @Test
    public void utilityThreeHorizontalRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPieceTypeAtCol(5, 4, human.getPieceType());
        board.putPieceTypeAtCol(5, 3, human.getPieceType());
        board.putPieceTypeAtCol(5, 2, human.getPieceType());
        Assert.assertEquals((int)Math.pow(50, 3), board.horizontalUtility());
    }

    @Test
    public void utilityThreeHorizontalBrokenBLACKRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPieceTypeAtCol(5, 4, human.getPieceType());
        board.putPieceTypeAtCol(5, 3, PieceType.BLACK);
        board.putPieceTypeAtCol(5, 2, human.getPieceType());
        board.putPieceTypeAtCol(5, 1, human.getPieceType());
        Assert.assertEquals((int)Math.pow(50, 2), board.horizontalUtility());
    }

    @Test
    public void utilityThreeHorizontalBrokenNONERed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPieceTypeAtCol(5, 4, human.getPieceType());
        board.putPieceTypeAtCol(5, 3, PieceType.NONE);
        board.putPieceTypeAtCol(5, 2, human.getPieceType());
        board.putPieceTypeAtCol(5, 1, human.getPieceType());
        Assert.assertEquals((int)Math.pow(50, 2)+50, board.horizontalUtility());
    }

    @Test
    public void utilityThreeVerticalBrokenBLACKRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPieceTypeAtCol(0, human.getPieceType());
        board.putPieceTypeAtCol(0, human.getPieceType());
        board.putPieceTypeAtCol(0, PieceType.BLACK);
        board.putPieceTypeAtCol(0, human.getPieceType());
        Assert.assertEquals((int)Math.pow(50, 2), board.verticalUtility());
    }

    @Test
    public void utilityThreeVerticalBrokenREDBlack()
    {
        Player human = new HumanPlayer("Player 2", PieceType.BLACK, false);
        Board board = new Board();
        board.putPieceTypeAtCol(0, human.getPieceType());
        board.putPieceTypeAtCol(0, human.getPieceType());
        board.putPieceTypeAtCol(0, PieceType.RED);
        board.putPieceTypeAtCol(0, human.getPieceType());
        Assert.assertEquals(-(int)Math.pow(50, 2), board.verticalUtility());
    }

    @Test
    public void utilityThreeHorizontalStackedRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.BLACK, false);
        Board board = new Board();
        board.putPieceTypeAtCol(5, 4, human.getPieceType());
        board.putPieceTypeAtCol(5, 3, PieceType.RED);
        board.putPieceTypeAtCol(5, 2, human.getPieceType());
        board.putPieceTypeAtCol(5, 1, human.getPieceType());

        board.putPieceTypeAtCol(4, human.getPieceType());
        board.putPieceTypeAtCol(3, PieceType.RED);
        board.putPieceTypeAtCol(2, human.getPieceType());
        board.putPieceTypeAtCol(1, human.getPieceType());

        Assert.assertEquals(-2*((int)Math.pow(50, 2)), board.horizontalUtility());
    }

    @Test
    public void tiedHorizontalUtility1()
    {
        Player human = new HumanPlayer("Player 2", PieceType.BLACK, false);
        Board board = new Board();
        board.putPieceTypeAtCol(0, human.getPieceType());
        board.putPieceTypeAtCol(0, PieceType.RED);

        Assert.assertEquals(0, board.horizontalUtility());
    }

    @Test
    public void sequentialVerticalUtilities()
    {
        Player human = new HumanPlayer("Player 2", PieceType.BLACK, false);
        Board board = new Board();

        for (int i = 0; i < board.getNumCols(); i++) {
            board.putPieceTypeAtCol(i, human.getPieceType());
        }

        Assert.assertEquals(-50*7, board.verticalUtility());
    }

    @Test
    public void utilityEmptyBoard()
    {
        Player human = new HumanPlayer("Player 2", PieceType.BLACK, false);
        Board board = new Board();
        Assert.assertEquals(0, board.utility(human));
    }

    @Test
    public void totalUtilOneRED()
    {
        Player human = new HumanPlayer("Player 1", PieceType.RED, true);
        Board board = new Board();
        board.putPieceTypeAtCol(0, human.getPieceType());
        Assert.assertEquals(3*50, board.utility(human));
    }

    @Test
    public void zigZagVertical()
    {
        Board board = new Board();
        for (int i = 0; i < board.getNumRows(); i++) {
            if(i % 2 == 0)
                board.putPieceTypeAtCol(0, PieceType.RED);
            else
                board.putPieceTypeAtCol(0, PieceType.BLACK);

        }
        Assert.assertEquals(0, board.verticalUtility());
    }

    @Test
    public void checkFull()
    {
        Board board = new Board();
        for (int i = board.getNumRows()-1; i>= 0; i--) {
            for (int j = board.getNumCols()-1; j >= 0; j--) {
                board.putPieceTypeAtCol(i, j, PieceType.RED);
            }
        }

        Assert.assertTrue(board.isFull());
    }

    @Test
    public void diagonalUtility()
    {
        Board board = new Board();

        Player first, second;

        first = new HumanPlayer("P1", PieceType.RED);
        second = new HumanPlayer("P2", PieceType.BLACK);

        board.putPieceTypeAtCol(0, first.getPieceType());
        board.putPieceTypeAtCol(0, second.getPieceType());
        board.putPieceTypeAtCol(0, first.getPieceType());

        board.putPieceTypeAtCol(1, first.getPieceType());
        board.putPieceTypeAtCol(1, first.getPieceType());

        board.putPieceTypeAtCol(2, first.getPieceType());

        Assert.assertEquals((int)Math.pow(50, 3)+(int)Math.pow(50, 2)+100, board.diagonalUtility());
    }
//
//    @Test
//    public void diagonalUtilitySecondHalf()
//    {
//        Board board = new Board();
//
//        Player first, second;
//
//        first = new HumanPlayer("P1", PieceType.RED);
//        second = new HumanPlayer("P2", PieceType.BLACK);
//
//        board.putPieceTypeAtCol(3, second.getPieceType());
//        board.putPieceTypeAtCol(3, first.getPieceType());
//        board.putPieceTypeAtCol(3, first.getPieceType());
//        board.putPieceTypeAtCol(3, second.getPieceType());
//
//        board.putPieceTypeAtCol(4, first.getPieceType());
//        board.putPieceTypeAtCol(4, first.getPieceType());
//        board.putPieceTypeAtCol(4, second.getPieceType());
//
//        board.putPieceTypeAtCol(5, first.getPieceType());
//        board.putPieceTypeAtCol(5, second.getPieceType());
//
//        Assert.assertEquals((int)Math.pow(-50, 3)+(int)Math.pow(50, 2)+(int)Math.pow(50, 3)-50, board.diagonalUtility());
//    }
//
//    @Test
//    public void diagonalUtilityUPFirstHalf()
//    {
//        Board board = new Board();
//
//        Player first, second;
//
//        first = new HumanPlayer("P1", PieceType.RED);
//        second = new HumanPlayer("P2", PieceType.BLACK);
//
//        board.putPieceTypeAtCol(6, first.getPieceType());
//
//        board.putPieceTypeAtCol(5, second.getPieceType());
//        board.putPieceTypeAtCol(5, first.getPieceType());
//
//        board.putPieceTypeAtCol(4, second.getPieceType());
//        board.putPieceTypeAtCol(4, second.getPieceType());
//        board.putPieceTypeAtCol(4, first.getPieceType());
//
//        Assert.assertEquals((int)Math.pow(50, 3), board.diagonalUtility());
//    }
//
//    @Test
//    public void diagonalUtilityUPSecondHalf2()
//    {
//        Board board = new Board();
//
//        Player first, second;
//
//        first = new HumanPlayer("P1", PieceType.RED);
//        second = new HumanPlayer("P2", PieceType.BLACK);
//
//        board.putPieceTypeAtCol(1, first.getPieceType());
//
//        board.putPieceTypeAtCol(2, second.getPieceType());
//        board.putPieceTypeAtCol(2, first.getPieceType());
//
//        Assert.assertEquals((int)Math.pow(50, 2)-50, board.diagonalUtility());
//    }
//
//    @Test
//    public void diagonalUtilityUPSecondHalf()
//    {
//        Board board = new Board();
//
//        Player first, second;
//
//        first = new HumanPlayer("P1", PieceType.RED);
//        second = new HumanPlayer("P2", PieceType.BLACK);
//
//        board.putPieceTypeAtCol(0, second.getPieceType());
//        board.putPieceTypeAtCol(0, first.getPieceType());
//        board.putPieceTypeAtCol(0, second.getPieceType());
//        board.putPieceTypeAtCol(0, first.getPieceType());
//        board.putPieceTypeAtCol(0, second.getPieceType());
//
//        board.putPieceTypeAtCol(1, first.getPieceType());
//        board.putPieceTypeAtCol(1, second.getPieceType());
//        board.putPieceTypeAtCol(1, first.getPieceType());
//        board.putPieceTypeAtCol(1, second.getPieceType());
//        board.putPieceTypeAtCol(1, first.getPieceType());
//        board.putPieceTypeAtCol(1, second.getPieceType());
//
//        Assert.assertEquals(-(int)Math.pow(50, 2)+50, board.diagonalUtility());
//    }

    @Test
    public void checkRemoveCol()
    {
        Board board = new Board();

        Player first, second;

        first = new HumanPlayer("P1", PieceType.RED);
        second = new HumanPlayer("P2", PieceType.BLACK);

        board.putPieceTypeAtCol(0, first.getPieceType());
        board.putPieceTypeAtCol(0, second.getPieceType());
        board.putPieceTypeAtCol(0, first.getPieceType());

        board.putPieceTypeAtCol(1, first.getPieceType());
        board.putPieceTypeAtCol(1, first.getPieceType());

        board.putPieceTypeAtCol(2, first.getPieceType());

        board.removePiece(0);
        board.removePiece(0);
        board.removePiece(0);

        board.removePiece(1);
        board.removePiece(1);

        board.removePiece(2);

        Assert.assertTrue(board.isEmpty());
    }

    @Test
    public void check4InARow1()
    {
        Board board = new Board();

        Player first, second;

        first = new HumanPlayer("P1", PieceType.RED);
        second = new HumanPlayer("P2", PieceType.BLACK);

        board.putPieceTypeAtCol(0, first.getPieceType());
        board.putPieceTypeAtCol(0, first.getPieceType());

        board.putPieceTypeAtCol(1, first.getPieceType());
        board.putPieceTypeAtCol(2, first.getPieceType());

        Assert.assertFalse(board.isWin());
    }

    @Test
    public void check4InARowFalse()
    {
        Board board = new Board();

        Player first, second;

        first = new HumanPlayer("P1", PieceType.RED);
        second = new HumanPlayer("P2", PieceType.BLACK);

        board.putPieceTypeAtCol(0, first.getPieceType());
        board.putPieceTypeAtCol(0, first.getPieceType());

        board.putPieceTypeAtCol(1, first.getPieceType());
        board.putPieceTypeAtCol(2, first.getPieceType());

        Assert.assertFalse(board.checkWin(4, 2, first.getPieceType()));
    }
}
