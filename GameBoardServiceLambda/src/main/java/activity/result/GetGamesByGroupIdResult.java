package activity.result;

import models.GameModel;

import java.util.Set;

public class GetGamesByGroupIdResult {
    public final Set<GameModel> gameModelSet;

    public GetGamesByGroupIdResult(Set<GameModel> gameModelSet) {
        this.gameModelSet = gameModelSet;
    }

    public Set<GameModel> getGameModelSet() {
        return gameModelSet;
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private Set<GameModel> gameModelSet;

        public Builder withGameModelSet(Set<GameModel> gameModelSet) {
            this.gameModelSet = gameModelSet;
            return this;
        }

        public GetGamesByGroupIdResult build() {
            return new GetGamesByGroupIdResult(gameModelSet);
        }
    }
}
