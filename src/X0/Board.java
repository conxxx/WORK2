package X0;

import java.util.ArrayList;
import java.util.List;

public class Board {
    protected static final int SIZE = 5; // The size of the board
    private static final int WIN_CONDITION = 4; // Number of marks in a line needed to win
    private PlayerType[][] board;

    public Board() {
        board = new PlayerType[SIZE][SIZE];
    }
    public boolean isWinningMove(Cell cell) {
        PlayerType playerType = board[cell.getRow()][cell.getCol()];
        if (playerType == null) {
            return false; // Empty cell, cannot be a winning move
        }

        return checkWinningCondition(cell, playerType);
    }
    private boolean checkWinningCondition(Cell cell, PlayerType playerType) {
        // Check for 4 in a row horizontally, vertically, and diagonally
        return checkHorizontalWin(cell, playerType) || checkVerticalWin(cell, playerType)
                || checkDiagonalWin(cell, playerType) || checkAntiDiagonalWin(cell, playerType);
    }
    private boolean checkHorizontalWin(Cell cell, PlayerType playerType) {
        int inRow = 0;
        for (int col = 0; col < SIZE; col++) {
            if (board[cell.getRow()][col] == playerType) {
                inRow++;
                if (inRow == WIN_CONDITION) return true;
            } else {
                inRow = 0; // Reset count if a different type is encountered
            }
        }
        return false;
    }

    private boolean checkVerticalWin(Cell cell, PlayerType playerType) {
        int inColumn = 0;
        for (int row = 0; row < SIZE; row++) {
            if (board[row][cell.getCol()] == playerType) {
                inColumn++;
                if (inColumn == WIN_CONDITION) return true;
            } else {
                inColumn = 0; // Reset count if a different type is encountered
            }
        }
        return false;
    }

    private boolean checkDiagonalWin(Cell cell, PlayerType playerType) {
        int inDiagonal = 0;
        int col = cell.getCol() - cell.getRow();
        for (int row = 0; row < SIZE; row++) {
            if (col >= 0 && col < SIZE) {
                if (board[row][col] == playerType) {
                    inDiagonal++;
                    if (inDiagonal == WIN_CONDITION) return true;
                } else {
                    inDiagonal = 0; // Reset count if a different type is encountered
                }
            }
            col++;
        }
        return false;
    }

    private boolean checkAntiDiagonalWin(Cell cell, PlayerType playerType) {
        int inDiagonal = 0;
        int col = cell.getCol() + cell.getRow();
        for (int row = 0; row < SIZE; row++) {
            if (col >= 0 && col < SIZE) {
                if (board[row][col] == playerType) {
                    inDiagonal++;
                    if (inDiagonal == WIN_CONDITION) return true;
                } else {
                    inDiagonal = 0; // Reset count if a different type is encountered
                }
            }
            col--;
        }
        return false;
    }

    public void printBoard() {
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == null) {
                    System.out.print("- ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }
            System.out.println();
        }

        // Add a space and the separator after the board
        System.out.println();  // Print an empty line for spacing
        System.out.println("----------");  // Print the separator
    }

    public List<Cell> getFreeCells() {
        List<Cell> freeCells = new ArrayList<>();
        for (int row = 0; row < SIZE; row++) {
            for (int col = 0; col < SIZE; col++) {
                if (board[row][col] == null) {
                    freeCells.add(new Cell(row, col));
                }
            }
        }
        return freeCells;
    }

    public boolean placeMark(Cell cell, PlayerType playerType) {
        if (cell.getRow() >= 0 && cell.getRow() < SIZE &&
                cell.getCol() >= 0 && cell.getCol() < SIZE &&
                board[cell.getRow()][cell.getCol()] == null) {
            board[cell.getRow()][cell.getCol()] = playerType;
            return true;
        }
        return false;
    }

    public boolean isBoardFull() {
        for (PlayerType[] row : board) {
            for (PlayerType cell : row) {
                if (cell == null) {
                    return false;
                }
            }
        }
        return true;
    }

    public PlayerType getCell(int row, int col) {
        if (row >= 0 && row < SIZE && col >= 0 && col < SIZE) {
            return board[row][col];
        } else {
            throw new IllegalArgumentException("Row or column is out of bounds.");
        }
    }

}
