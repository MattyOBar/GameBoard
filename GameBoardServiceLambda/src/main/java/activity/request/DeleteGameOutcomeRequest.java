package activity.request;

public class DeleteGameOutcomeRequest {
    private String gameOutcomeId;

    private DeleteGameOutcomeRequest(String gameOutcomeId) {
        this.gameOutcomeId = gameOutcomeId;
    }

    public String getGameOutcomeId() {
        return gameOutcomeId;
    }

    public void setGameOutcomeId(String gameOutcomeId) {
        this.gameOutcomeId = gameOutcomeId;
    }

    @Override
    public String toString() {
        return "DeleteGameOutcomeRequest{" +
                "gameOutcomeId='" + gameOutcomeId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String gameOutcomeId;

        public Builder withGameOutcomeId(String gameOutcomeId) {
            this.gameOutcomeId = gameOutcomeId;
            return this;
        }

        public DeleteGameOutcomeRequest build() {
            return new DeleteGameOutcomeRequest(gameOutcomeId);
        }
    }
}
