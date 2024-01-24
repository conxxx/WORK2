package X0;

public abstract class Player {
    protected PlayerType playerType;
    protected Game game;
    public abstract Cell makeMove();
    public Player(PlayerType playerType, Game game) {
        this.playerType = playerType;
        this.game = game;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }



    // Other common player methods can be added here
}
