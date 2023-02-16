package activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Set;

@JsonDeserialize(builder = UpdateGroupRequest.Builder.class)
public class UpdateGroupRequest {
    private String groupId;
    private String groupName;
    private String favoriteGameId;
    private Set<String> gameIds;
    private Set<String> gameOutcomeIds;
    private Set<String> playerIds;

    /**
     * The request object for updating a group.
     * @param groupId the GroupId
     * @param groupName the GroupName
     * @param favoriteGameId the Group's favorite Game
     * @param gameIds the gameIds registered to the group.
     * @param gameOutcomeIds the gameOutcomeId's registered to the group.
     * @param playerIds the playerId's registered to the group.
     */
    public UpdateGroupRequest(String groupId, String groupName,
                              String favoriteGameId, Set<String> gameIds,
                              Set<String> gameOutcomeIds, Set<String> playerIds) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.favoriteGameId = favoriteGameId;
        this.gameIds = gameIds;
        this.gameOutcomeIds = gameOutcomeIds;
        this.playerIds = playerIds;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getFavoriteGameId() {
        return favoriteGameId;
    }

    public void setFavoriteGameId(String favoriteGameId) {
        this.favoriteGameId = favoriteGameId;
    }

    public Set<String> getGameIds() {
        return gameIds;
    }

    public void setGameIds(Set<String> gameIds) {
        this.gameIds = gameIds;
    }

    public Set<String> getGameOutcomeIds() {
        return gameOutcomeIds;
    }

    public void setGameOutcomeIds(Set<String> gameOutcomeIds) {
        this.gameOutcomeIds = gameOutcomeIds;
    }

    public Set<String> getPlayerIds() {
        return playerIds;
    }

    public void setPlayerIds(Set<String> playerIds) {
        this.playerIds = playerIds;
    }

    @Override
    public String toString() {
        return "UpdateGroupRequest{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", favoriteGameId='" + favoriteGameId + '\'' +
                ", gameIds=" + gameIds +
                ", gameOutcomeIds=" + gameOutcomeIds +
                ", playerIds=" + playerIds +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }
    @JsonPOJOBuilder
    public static class Builder {
        private String groupId;
        private String groupName;
        private String favoriteGameId;
        private Set<String> gameIds;
        private Set<String> gameOutcomeIds;
        private Set<String> playerIds;

        public Builder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder withGroupName(String groupName) {
            this.groupName = groupName;
            return this;
        }

        public Builder withFavoriteGameId(String favoriteGameId) {
            this.favoriteGameId = favoriteGameId;
            return this;
        }
        public Builder withGameIds(Set<String> gameIds) {
            this.gameIds = gameIds;
            return this;
        }

        public Builder withGameOutcomeIds(Set<String> gameOutcomeIds) {
            this.gameOutcomeIds = gameOutcomeIds;
            return this;
        }

        public Builder withPlayerIds(Set<String> playerIds) {
            this.playerIds = playerIds;
            return this;
        }

        public UpdateGroupRequest build() {
            return new UpdateGroupRequest(groupId, groupName, favoriteGameId, gameIds, gameOutcomeIds, playerIds);
        }
    }
}
