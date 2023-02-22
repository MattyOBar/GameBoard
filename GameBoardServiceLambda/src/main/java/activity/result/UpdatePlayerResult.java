package activity.result;

import models.PlayerModel;

public class UpdatePlayerResult {
    private final PlayerModel playerModel;

    /**
     * The result object for the UpdatePlayerActivity.
     * @param playerModel the updated playerModel.
     */
    public UpdatePlayerResult(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }

    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    @Override
    public String toString() {
        return "UpdatePlayerResult{" +
                "playerModel=" + playerModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private PlayerModel playerModel;

        public Builder withPlayerModel(PlayerModel playerModel){
            this.playerModel = playerModel;
            return this;
        }

        public UpdatePlayerResult build() {
            return new UpdatePlayerResult(playerModel);
        }
    }
}
