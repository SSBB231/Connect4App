package com.example.ssbb231.connect4;

/**
 * Created by eyouabmesfin on 11/29/17.
 * Edited by strinh 12/1/17
 */

public class Board {
    //field
    private Piece board[][]; // 6X7
    private int filledValues[];
    private int numRows;
    private int numCols;
    private boolean isWin;

    //methods

    /**
     * Default constructor for the class, currently hardcoding the size of the board
     */
    public Board(){ //constructor
        this.isWin = false;
        this.board = new Piece[6][7];
        //Keep an internal representation of how filled each column is.
        this.numCols = 7;
        this.numRows = 6;

        //Make sure to inailize the board to nulls //remove if not needed
        for(int i = 0; i < 6; i++){
            for(int j = 0; j < 7; j++)
                this.board[i][j] = null;
        }
        this.filledValues = new int[6];
        for(int i = 0; i < numRows; i++)
            filledValues[i] = 0;
    }

    /**
     * Constructor for the class to pass in the size of the board
     */
    public Board(int rows, int cols){ //constructor
        this.isWin = false;
        this.numCols = cols;
        this.numRows = rows;
        //Make sure to inaialize the board to nulls //remove if not needed
        for(int i = 0; i < this.numRows; i++){
            for(int j = 0; j < this.numCols; j++)
                this.board[i][j] = null;
        }
        this.board = new Piece[this.numRows][this.numCols];
        //Keep an internal representation of how filled each column is.
        this.filledValues = new int[this.numRows];
        for(int i = 0; i < this.numRows; i++)
            this.filledValues[i] = 0;
    }


    public String toString() {
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                builder.append(String.format("%3s ", board[i][j].toString()));
            }
            builder.append("\n");
        }

        return builder.toString();
    }

    public boolean isValidMove(int row, int col)
    {
        if(!withinBounds(row, col))
            return false;
        else if(board[row][col].getType() != PieceType.NONE)
        {
            return false;
        }
        else if(row < board.length-1 && board[row+1][col].getType() == PieceType.NONE)
        {
            return false;
        }
        else
        {
            return true;
        }
    }

    public boolean withinBounds(int row, int col)
    {
        return (row >= 0 && row < board.length) && (col >= 0 && col < board[0].length);
    }

    /**
     * Place a new piece into the board at the given position.
     * Checks to see if the user has just won the game as well.
     * @param row - The row index
     * @param col - The col index
     * @param p   - The piece to be passed in
     */
    public void putPiece(int row, int col, Piece p){
        //Put the piece in the appropriate place if it can.
        if(isValidPut(row, col)) {
            this.board[row][col] = p;
            this.filledValues[row] += 1;
        }
        //Check the spaces around the current piece to see if the win conditions are set.
        if(check4InARow(row, col))
            this.isWin = true;

    }

    /**
     * Remove the piece from the board at the given position. Shouldn't actually be called by players.
     * @param row - The row index
     * @param col - The col index
     */
    public void removePiece(int row, int col){
        int filledUpToIndex = this.filledValues[row] - 1;
        if(row >= 0 && col >= 0 && row < this.numRows && col < this.numCols && filledUpToIndex >= col && this.board[row][col] != null ) {
            this.board[row][col] = null;
            this.filledValues[row] -= 1;
        }
    }

    /**
     * Return the current board
     * @return - The board represented as an array of Pieces
     */
    public Piece[][] getBoard(){
        return this.board;
    }

//    /**
//     * Return the board as a string
//     * @return - The string representation of the board
//     */
//    public String toString(){
//        String boardStr = "";
//        for(int i = 0; i < numRows; i++){
//            for(int j = 0; j < numCols; j++){
//                boardStr += board[i][j];
//                if(j != (numCols-1)){ //If this is not the last entry in the row
//                    boardStr += ", ";
//                }
//            }
//            boardStr += "\n ";
//        }
//        return boardStr;
//    }

    /**
     * isValidMove will differ per put/remove. Will make this for putting for now as users can't remove.
     * @param row - The row index
     * @param col - The col index
     * @return  boolean value of whether this is a valid put command.
     */
    public boolean isValidPut(int row, int col){
        if(row >= 0 && col >= 0 && row < this.numRows && col < this.numCols) { //check for valid bounds
            int filledUpTo = this.filledValues[row];
            if(filledUpTo < this.numCols && (board[row][col] == null)) //check if cols are full or if coordinates aren't empty.
                return true;
        }
        return false;
    }

    /**
     * Outer method to call to check if the game is won via a full search of the array.
     * (Also implemented in 'put' for a more efficient vers. that will be called after every put)
     * @return Boolean on is the game won?
     */
    public boolean isWin(){
        //If we operate under the assumption that we only call isWin after a put call, we can just return the field
        return this.isWin;

        //otherwise do the following:
        /*if(this.isWin == true)  //First check to see if someone has already won to avoid re-checking
            return true;
        //The full search of the board while it has valid pieces. (call check4InARow)
        return false;
        */
    }

    /**
     * This method will assign a numerical value that will describe how good the Board's current state
     * is for each of the players. Min will be the computer player and Max will be the human player.
     * So negative numbers are good for the computer and positive numbers are good for the human player.
     *
     * @return  This board's utility based on its current state.
     */
    public int utility(Player currentPlayer) {

        //initialize value to return as zero first
        int value = 0;

        //First, check for win. If win, return maximum value or minimum value depending on who the current player is
        if(isWin)
        {
            if(currentPlayer.isPlayer1())
                return Integer.MAX_VALUE;
            else
                return Integer.MIN_VALUE;
        }

        //If the board is full and it wasn't a win, then it's a tie.
        //return neutral value
        if(isFull())
        {
            return 0;
        }

        //Keeps track of how many pieces of the same color are in the same row
        int sameInARow = 0;

        //Keep track of the mini values that are formed by chains of tiles of same color
        int currentValue;

        //It is optimal to check the board from bottom to top.
        for(int i = board.length; i >= 0; i--)
        {
            //Set currentValue to zero to check the current row for triples or quadruples.
            //For each row, start with a neutral PieceType
            PieceType previousType = board[i][0].getType();
            PieceType currentType;
            for(int j = board[0].length; j >= 0; j--)
            {
                currentType = board[i][j].getType();

                //if currentType is different from previousType, flush the currentValue into value
            }
        }

        return value;
    }

    private boolean isFull()
    {
        for(int i = 0; i < board.length; i++)
        {
            for(int j = 0; j < board[0].length; j++)
            {
                if(board[i][j].getType() == PieceType.NONE)
                    return false;
            }
        }

        return true;
    }

    /**
     * Helper method to check if the there are four matching pieces in any direction
     * @param row - The row index
     * @param col - The col index
     * @return
     */
    private boolean check4InARow(int row, int col){
        //Coordinate positions
        int leftX = row, leftY = col - 1;
        int rightX = row, rightY = col + 1 ;
        int upX = row + 1,  upY = col;
        int downX = row - 1, downY = col;
        int upLeftX =  row + 1, upLeftY = col - 1;
        int upRightX = row + 1, upRightY = col + 1;
        int downLeftX = row - 1, downLeftY = col - 1;

        //Need to finish this
        return false;
    }
}
