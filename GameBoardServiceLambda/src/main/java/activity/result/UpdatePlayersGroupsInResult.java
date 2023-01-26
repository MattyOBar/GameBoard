package activity.result;

import models.PlayerModel;

public class UpdatePlayersGroupsInResult {
    private final PlayerModel player;

    private UpdatePlayersGroupsInResult(PlayerModel player) {
        this.player = player;
    }
    public PlayerModel getPlayer() {
        return player;
    }

    @Override
    public String toString() {
        return "UpdatePlayersGroupsInResult{" +
                "player=" + player +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PlayerModel player;

        public Builder withPlayer(PlayerModel player) {
            this.player = player;
            return this;
        }

        public UpdatePlayersGroupsInResult build() {
            return new UpdatePlayersGroupsInResult(player);
        }
    }
}
