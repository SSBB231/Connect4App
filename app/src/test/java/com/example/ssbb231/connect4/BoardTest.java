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
            board.putPiece(0, PieceType.RED);
        }
        board.putPiece(1, PieceType.RED);
        Assert.assertEquals(PieceType.RED, board.getBoard()[5][0].getType());
        Assert.assertEquals(PieceType.RED, board.getBoard()[4][0].getType());
        Assert.assertEquals(PieceType.RED, board.getBoard()[5][1].getType());
    }

    @Test
    public void putInAllCols()
    {
        Board board = new Board();
        for (int i = 0; i < board.getNumCols(); i++) {
            board.putPiece(i, PieceType.RED);
        }
        board.putPiece(1, PieceType.RED);
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
        board.putPiece(5, 6, PieceType.RED);
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
        board.putPiece(5, 4, PieceType.BLACK);
        board.putPiece(5, 3, PieceType.BLACK);
        board.putPiece(5, 2, PieceType.BLACK);
        board.putPiece(5, 1, PieceType.BLACK);
        Assert.assertEquals(Integer.MIN_VALUE, board.utility(human));
    }

    @Test
    public void utilityOneHorizontalRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPiece(5, 4, human.getPieceType());
        Assert.assertEquals(50, board.horizontalUtility());
    }

    @Test
    public void utilityTwoHorizontalRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPiece(5, 4, human.getPieceType());
        board.putPiece(5, 3, human.getPieceType());
        Assert.assertEquals((int)Math.pow(50, 2), board.horizontalUtility());
    }

    @Test
    public void utilityThreeHorizontalRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPiece(5, 4, human.getPieceType());
        board.putPiece(5, 3, human.getPieceType());
        board.putPiece(5, 2, human.getPieceType());
        Assert.assertEquals((int)Math.pow(50, 3), board.horizontalUtility());
    }

    @Test
    public void utilityThreeHorizontalBrokenBLACKRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPiece(5, 4, human.getPieceType());
        board.putPiece(5, 3, PieceType.BLACK);
        board.putPiece(5, 2, human.getPieceType());
        board.putPiece(5, 1, human.getPieceType());
        Assert.assertEquals((int)Math.pow(50, 2), board.horizontalUtility());
    }

    @Test
    public void utilityThreeHorizontalBrokenNONERed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPiece(5, 4, human.getPieceType());
        board.putPiece(5, 3, PieceType.NONE);
        board.putPiece(5, 2, human.getPieceType());
        board.putPiece(5, 1, human.getPieceType());
        Assert.assertEquals((int)Math.pow(50, 2)+50, board.horizontalUtility());
    }

    @Test
    public void utilityThreeVerticalBrokenBLACKRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.RED, true);
        Board board = new Board();
        board.putPiece(0, human.getPieceType());
        board.putPiece(0, human.getPieceType());
        board.putPiece(0, PieceType.BLACK);
        board.putPiece(0, human.getPieceType());
        Assert.assertEquals((int)Math.pow(50, 2), board.verticalUtility());
    }

    @Test
    public void utilityThreeVerticalBrokenREDBlack()
    {
        Player human = new HumanPlayer("Player 2", PieceType.BLACK, false);
        Board board = new Board();
        board.putPiece(0, human.getPieceType());
        board.putPiece(0, human.getPieceType());
        board.putPiece(0, PieceType.RED);
        board.putPiece(0, human.getPieceType());
        Assert.assertEquals(-(int)Math.pow(50, 2), board.verticalUtility());
    }

    @Test
    public void utilityThreeHorizontalStackedRed()
    {
        Player human = new HumanPlayer("Player 2", PieceType.BLACK, false);
        Board board = new Board();
        board.putPiece(5, 4, human.getPieceType());
        board.putPiece(5, 3, PieceType.RED);
        board.putPiece(5, 2, human.getPieceType());
        board.putPiece(5, 1, human.getPieceType());

        board.putPiece(4, human.getPieceType());
        board.putPiece(3, PieceType.RED);
        board.putPiece(2, human.getPieceType());
        board.putPiece(1, human.getPieceType());

        Assert.assertEquals(-2*((int)Math.pow(50, 2)), board.horizontalUtility());
    }

    @Test
    public void tiedHorizontalUtility1()
    {
        Player human = new HumanPlayer("Player 2", PieceType.BLACK, false);
        Board board = new Board();
        board.putPiece(0, human.getPieceType());
        board.putPiece(0, PieceType.RED);

        Assert.assertEquals(0, board.horizontalUtility());
    }

    @Test
    public void sequentialVerticalUtilities()
    {
        Player human = new HumanPlayer("Player 2", PieceType.BLACK, false);
        Board board = new Board();

        for (int i = 0; i < board.getNumCols(); i++) {
            board.putPiece(i, human.getPieceType());
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
        board.putPiece(0, human.getPieceType());
        Assert.assertEquals(50+50, board.utility(human));
    }

    @Test
    public void zigZagVertical()
    {
        Board board = new Board();
        for (int i = 0; i < board.getNumRows(); i++) {
            if(i % 2 == 0)
                board.putPiece(0, PieceType.RED);
            else
                board.putPiece(0, PieceType.BLACK);

        }
        Assert.assertEquals(0, board.verticalUtility());
    }

    @Test
    public void checkFull()
    {
        Board board = new Board();
        for (int i = board.getNumRows()-1; i>= 0; i--) {
            for (int j = board.getNumCols()-1; j >= 0; j--) {
                board.putPiece(i, j, PieceType.RED);
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

        board.putPiece(0, first.getPieceType());
        board.putPiece(0, second.getPieceType());
        board.putPiece(0, first.getPieceType());

        board.putPiece(1, first.getPieceType());
        board.putPiece(1, first.getPieceType());

        board.putPiece(2, first.getPieceType());

        Assert.assertEquals((int)Math.pow(PieceType.RED.value, 3), board.diagonalDownUtility());
    }

    @Test
    public void diagonalUtilitySecondHalf()
    {
        Board board = new Board();

        Player first, second;

        first = new HumanPlayer("P1", PieceType.RED);
        second = new HumanPlayer("P2", PieceType.BLACK);

        board.putPiece(3, second.getPieceType());
        board.putPiece(3, first.getPieceType());
        board.putPiece(3, first.getPieceType());
        board.putPiece(3, second.getPieceType());

        board.putPiece(4, first.getPieceType());
        board.putPiece(4, first.getPieceType());
        board.putPiece(4, second.getPieceType());

        board.putPiece(5, first.getPieceType());
        board.putPiece(5, second.getPieceType());

        Assert.assertEquals((int)Math.pow(-50, 3)+(int)Math.pow(50, 2)+(int)Math.pow(50, 3)-50, board.diagonalDownUtility());
    }
}
