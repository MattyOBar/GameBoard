package activity.result;

import models.GameOutcomeModel;

public class CreateGameOutcomeResult {
    private final GameOutcomeModel gameOutcomeModel;
    private CreateGameOutcomeResult(GameOutcomeModel gameOutcomeModel) {
        this.gameOutcomeModel = gameOutcomeModel;
    }

    public GameOutcomeModel getGameOutcomeModel() {
        return gameOutcomeModel;
    }

    @Override
    public String toString() {
        return "CreateGameOutcomeResult{" +
                "gameOutcomeModel=" + gameOutcomeModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GameOutcomeModel gameOutcomeModel;

        public Builder withGameOutcomeModel(GameOutcomeModel gameOutcomeModel) {
            this.gameOutcomeModel = gameOutcomeModel;
            return this;
        }

        public CreateGameOutcomeResult build() {
            return new CreateGameOutcomeResult(gameOutcomeModel);
        }
    }
}
