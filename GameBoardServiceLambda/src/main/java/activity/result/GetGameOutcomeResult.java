package activity.result;

import models.GameOutcomeModel;

public class GetGameOutcomeResult {
    private final GameOutcomeModel gameOutcomeModel;

    public GetGameOutcomeResult(GameOutcomeModel gameOutcomeModel) {
        this.gameOutcomeModel = gameOutcomeModel;
    }

    public GameOutcomeModel getGameOutcomeModel() {
        return gameOutcomeModel;
    }

    @Override
    public String toString() {
        return "GetGameOutcomeResult{" +
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

        public GetGameOutcomeResult build() {
            return new GetGameOutcomeResult(gameOutcomeModel);
        }
    }
}
