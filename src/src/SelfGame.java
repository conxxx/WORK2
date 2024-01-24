package src;

public class SelfGame extends Game {
    private Player player1;
    private Player player2;

    public SelfGame() {
        super();
        player1 = new SelfPlayer(PlayerType.X, this);
        player2 = new SelfPlayer(PlayerType.O, this);
    }

    @Override
    public void startGame() {
        Thread player1Thread = new Thread(() -> runPlayer(player1));
        Thread player2Thread = new Thread(() -> runPlayer(player2));

        player1Thread.start();
        player2Thread.start();

        try {
            player1Thread.join();
            player2Thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private void runPlayer(Player player) {
        while (!isGameOver()) {
            Cell move = player.makeMove();
            if (move != null) {
                printBoard();
            }
            try {
                Thread.sleep(500); // Slight delay for turn
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
    }
}