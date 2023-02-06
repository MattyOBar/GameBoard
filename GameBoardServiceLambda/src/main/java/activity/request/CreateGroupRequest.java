package activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

import java.util.Set;

@JsonDeserialize(builder = CreateGroupRequest.Builder.class)
public class CreateGroupRequest {
    private final String groupId;
    private final String groupName;
    private final String favoriteGameId;
    private final Set<String> gameIds;
    private final Set<String> gameOutcomeIds;
    private final Set<String> playerIds;

    /**
     * Request object for creating a new Group object.
     * @param groupId The unique groupId.
     * @param groupName The name of the group.
     * @param favoriteGameId Id for the group's favorite game to play
     * @param gameIds Ids for all of the games that the group plays.
     * @param gameOutcomeIds Ids for all of the GameOutcomes relating to the group.
     * @param playerIds Ids for all the players in the group.
     */
    public CreateGroupRequest(String groupId, String groupName, String favoriteGameId,
                              Set<String> gameIds, Set<String> gameOutcomeIds, Set<String> playerIds) {
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

    public String getGroupName() {
        return groupName;
    }

    public String getFavoriteGameId() {
        return favoriteGameId;
    }

    public Set<String> getGameIds() {
        return gameIds;
    }

    public Set<String> getGameOutcomeIds() {
        return gameOutcomeIds;
    }

    public Set<String> getPlayerIds() {
        return playerIds;
    }

    @Override
    public String toString() {
        return "CreateGroupRequest{" +
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

        public CreateGroupRequest build() {
            return new CreateGroupRequest(groupId, groupName, favoriteGameId, gameIds, gameOutcomeIds, playerIds);
        }
    }
}
