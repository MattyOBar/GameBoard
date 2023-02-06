package activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

import java.util.Set;

@JsonDeserialize(builder = CreateGameOutcomeRequest.Builder.class)
public class CreateGameOutcomeRequest {
    private final String gameOutcomeId;
    private final String groupId;
    private final String gameId;
    private final String playerWinId;
    private final Set<String> playerLoseIds;

    /**
     * Instantiates the request for creating a GameOutcome.
     * @param gameOutcomeId The unique GameOutcomeId.
     * @param groupId The groupId.
     * @param gameId GameId.
     * @param playerWinId playerWinId.
     * @param playerLoseIds playerLoseId set.
     */
    public CreateGameOutcomeRequest(String gameOutcomeId, String groupId, String gameId,
                                    String playerWinId, Set<String> playerLoseIds) {
        this.gameOutcomeId = gameOutcomeId;
        this.groupId = groupId;
        this.gameId = gameId;
        this.playerWinId = playerWinId;
        this.playerLoseIds = playerLoseIds;
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

    public Set<String> getPlayerLoseIds() {
        return playerLoseIds;
    }

    @Override
    public String toString() {
        return "CreateGameOutcomeRequest{" +
                "gameOutcomeId='" + gameOutcomeId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", gameId='" + gameId + '\'' +
                ", playerWinId='" + playerWinId + '\'' +
                ", playerLoseIds=" + playerLoseIds +
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
        private Set<String> playerLoseIds;

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

        public Builder withPlayerLoseIds(Set<String> playerLoseIds) {
            this.playerLoseIds = playerLoseIds;
            return this;
        }

        public CreateGameOutcomeRequest build() {
            return new CreateGameOutcomeRequest(gameOutcomeId, groupId, gameId, playerWinId, playerLoseIds);
        }
    }
}
