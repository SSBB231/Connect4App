package com.example.ssbb231.connect4;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/**
 * Created by ssbb231 on 12/10/17.
 */

public class SimpleMM implements Minmax {

    private int limit;
    private int depth;
    private int move;
    private final Player first, second;
    private final List<Integer> choices;

    public SimpleMM(int limit)
    {
        this.limit = limit;
        this.depth = 0;
        this.move = -1;

        first = new HumanPlayer("P1", PieceType.RED);
        second = new HumanPlayer("P2", PieceType.BLACK);

        choices = new ArrayList<>();
        for (int i = 0; i < 7; i++) {
            choices.add(i);
        }
    }

    @Override
    public int getMove(Board state) {
        return alphaBetaSearch(state);
    }

    private int alphaBetaSearch(Board state) {
//        Random random = new Random();
        Board stateCopy = state.deepCopy();
        this.move = -1;

        int value = minValue(stateCopy, Integer.MIN_VALUE, Integer.MAX_VALUE, depth, limit, first);

        if(move == -1) {
            System.out.println("==== P2 MAKES RANDOM MOVE ========");
            move = new Random().nextInt(7);
        }
        else
        {
            System.out.println("------------- P2 Makes Move: " + move + "----------------------");
        }

        return move;
    }

    private int maxValue(Board state, int alpha, int beta, int depth, int limit, Player currentPlayer) {

        if(state.isWin())
        {
//            System.out.printf("BOTTOM %d\n", depth);
//            System.out.print(state.toString());
//            System.out.flush();
            return state.utility(currentPlayer);
        }

        if(state.isFull())
            return state.utility(currentPlayer);

        if(depth == limit)
            return state.utility(currentPlayer);


        int value = Integer.MIN_VALUE;

        Collections.shuffle(choices);
        //For every action
        for(int col = 0; col < state.getNumCols(); col++)
        {
            int choice = choices.get(col);
            //Check if move valid.
            if(state.isValidMove(choice))
            {
                if(move != -1)
                    return value;
                state.putPieceTypeAtCol(choice, first.getPieceType());
                int moveResult = minValue(state, alpha, beta, depth+1, limit, first);
                state.removePiece(choice);

                value = Math.max(value, moveResult);

                if((value >= beta)) {
                    if(depth == 1)
                        move = choice;
                    return value;
                }

                alpha = Math.max(alpha, value);
            }
        }
        return value;
    }

    private int minValue(Board state, int alpha, int beta, int depth, int limit, Player currentPlayer)
    {
        if(state.isWin())
        {
//            System.out.printf("BOTTOM %d\n", depth);
//            System.out.print(state.toString());
//            System.out.flush();
            return state.utility(currentPlayer);
        }

        if(state.isFull())
            return state.utility(currentPlayer);

        if(depth == limit)
            return state.utility(currentPlayer);

        int value = Integer.MAX_VALUE;

        Collections.shuffle(choices);

        for (int col = 0; col < state.getNumCols(); col++) {

            int choice = choices.get(col);

            if (state.isValidMove(choice))
            {
                if(move != -1)
                    return value;
                state.putPieceTypeAtCol(choice, second.getPieceType());
                int moveResult = maxValue(state, alpha, beta, depth+1, limit, second);
                state.removePiece(choice);

                value = Math.min(value, moveResult);

                if ((value <= alpha)) {
                    if(depth == 0)
                        move = choice;
                    return value;
                }

                beta = Math.min(beta, value);
            }
        }
        return value;
    }

    @Override
    public void run() {

    }
}
