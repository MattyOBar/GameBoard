package activity.result;

import models.PlayerModel;

public class CreatePlayerResult {
    private final PlayerModel player;

    private CreatePlayerResult(PlayerModel player) {
        this.player = player;
    }

    public PlayerModel getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "CreatePlayerResult{" +
                "player=" + player +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private PlayerModel playerModel;

        public Builder withPlayer(PlayerModel playerModel) {
            this.playerModel = playerModel;
            return this;
        }
        public CreatePlayerResult build() {
            return new CreatePlayerResult(playerModel);
        }
    }
}
