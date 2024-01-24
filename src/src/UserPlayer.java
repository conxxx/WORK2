package src;

import java.util.Scanner;

public class UserPlayer extends Player {
  private Scanner scanner ;
    public UserPlayer(PlayerType playerType, Game game) {
        super(playerType, game);
     this.scanner = new Scanner(System.in);
    }

    @Override
    public synchronized Cell makeMove() {
        if (game.isGameOver() || game.getCurrentPlayer() != playerType) {
            return null;
        }

        Cell selectedCell = getUserInputForMove();
        game.getBoard().placeMark(selectedCell, playerType);
        game.updateGameState(selectedCell);

        game.getTurn();
        return selectedCell;
    }
    private Cell getUserInputForMove() {
        int row, col;
        boolean validInput = false;
        Cell cell = null;
        while (!validInput) {
            System.out.print("Enter row and column numbers (0-4) separated by space: ");
            if (scanner.hasNextInt()) {
                row = scanner.nextInt();
                if (scanner.hasNextInt()) {
                    col = scanner.nextInt();
                    if (isValidPosition(row, col)) {
                        cell = new Cell(row, col);
                        validInput = true;
                    } else {
                        System.out.println("Invalid position, try again.");
                    }
                } else {
                    System.out.println("Invalid input, please enter numbers.");
                    scanner.next(); // Consume the invalid token
                }
            } else {
                System.out.println("Invalid input, please enter numbers.");
                scanner.next(); // Consume the invalid token
            }
        }
        return cell;
    }
    private boolean isValidPosition(int row, int col) {
        return row >= 0 && row < Board.SIZE && col >= 0 && col < Board.SIZE
                && game.getBoard().getCell(row, col) == null;
    }
}