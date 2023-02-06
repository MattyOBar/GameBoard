package activity.request;

public class GetGroupsByPlayerRequest {
    private final String playerId;

    /**
     * Request object containing the PlayerId.
     * @param playerId the playerId.
     */
    public GetGroupsByPlayerRequest(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerId() {
        return playerId;
    }

    @Override
    public String toString() {
        return "GetGroupsByPlayerRequest{" +
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

        public GetGroupsByPlayerRequest build() {
            return new GetGroupsByPlayerRequest(playerId);
        }
    }
}
