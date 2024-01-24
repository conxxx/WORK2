package src;

import java.util.List;
import java.util.Random;

public class SelfPlayer extends Player {
    private Random random;

    public SelfPlayer(PlayerType playerType, Game game) {
        super(playerType, game);
        this.random = new Random();
    }

    @Override
    public synchronized Cell makeMove() {
        if (game.isGameOver() || game.getCurrentPlayer() != playerType) {
            return null;
        }

        List<Cell> freeCells = game.getBoard().getFreeCells();
        Cell selectedCell = null;
        if (!freeCells.isEmpty()) {
            selectedCell = freeCells.get(random.nextInt(freeCells.size()));
            game.getBoard().placeMark(selectedCell, playerType);
            game.updateGameState(selectedCell);
        }

        game.switchTurns();
        return selectedCell;
    }
}