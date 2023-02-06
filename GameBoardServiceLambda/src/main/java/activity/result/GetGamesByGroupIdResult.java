package activity.result;

import models.GameModel;

import java.util.Set;

public class GetGamesByGroupIdResult {
    public final Set<GameModel> gameModelSet;

    /**
     * Result object that contains a set of gameModels.
     * @param gameModelSet gameModel set of games that belong to a specific group.
     */
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
