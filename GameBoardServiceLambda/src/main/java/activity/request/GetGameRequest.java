package activity.request;

public class GetGameRequest {
    private final String gameId;

    private GetGameRequest(String gameId) {
        this.gameId = gameId;
    }

    public String getGameId() {
        return gameId;
    }

    @Override
    public String toString() {
        return "GetGameRequest{" +
                "gameId='" + gameId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private String gameId;

        public Builder withGameID(String gameId) {
            this.gameId = gameId;
            return this;
        }

        public GetGameRequest build() {
            return new GetGameRequest(gameId);
        }
    }
}
