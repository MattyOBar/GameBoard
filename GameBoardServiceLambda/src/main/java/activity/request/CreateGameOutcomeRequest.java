package activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Set;

@JsonDeserialize(builder = CreateGameOutcomeRequest.Builder.class)
public class CreateGameOutcomeRequest {
    private final String gameOutcomeId;
    private final String groupId;
    private final String gameId;
    private final String playerWinId;

    /**
     * Instantiates the request for creating a GameOutcome.
     * @param gameOutcomeId The unique GameOutcomeId.
     * @param groupId The groupId.
     * @param gameId GameId.
     * @param playerWinId playerWinId.
     */
    public CreateGameOutcomeRequest(String gameOutcomeId, String groupId, String gameId,
                                    String playerWinId) {
        this.gameOutcomeId = gameOutcomeId;
        this.groupId = groupId;
        this.gameId = gameId;
        this.playerWinId = playerWinId;
    }

    public String getGameOutcomeId() {
        return gameOutcomeId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGameId() {
        return gameId;
    }

    public String getPlayerWinId() {
        return playerWinId;
    }

    @Override
    public String toString() {
        return "CreateGameOutcomeRequest{" +
                "gameOutcomeId='" + gameOutcomeId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", gameId='" + gameId + '\'' +
                ", playerWinId='" + playerWinId +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    public static class Builder {
        private String gameOutcomeId;
        private String groupId;
        private String gameId;
        private String playerWinId;

        public Builder withGameOutcomeId(String gameOutcomeId) {
            this.gameOutcomeId = gameOutcomeId;
            return this;
        }

        public Builder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder withGameId(String gameId) {
            this.gameId = gameId;
            return this;
        }

        public Builder withPlayerWinId(String playerWinId) {
            this.playerWinId = playerWinId;
            return this;
        }

        public CreateGameOutcomeRequest build() {
            return new CreateGameOutcomeRequest(gameOutcomeId, groupId, gameId, playerWinId);
        }
    }
}
