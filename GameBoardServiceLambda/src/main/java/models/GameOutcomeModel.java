package models;

import java.util.Objects;
import java.util.Set;

public class GameOutcomeModel {
    private final String gameOutcomeId;
    private final String groupId;
    private final String gameId;
    private final String playerWinId;


    private GameOutcomeModel(String gameOutcomeId, String groupId, String gameId,
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameOutcomeModel that = (GameOutcomeModel) o;
        return Objects.equals(getGameOutcomeId(), that.getGameOutcomeId()) &&
                Objects.equals(getGroupId(), that.getGroupId()) &&
                Objects.equals(getGameId(), that.getGameId()) &&
                Objects.equals(getPlayerWinId(), that.getPlayerWinId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameOutcomeId(), getGroupId(), getGameId(),
                getPlayerWinId());
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

        public Builder withGroupID(String groupId) {
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

        public GameOutcomeModel build() {
            return new GameOutcomeModel(gameOutcomeId, groupId, gameId, playerWinId);
        }

    }
}
