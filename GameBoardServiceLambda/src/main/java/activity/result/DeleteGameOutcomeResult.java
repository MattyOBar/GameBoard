package activity.result;

import models.GameOutcomeModel;

public class DeleteGameOutcomeResult {
    private final GameOutcomeModel gameOutcome;

    public DeleteGameOutcomeResult(GameOutcomeModel gameOutcome) {
        this.gameOutcome = gameOutcome;
    }

    public GameOutcomeModel getGameOutcome() {
        return gameOutcome;
    }

    @Override
    public String toString() {
        return "DeleteGameOutcomeResult{" +
                "gameOutcome=" + gameOutcome +
                '}';
    }
    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private static GameOutcomeModel gameOutcome;

        public Builder withGameOutcome(GameOutcomeModel gameOutcome) {
            this.gameOutcome = gameOutcome;
            return this;
        }

        public DeleteGameOutcomeResult build() {
            return new DeleteGameOutcomeResult(gameOutcome);
        }
    }
}
