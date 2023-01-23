package activity.result;

import Models.PlayerModel;
import org.apache.commons.lang3.builder.Builder;

public class GetPlayerResult {
    private final PlayerModel playerModel;

    private GetPlayerResult(PlayerModel playerModel) {
        this.playerModel = playerModel;
    }
    public PlayerModel getPlayerModel() {
        return playerModel;
    }

    @Override
    public String toString() {
        return "GetPlayerResult{" +
                "playerModel=" + playerModel +
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

        public GetPlayerResult build() {
            return new GetPlayerResult(playerModel);
        }
    }
}
