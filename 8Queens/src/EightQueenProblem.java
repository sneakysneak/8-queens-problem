
/**
 *
 */
/**
 * @author sneakysneak
 *
 */

import java.util.Scanner;

/** ********************************************************
 * Eight Queen problem - Backtracking - Recursive - Based
 * on the First Queen display all possible solutions
 *  ********************************************************/

class EightQueenProblem {

    /** **************************************************
     * Need a
     * - Board
     *   - setBoard - create rows and columns - later implement the board array
     *   - printBoard - draw the board - later implement setBoard
     * - First queen
     *   - Store its position somehow
     *   - Evaluate its pos later for the rest of the queens, important!
     *   - user input
     * - Place all queens
     *   - Actually, 7 more queens needed
     *   - Evaluate first queen's pos, as before stuck the first and an other queen on the same position
     * - Monitor positions, evaluate attacks
     *   - Make sure about queen's position and their rules, don't attack each other
     *   - Evaluate directions in, col and row, as well as diagonal
     * - Algorithm
     *   - Use backtracking with recursion which is the most suitable for this problem
     *   - Look for pseudo code
     ****************************************************/

    /**
     * Call placeQueens method
     * and set the col to 0
     * */
    void solveQueens() {

        placeQueens(board, 0);
    }

    //Board
    /**
     * Need an array for the 8X8 board,
     * count the solutions and
     * store the user input globally.
     * - Counter int for solutions
     * - A GLOBAL var for user input
     * - An array for the board
     **/
    private int solutionCounter;
    // A single int enough
    private int userColumn;
    // Fix sized board 8X8
    private String[][] board;
    // Define them in a constructor
    EightQueenProblem() {

        board = new String[8][8];
        // Starts from 1 not from 0
        solutionCounter = 1;
    }

    /**
     * A void method for the board
     * which implements board[][]
     * from a fix sized array, an empty board.
     * - 2 for loops create tiles where queens can be evaluated
     * - empty tiles
     * */
    void setBoard() {
        // NxN board, N is 8 currently
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                // Empty tiles
                board[row][col] = " ";
            }
        }
    }

    /**
     * Draw the board
     * - 2 for loops create drawing, numbers and chars for the positions of the tiles
     **/
    void printBoard() {
        int rowNum = 1;

        System.out.print("\n    a   b   c   d   e   f   g   h");

        for (String[] row : board) {
            System.out.print("\n   - - - - - - - - - - - - - - - -\n" + rowNum++ + " |");

            for (String col : row) {
                System.out.print(" ");
                System.out.print(col);
                System.out.print(" |");
            }

        }
        System.out.print("\n   - - - - - - - - - - - - - - - -\n");
    }

    //First queen
    /**
     * Need user input for the position.
     * Need x, y pos on board for first queen in order to put it.
     * - Need a loop, boolean which is moving the loop.
     * - Input for the user; row, col.
     * - Add these input values to the board array[X][Y].
     * - SAVE this position in the global var.
     * - Later somehow validate the correctness of the input data
     * */
    void firstQueen() {
        // Scanner for user input
        Scanner input = new Scanner(System.in);
        // A boolean for the do while loop
        boolean isOk = false;

        System.out.print("Enter coordinates (A1):");
        // A string to store the user input, row and col coordinates
        String boardPos = input.next().toLowerCase();

        do {
            try {
                // Disassemble the string input to chars, validate them
                // Java counts from 0 so a-h & 1-8 board tiles (8x8=64) summary is 0-7 X 0-7
                // so 49! Beyond it would let the user to type incorrect input data
                // i.e. h9 at 50 for instance. charAt input validation.
                int col = boardPos.charAt(0) - 'a';
                // On the 1st pos of input string is int
                // Counting from 0, 7x7 is 49
                int row = boardPos.charAt(1) - 49;
                // Add the Queen to the board
                board[row][col] = "Q";
                // Save col to a global int
                userColumn = col;
                // Validate with boolean
                isOk = true;
                // Print the pos of the first queen
                System.out.println("The first queen's pos is: " + boardPos);
                //Validate input data
            } catch (ArrayIndexOutOfBoundsException | StringIndexOutOfBoundsException e) {
                System.out.println("Re enter: \n-a letter a-h\n-a number 1-8");
                boardPos = input.next().toLowerCase();
            }
            // Validate String's length, if it's longer than 2...
            if (boardPos.length() > 2) {
                System.out.println("Re enter: \na letter a-h\na number 1-8");
                boardPos = input.next().toLowerCase();
            }
            // Do while loop, isOk boolean keeps asking till will be true
        } while (!isOk);
        // Resource leak, input never closed, now closed.
        input.close();
    }

    //Monitor pos
    /**
     * Must check all directions diagonally, imagine it from the middle of the board!
     * Left upper
     * - moving row UP col LEFT - from BOT RIGHT to TOP LEFT
     * Left lower
     * - moving row DOWN col LEFT - from TOP RIGHT to BOT LEFT
     * Right upper
     * - moving row UP col RIGHT - from BOT LEFT to TOP RIGHT
     * Right lower
     * - moving row DOWN col RIGHT - from TOP LEFT to BOT RIGHT
     * Horizontal - going DOWN
     * - Iterate through all the rows, in the current row checking each col, if it finds a "Q" - false
     * - and then if the algo finds the first queen, add + 1 and do this with other queens too. So do it twice!
     * */
    private boolean isSafe(String board[][], int row, int col) {
        int i, j;

        // Upper right side diagonAl - moving row UP col RIGHT - from BOT LEFT to TOP RIGHT
        for (i = row, j = col; i >= 0 && j < 8; i--, j++)
            if (board[i][j].equals("Q"))
                return false;

        // Lower right side diagonal - moving row DOWN col RIGHT - from TOP LEFT to BOT RIGHT
        for (i = row, j = col; i < 8 && j < 8; i++, j++)
            if (board[i][j].equals("Q"))
                return false;

        // Upper left side doagonal - moving row UP col LEFT - from BOT RIGHT to TOP LEFT
        for (i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j].equals("Q"))
                return false;

        // Lower left side diagonal - moving row DOWN col LEFT - from TOP RIGHT to BOT LEFT
        for (i = row, j = col; i < 8 && j >= 0; i++, j--)
            if (board[i][j].equals("Q"))
                return false;

        // Row collision - goes along the row checking all col tiles
        for (j = 0; j < 8; j++)
            if (board[row][j].equals("Q"))
                return false;
        return true;
    }

    //Place all queens & algorithm & recursion
    /**
     * All possible tiles
     * - put first queen
     * - try to place rest of the queens
     * - re-place queens
     * All row and column only one queen
     *
     * - Call isSafe method recursively to ensure correct queen pos.
     * - If all queens done, print the board and all solutions
     * - Check for first queen in the current column. If it's there col + 1 !!! - That plus needs in order to work
     * - If based on isSafe the position is fine, put a queen and step on to the next col
     * - Else, step back and change the route - simple backtracking
     * */
    private boolean placeQueens(String board[][], int col) {
        // Print board if for loop reached the last pos
        if (col >= 8) {
            System.out.print("\nSolution " + solutionCounter++);
            printBoard();
            return false;
        }
        //HERE!
        // Backtracking algorithm with additional first queen evaluation.
        // If column is the user input column which means
        // the first queen was found THEN call this method
        // recursively and add that column + 1 so don't put queen
        // in that col, jump to the next col and continue placing queens
        // after this.
        // That's how to store the first queen's position and evaluate.
        if (col == userColumn) {
            if (placeQueens(board, col + 1))
                return true;
        }
        // Put the rest of the queens on the board.
        // isSafe method helps to put the rest of the queens correctly.
        // Place 1 queen in this col
        // Each possible place in this col EXCEPT first queen's col
        for (int i = 0; i < 8; i++) {
            // If isSafe position then
            if (isSafe(board, i, col)) {
                // Place queen
                board[i][col] = "Q";
                // Explore
                if (placeQueens(board, col + 1))
                    return true;
                // Remove queen
                board[i][col] = " ";
            }
        }
        return false;
    }
}