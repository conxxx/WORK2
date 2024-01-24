package src;

public class UserGame extends Game {
    private Player autonomousPlayer;
    private Player userPlayer;
    private Thread autonomousPlayerThread;

    public UserGame() {
        super();
        autonomousPlayer = new SelfPlayer(PlayerType.X, this); // Autonomous player
        userPlayer = new UserPlayer(PlayerType.O, this); // User-controlled player
    }

    public void startGame() {
        autonomousPlayerThread = new Thread(this::runAutonomousPlayer);
        autonomousPlayerThread.start();

        while (!gameOver) {
            printBoard();
            synchronized (this) {
                if (currentPlayer == PlayerType.O) {
                    userPlayer.makeMove();
                }
            }

            try {
                Thread.sleep(500); // Slight delay for turn
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }

        try {
            autonomousPlayerThread.join(); // Ensure autonomous player thread finishes
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    private void runAutonomousPlayer() {
        while (!isGameOver()) {
            if (currentPlayer == PlayerType.X) {
                autonomousPlayer.makeMove();
            }
            try {
                Thread.sleep(500); // Slight delay for turn
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}
