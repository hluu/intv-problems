package org.learning.backtracking;


import org.common.ArrayUtils;

/**
 *
 * Given a board of size n by n, place a queen in each column such that
 * it doesn't violating the following rule
 *  1) no two are in the same row
 *  2) no two are on the sam column
 *  3  no two are on the same diagonal
 *
 * This is a classic backtracking problem:
 *  1) We try to place a queen a possible row
 *  2) If that doesn't violate the rules, we move on to next queen
 *  3) If it does violate the rules, then undo previous action, and try a different rule
 *  4) End state, when we finish populating all the queens in all the columns w/o violating the rules
 *
 * The backtrack part is the undo part.  General approach is being optimistic about placing a queen
 * and if that doesn't work out, then undo it.
 *
 *
 */
public class QueenBee {
  private static final int UNASSIGNED_VALUE = 0;
  private static final int QUEEN_VALUE = 1;

  public static void main(String[] args) {
    System.out.printf("%s\n", QueenBee.class.getName());

    int boardSize = 4;
    int[][] board = new int[boardSize][boardSize];

    printBoard(board);

    //board[0][0] = QUEEN_VALUE;

    boolean possible = solveQueenBee(board, 0);

    System.out.printf("board size: %d, possible: %b\n", board.length, possible);
    printBoard(board);

    System.out.printf("isEntireBoardValid valid? %b\n", isEntireBoardValid(board));
  }

  private static boolean solveQueenBee(int[][] board, int column) {
    if (column == board[0].length) {
      return true;
    }

    for (int candidateRow = 0; candidateRow < board.length; candidateRow++) {

      if (isBoardSafe(board, candidateRow,column)) {
        // make a decision
        board[candidateRow][column] = QUEEN_VALUE;

        // see if we can solve it
        if (solveQueenBee(board,  column +1)) {
          return true;
        }
        //System.out.printf("*** backtracking from (%d,%d) ***\n", candidateRow, column);

        // backtrack
        board[candidateRow][column] = UNASSIGNED_VALUE;
      }
    }
    return false;
  }

  private static boolean isEntireBoardValid(int[][] board) {
    for (int row = 0; row < board.length; row++) {
      for (int column = 0; column < board[0].length; column++) {
        if ((board[row][column] == QUEEN_VALUE) && !isBoardValid(board, row, column)) {
          System.out.printf("*** found false at (%d,%d)\n", row, column);
          return  false;
        }
      }
    }

    return true;
  }

  private static boolean isBoardSafe(int[][] board, int row, int column) {

    // check row
    int count = 0;
    for (int col = 0; col < board[row].length; col++) {
      if (board[row][col] == QUEEN_VALUE) {
        return false;
      }
    }

    // check column
    count = 0;
    for (int rw = 0; rw < board.length; rw++) {
      if (board[rw][column] == QUEEN_VALUE) {
        return false;
      }
    }


    // == the reason we don't have to check right side diagonal is because we haven't placed
    // == any queen over there yet

    // check upper left diagonal (going left and up)
    for (int rowCoord = row, columnCoord = column; rowCoord >= 0 && columnCoord >= 0;
         rowCoord--, columnCoord--) {
      if (board[rowCoord][columnCoord] == QUEEN_VALUE) {
        return false;
      }
    }

    // check lower left diagonal
    for (int rowCoord = row, columnCoord = column; rowCoord < board.length && columnCoord >= 0;
         rowCoord++, columnCoord--) {
      if (board[rowCoord][columnCoord] == QUEEN_VALUE) {
        return false;
      }
    }


    // if we get here, then there was no conflict
    return true;
  }

  private static boolean isBoardValid(int[][] board, int row, int column) {

    // check row
    int count = 0;
    for (int i = 0; i < board[row].length; i++) {
      if (board[row][i] == QUEEN_VALUE) {
        count++;
      }
    }

    if (count > 1) {
      return false;
    }

    // check column
    count = 0;
    for (int i = 0; i < board.length; i++) {
      if (board[i][column] == QUEEN_VALUE) {
        count++;
      }
    }

    if (count > 1) {
      return false;
    }

    // check diagonal
    // upper left
    int rowCoord = row - 1;
    int colCoord = column - 1;
    if (isThereAQueenHere(board, rowCoord, colCoord)) {
      return  false;
    }

    // lower left
    rowCoord = row + 1;
    colCoord = column - 1;
    if (isThereAQueenHere(board, rowCoord, colCoord)) {
      return  false;
    }

    // upper right
    rowCoord = row - 1;
    colCoord = column + 1;
    if (isThereAQueenHere(board, rowCoord, colCoord)) {
      return  false;
    }

    // lower right
    rowCoord = row + 1;
    colCoord = column + 1;
    if (isThereAQueenHere(board, rowCoord, colCoord)) {
      return  false;
    }

    return true;
  }

  private static boolean isThereAQueenHere(int[][] board, int row, int column) {
    if (row < 0 || column < 0) {
      return false;
    }

    if (row >= board.length || column >= board[0].length) {
      return false;
    }

    return board[row][column] == QUEEN_VALUE;
  }


  private static void printBoard(int[][] board) {
    ArrayUtils.printMatrix(board);
  }

}

