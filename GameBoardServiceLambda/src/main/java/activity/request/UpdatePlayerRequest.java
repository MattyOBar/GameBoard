package activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Set;

@JsonDeserialize(builder = UpdatePlayerRequest.Builder.class)
public class UpdatePlayerRequest {
    private String playerId;
    private String playerName;
    private Set<String> groupIds;


    /**
     * The request object for updating a player.
     * @param playerId The playerId
     * @param playerName The playerName
     * @param groupIds The groupIds
     */
    public UpdatePlayerRequest(String playerId, String playerName, Set<String> groupIds) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.groupIds = groupIds;
    }

    public String getPlayerId() {
        return playerId;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public Set<String> getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(Set<String> groupIds) {
        this.groupIds = groupIds;
    }

    @Override
    public String toString() {
        return "UpdatePlayerRequest{" +
                "playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", groupIds=" + groupIds +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String playerId;
        private String playerName;
        private Set<String> groupIds;

        public Builder withPlayerId(String playerId) {
            this.playerId = playerId;
            return this;
        }

        public Builder withPlayerName(String playerName) {
            this.playerName = playerName;
            return this;
        }

        public Builder withGroupIds(Set<String> groupIds) {
            this.groupIds = groupIds;
            return this;
        }

        public UpdatePlayerRequest build() {
            return new UpdatePlayerRequest(playerId, playerName, groupIds);
        }
    }
}
