package activity.result;

import models.GameModel;

public class CreateGameResult {
    private final GameModel gameModel;

    private CreateGameResult(GameModel gameModel) {
        this.gameModel = gameModel;
    }

    public GameModel getGameModel() {
        return gameModel;
    }

    @Override
    public String toString() {
        return "CreateGameResult{" +
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

        public CreateGameResult build() {
            return new CreateGameResult(gameModel);
        }
    }
}
