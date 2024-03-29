package com.example.ssbb231.connect4;

/**
 * Created by ssbb231 on 12/10/17.
 */

public class MyMinmax implements Minmax {

    private int limit;
    private int depth;
    private int move;
    private final Player first, second;

    public MyMinmax(int limit)
    {
        this.limit = limit;
        this.depth = 0;
        this.move = -1;
        first = new HumanPlayer("P1", PieceType.RED);
        second = new HumanPlayer("P2", PieceType.BLACK);
    }

    @Override
    public int getMove(Board state) {
        return alphaBetaSearch(state);
    }

    private int alphaBetaSearch(Board state) {
//        Random random = new Random();
        Board stateCopy = state.deepCopy();
        this.move = -1;
        int util;

        int value = minValue(stateCopy, Integer.MIN_VALUE, Integer.MAX_VALUE, depth, limit);

        depth++;
        limit++;

        return move;
    }

    private int maxValue(Board state, int alpha, int beta, int depth, int limit) {

        if(state.isWin())
        {
//            System.out.printf("BOTTOM %d\n", depth);
//            System.out.print(state.toString());
//            System.out.flush();
            return state.utility(first);
        }

        if(state.isFull())
            return state.utility(first);

        if(depth == limit)
            return state.utility(first);


        int value = Integer.MIN_VALUE;

        //For every action
        for(int col = 0; col < state.getNumCols(); col++)
        {
            //Check if move valid.
            if(state.isValidMove(col))
            {
                state.putPieceTypeAtCol(col, second.getPieceType());
                value = Math.max(value, minValue(state, alpha, beta, depth+1, limit));
                state.removePiece(col);

                if((value >= beta)) {
                    if(depth == 1)
                        move = col;
                    return value;
                }

                alpha = Math.max(alpha, value);
            }
        }
        return value;
    }

    private int minValue(Board state, int alpha, int beta, int depth, int limit)
    {
        if(state.isWin())
        {
//            System.out.printf("BOTTOM %d\n", depth);
//            System.out.print(state.toString());
//            System.out.flush();
            return state.utility(second);
        }

        if(depth == limit)
            return state.utility(second);

        if(state.isFull())
            return state.utility(second);

        int value = Integer.MAX_VALUE;

        for (int col = 0; col < state.getNumCols(); col++) {
            if (state.isValidMove(col))
            {
                state.putPieceTypeAtCol(col, first.getPieceType());
                value = Math.min(value, maxValue(state, alpha, beta, depth+1, limit));
                state.removePiece(col);

                if ((value <= alpha)) {
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
