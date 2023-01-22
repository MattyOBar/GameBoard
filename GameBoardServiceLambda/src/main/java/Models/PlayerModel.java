package Models;

import java.util.List;
import java.util.Objects;

public class PlayerModel {
    private final String playerId;

    private final String playerName;

    private final List<String> groupIds;

    private PlayerModel(String playerId, String playerName, List<String> groupIds) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.groupIds = groupIds;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public List<String> getGroupIds() {
        return groupIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerModel that = (PlayerModel) o;
        return Objects.equals(getPlayerId(), that.getPlayerId()) &&
                Objects.equals(getPlayerName(), that.getPlayerName()) &&
                Objects.equals(getGroupIds(), that.getGroupIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerId(), getPlayerName(), getGroupIds());
    }


    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }



    public static class Builder {
        private String playerId;
        private String playerName;
        private List<String> groupIds;

        public Builder withPlayerId(String playerId) {
            this.playerId = playerId;
            return this;
        }

        public Builder withPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public Builder withGroupIds(List<String> groupIds) {
            this.groupIds = groupIds;
            return this;
        }

        public PlayerModel build() {
            return new PlayerModel(playerId, playerName, groupIds);
        }
    }
}
