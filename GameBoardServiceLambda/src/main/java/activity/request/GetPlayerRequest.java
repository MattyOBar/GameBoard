package activity.request;

public class GetPlayerRequest {
    private final String playerId;

    private GetPlayerRequest(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "GetPlayerRequest{" +
                "playerId='" + playerId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private String playerId;

        public Builder withPlayerID(String playerId) {
            this.playerId = playerId;
            return this;
        }

        public GetPlayerRequest build() {
            return new GetPlayerRequest(playerId);
        }
    }
}
