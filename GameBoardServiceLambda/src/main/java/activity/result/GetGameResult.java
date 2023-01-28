package activity.result;

import models.GameModel;

public class GetGameResult {
    private final GameModel gameModel;

    private GetGameResult(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    @Override
    public String toString() {
        return "GetGameResult{" +
                "gameModel=" + gameModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GameModel gameModel;

        public Builder withGameModel(GameModel gameModel) {
            this.gameModel = gameModel;
            return this;
        }

        public GetGameResult build() {
            return new GetGameResult(gameModel);
        }
    }
}
