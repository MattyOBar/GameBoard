package activity.request;

public class GetGameOutcomeRequest {
    private final String gameOutcomeId;

    private GetGameOutcomeRequest(String gameOutcomeId) {
        this.gameOutcomeId = gameOutcomeId;
    }

    public String getGameOutcomeId() {
        return gameOutcomeId;
    }

    @Override
    public String toString() {
        return "GetGameOutcomeRequest{" +
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

        public GetGameOutcomeRequest build() {
            return new GetGameOutcomeRequest(gameOutcomeId);
        }
    }
}
