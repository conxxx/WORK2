package src;

public abstract class Game {
    protected Board board;
    protected PlayerType currentPlayer;

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public PlayerType getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(PlayerType currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public Cell getLastMove() {
        return lastMove;
    }

    public void setLastMove(Cell lastMove) {
        this.lastMove = lastMove;
    }

    protected boolean gameOver;
    protected Cell lastMove;

    public Game() {
        board = new Board();
        currentPlayer = PlayerType.X; // X starts by default
        gameOver = false;
    }

    public synchronized void printBoard() {
        board.printBoard();
    }

  //  public abstract boolean makeMove();

    public synchronized boolean isGameOver() {
        return gameOver;
    }

    public synchronized void declareWinner(PlayerType winner) {
        System.out.println("Player " + winner + " wins!");
        gameOver = true;
    }

    public synchronized void declareDraw() {
        System.out.println("The game is a draw!");
        gameOver = true;
    }

    protected synchronized void switchTurns() {
        currentPlayer = (currentPlayer == PlayerType.X) ? PlayerType.O : PlayerType.X;
    }

    public synchronized void updateGameState(Cell lastMove) {
        if (board.isWinningMove(lastMove)) {
            declareWinner(currentPlayer);
        } else if (board.isBoardFull()) {
            declareDraw();
        }
    }

    public abstract void startGame();

  //  public abstract boolean makeMove();
    // Other getters and setters...
}
