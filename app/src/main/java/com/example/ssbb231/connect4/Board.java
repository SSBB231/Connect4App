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
                this.board[i][j] = new Piece(PieceType.NONE); //board is full of PieceType.NONE
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

    public void putPiece(int col, PieceType t)
    {
        for (int i = board.length-1; i >= 0 ; i--) {
            if(isValidMove(i, col))
            {
                putPiece(i, col, t);

                break;
            }
        }

    }

    public void putPiece(int col, Piece p)
    {
        for (int i = board.length-1; i >= 0 ; i--) {
            if(isValidMove(i, col))
            {
                putPiece(i, col, p);
                break;
            }
        }
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

    public int getNumRows() {
        return numRows;
    }

    public int getNumCols() {
        return numCols;
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
        if(isValidMove(row, col)) {
            this.board[row][col] = p;
            this.filledValues[row] += 1;
        }
        //Check the spaces around the current piece to see if the win conditions are set.
        if(check4InARow(row, col,p))
            this.isWin = true;
        else
            this.isWin = false;

    }

    /**
     * Optimal version of putPiece. Will not create a new Instance of Piece, but rather
     * just change the type of the Pieces that are already instantiated and in the board.
     * Place a new piece into the board at the given position.
     * Checks to see if the user has just won the game as well.
     * @param row - The row index
     * @param col - The col index
     * @param p   - The piece to be passed in
     */
    public void putPiece(int row, int col, PieceType p){
        //Put the piece in the appropriate place if it can.
        if(isValidMove(row, col)) {
            this.board[row][col].setType(p);
            this.filledValues[row] += 1;
        }
        //Check the spaces around the current piece to see if the win conditions are set.
        if(checkWin())
            this.isWin = true;
        else
            this.isWin = false;

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

    public void removePiece(int col)
    {
        PieceType returnVal;
        for (int i = 0; i < board.length-1; i++) {
            returnVal = board[i][col].getType();

            if(returnVal != PieceType.NONE)
            {
                board[i][col].setType(PieceType.NONE);
                break;
            }
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

    public int horizontalUtility()
    {
        int value = 0;
        //------------------------ Vertical Utility -----------------------------------
        //Keeps track of how many pieces of the same color are in the same col
        int sameInARow = 1;
        int toAdd;

        PieceType previous, current;

        for (int i = board.length-1; i >= 0; i--) {
            for (int j = board[0].length-2; j >= 0; j--) {
                previous = board[i][j+1].getType();
                current = board[i][j].getType();

                toAdd = previous.value;

                if(current != previous)
                {
                    if(toAdd < 0 && sameInARow >= 2)
                        value += -(int)Math.pow(toAdd, sameInARow);
                    else
                        value += (int)Math.pow(toAdd, sameInARow);

                    sameInARow = 1;
                }
                else
                {
                    sameInARow++;
                }

                if(j == 0)
                {
                    toAdd = current.value;

                    if(toAdd < 0 && sameInARow >= 2)
                        value += -(int)Math.pow(toAdd, sameInARow);
                    else
                        value += (int)Math.pow(toAdd, sameInARow);

                    sameInARow = 1;
                }
            }


        }

        return value;
        //------------------------ Vertical Utility -----------------------------------
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

        value += horizontalUtility();
        value += verticalUtility();
        value += diagonalUtility();

        return value;
    }

    public int verticalUtility()
    {
        int value = 0;
        //------------------------ Vertical Utility -----------------------------------
        //Keeps track of how many pieces of the same color are in the same col
        int sameInACol = 1;
        int toAdd;

        PieceType previous, current;

        for (int i = board[0].length-1; i >= 0; i--) {
            for (int j = board.length-2; j >= 0; j--) {
                previous = board[j+1][i].getType();
                current = board[j][i].getType();

                toAdd = previous.value;

                //If the row is empty, no point in going on. Move to next column.
                if(previous == PieceType.NONE)
                    break;
                else if(current != previous)
                {
                    if(toAdd < 0 && sameInACol % 2 == 0)
                        value += -(int)Math.pow(toAdd, sameInACol);
                    else
                        value += (int)Math.pow(toAdd, sameInACol);

                    sameInACol = 1;
                }
                else
                {
                    sameInACol++;
                }

                if(j == 0)
                {
                    toAdd = current.value;

                    if(toAdd < 0 && sameInACol % 2 == 0)
                        value += -(int)Math.pow(toAdd, sameInACol);
                    else
                        value += (int)Math.pow(toAdd, sameInACol);

                    sameInACol = 1;
                }

                if(current == PieceType.NONE)
                    break;
            }
        }

        return value;
        //------------------------ Vertical Utility -----------------------------------
    }

    public int diagonalUtility()
    {
        int value = 0;
        //------------------------ Vertical Utility -----------------------------------
        //Keeps track of how many pieces of the same color are in the same col
        int sameInACol = 1;
        int toAdd;

        PieceType previous, current;

        //Diagonal first half down=======================================================================
        for (int i = 0; i < numRows-1; i++) {
            for (int x = 0, y = i; x < numCols-1 && y < numRows-1; x++, y++) {
                previous = board[y][x].getType();
                current = board[y+1][x+1].getType();

                toAdd = previous.value;

                if(previous == PieceType.NONE && current == PieceType.NONE)
                    continue;

                if(current != previous)
                {
                    if(toAdd < 0 && sameInACol % 2 == 0)
                        value += -(int)Math.pow(toAdd, sameInACol);
                    else
                        value += (int)Math.pow(toAdd, sameInACol);

                    sameInACol = 1;
                }
                else
                {
                    sameInACol++;
                }

                if(y == numRows-2)
                {
                    toAdd = current.value;

                    if(toAdd < 0 && sameInACol % 2 == 0)
                        value += -(int)Math.pow(toAdd, sameInACol);
                    else
                        value += (int)Math.pow(toAdd, sameInACol);

                    sameInACol = 1;
                }
            }
        }
        //Diagonal first half down=======================================================================

        //Diagonal second half down=======================================================================
        sameInACol = 1;

        for (int i = 1; i < numCols-1; i++) {
            for (int x = i, y = 0; x < numCols-1 && y < numRows-1; x++, y++) {
                previous = board[y][x].getType();
                current = board[y+1][x+1].getType();

                toAdd = previous.value;

                if(previous == PieceType.NONE && current == PieceType.NONE)
                    continue;

                if(current != previous)
                {
                    if(toAdd < 0 && sameInACol % 2 == 0)
                        value += -(int)Math.pow(toAdd, sameInACol);
                    else
                        value += (int)Math.pow(toAdd, sameInACol);

                    sameInACol = 1;
                }
                else
                {
                    sameInACol++;
                }

                if(x == numCols-2)
                {
                    toAdd = current.value;

                    if(toAdd < 0 && sameInACol % 2 == 0)
                        value += -(int)Math.pow(toAdd, sameInACol);
                    else
                        value += (int)Math.pow(toAdd, sameInACol);

                    sameInACol = 1;
                }
            }
        }
        //Diagonal second half down=======================================================================

        //Diagonal first half up=======================================================================
        sameInACol = 1;

        for (int i = 0; i <= numRows-2; i++) {
            for (int x = numCols-1, y = i; x >= 1 && y <= numRows-2; x--, y++) {
                previous = board[y][x].getType();
                current = board[y+1][x-1].getType();

                toAdd = previous.value;

                if(previous == PieceType.NONE && current == PieceType.NONE)
                    continue;

                if(current != previous)
                {
                    if(toAdd < 0 && sameInACol % 2 == 0)
                        value += -(int)Math.pow(toAdd, sameInACol);
                    else
                        value += (int)Math.pow(toAdd, sameInACol);

                    sameInACol = 1;
                }
                else
                {
                    sameInACol++;
                }

                if(y == numRows-2)
                {
                    toAdd = current.value;

                    if(toAdd < 0 && sameInACol % 2 == 0)
                        value += -(int)Math.pow(toAdd, sameInACol);
                    else
                        value += (int)Math.pow(toAdd, sameInACol);

                    sameInACol = 1;
                }
            }
        }
        //Diagonal first half up=======================================================================


        //Diagonal SECOND half up=======================================================================
        sameInACol = 1;

        for (int i = numCols-2; i >= 1; i--) {
            for (int x = i, y = 0; x >= 1 && y <= numRows-2; x--, y++) {
                previous = board[y][x].getType();
                current = board[y+1][x-1].getType();

                toAdd = previous.value;

                if(previous == PieceType.NONE && current == PieceType.NONE)
                    continue;

                if(current != previous)
                {
                    if(toAdd < 0 && sameInACol % 2 == 0)
                        value += -(int)Math.pow(toAdd, sameInACol);
                    else
                        value += (int)Math.pow(toAdd, sameInACol);

                    sameInACol = 1;
                }
                else
                {
                    sameInACol++;
                }

                if(x == 1)
                {
                    toAdd = current.value;

                    if(toAdd < 0 && sameInACol % 2 == 0)
                        value += -(int)Math.pow(toAdd, sameInACol);
                    else
                        value += (int)Math.pow(toAdd, sameInACol);

                    sameInACol = 1;
                }
            }
        }
        //Diagonal SECOND half up=======================================================================

        return value;
        //------------------------ Vertical Utility -----------------------------------
    }

    public PieceType getPieceTypeAt(int row, int col)
    {
        if(!withinBounds(row, col))
            return PieceType.NONE;
        else
        {
            return board[row][col].getType();
        }
    }

    public boolean isFull()
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
    private boolean check4InARow(int row, int col, Piece p){
        //counts all start at 1 to account for piece just placed
        int nsCount = 1;
        int ewCount = 1;
        int neswCount = 1;
        int nwseCount = 1;

        //CHECK NORTH
        for(int i = row; i >= 0; i--){
            if(this.board[i][col].equals(p)){
                nsCount++;
            }
            else{
                break; //stops loop the minute the next piece isn't the same
            }
        }
        //CHECK SOUTH
        for(int i = row; i < this.numRows; i++){
            if(this.board[i][col].equals(p)){
                nsCount++;
            }
            else{
                break;
            }
        }
        if(nsCount >= 4){
            //four or more in a row vertically
            return true;
        }

        // CHECK EAST
        for(int i = col; i <this.numCols;i++){
            if(this.board[row][i].equals(p)){
                ewCount++;
            }
            else{
                break;
            }
        }
        //CHECK WEST
        for (int i = col; i >= 0;i--){
            if(this.board[row][i].equals(p)){
                ewCount++;
            }
        }
        if(ewCount >= 4){
            //four or more in a row horizontally
            return true;
        }

        //CHECK NORTH EAST
        for(int i = row,j = col; i >= 0 && j < this.numCols; i--,j++){
            if(this.board[i][j].equals(p)){
                neswCount++;
            }
            else {
                break;
            }
        }
        //CHECK SOUTH WEST
        for(int i = row,j = col; i < this.numRows && j >= 0; i++,j--){
            //this check s.w. direction
            if(this.board[i][j].equals(p)){
                neswCount++;
            }
            else {
                break;
            }
        }
        if(neswCount >= 4){
            //we have four or more in a row diagonally
            return true;
        }

        //CHECK NORTH WEST
        for(int i = row, j = col; i >= 0 && j >= 0; i--,j--){
            if(this.board[i][j].equals(p)){
                nwseCount++;
            }
            else{
                break;
            }
        }
        //CHECK SOUTH EAST
        for(int i = row, j = col; i < this.numRows && j < this.numCols; i++,j++){
            if(this.board[i][j].equals(p)){
                nwseCount++;
            }
            else{
                break;
            }
        }
        if(nwseCount >= 4){
            //we have 4 or more in a row diagonally
            return true;
        }

        return false; //default
    }

    public void putPieceBypass(int row, int col, PieceType t)
    {
        if(withinBounds(row, col))
        {
            board[row][col].setType(t);
        }
    }

    public PieceType removePieceBypass(int row, int col)
    {
        PieceType retval = getPieceTypeAt(row, col);
        putPieceBypass(row, col, PieceType.NONE);
        return retval;
    }

    public boolean check4InARow(int row, int col, PieceType p){
        //counts all start at 1 to account for piece just placed
        int nsCount = 1;
        int ewCount = 1;
        int neswCount = 1;
        int nwseCount = 1;

        //CHECK NORTH
        for(int i = row; i >= 0; i--){
            if(getPieceTypeAt(i, col)==p){
                nsCount++;
            }
            else{
                break; //stops loop the minute the next piece isn't the same
            }
        }
        //CHECK SOUTH
        for(int i = row; i < this.numRows; i++){
            if(getPieceTypeAt(i, col) == p){
                nsCount++;
            }
            else{
                break;
            }
        }
        if(nsCount >= 4){
            //four or more in a row vertically
            return true;
        }

        // CHECK EAST
        for(int i = col; i <this.numCols;i++){
            if(getPieceTypeAt(row, i) == p){
                ewCount++;
            }
            else{
                break;
            }
        }
        //CHECK WEST
        for (int i = col; i >= 0;i--){
            if(getPieceTypeAt(row, i) == p){
                ewCount++;
            }
        }
        if(ewCount >= 4){
            //four or more in a row horizontally
            return true;
        }

        //CHECK NORTH EAST
        for(int i = row,j = col; i >= 0 && j < this.numCols; i--,j++){
            if(getPieceTypeAt(i, j) == p){
                neswCount++;
            }
            else {
                break;
            }
        }
        //CHECK SOUTH WEST
        for(int i = row,j = col; i < this.numRows && j >= 0; i++,j--){
            //this check s.w. direction
            if(getPieceTypeAt(i, j) == p){
                neswCount++;
            }
            else {
                break;
            }
        }
        if(neswCount >= 4){
            //we have four or more in a row diagonally
            return true;
        }

        //CHECK NORTH WEST
        for(int i = row, j = col; i >= 0 && j >= 0; i--,j--){
            if(getPieceTypeAt(i, j) == p){
                nwseCount++;
            }
            else{
                break;
            }
        }
        //CHECK SOUTH EAST
        for(int i = row, j = col; i < this.numRows && j < this.numCols; i++,j++){
            if(getPieceTypeAt(i, j) == p){
                nwseCount++;
            }
            else{
                break;
            }
        }
        if(nwseCount >= 4){
            //we have 4 or more in a row diagonally
            return true;
        }

        return false; //default
    }


    public boolean checkWin()
    {
        //This checks horizontally---------------------------------
        for (int i = 0; i < board.length; i++) {
            int pieceInARow = 1;
            PieceType previous, current;
            for (int j = 0; j < board[0].length-1; j++) {
                previous = board[i][j].getType();
                current = board[i][j+1].getType();

                if(previous != current) {
                    pieceInARow = 1;
                    continue;
                }
                else
                {
                    if(previous != PieceType.NONE && current != PieceType.NONE)
                        pieceInARow++;
                }

                if(pieceInARow == 4) {
                    isWin = true;
                    return true;
                }
            }
        }
        //This checks horizontally---------------------------------

        isWin = false;
        return false;
    }
}
