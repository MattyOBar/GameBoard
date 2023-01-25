package models;

import java.util.Objects;
import java.util.Set;

public class GroupModel {
    private final String groupId;
    private final String groupName;
    private final String favoriteGameId;
    private final Set<String> gameIds;
    private final Set<String> gameOutcomeIds;
    private final Set<String> playerIds;

    private GroupModel(String groupId, String groupName, String favoriteGameId,
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GroupModel that = (GroupModel) o;
        return Objects.equals(getGroupId(), that.getGroupId()) &&
                Objects.equals(getGroupName(), that.getGroupName()) &&
                Objects.equals(getFavoriteGameId(), that.getFavoriteGameId()) &&
                Objects.equals(getGameIds(), that.getGameIds()) &&
                Objects.equals(getGameOutcomeIds(), that.getGameOutcomeIds()) &&
                Objects.equals(getPlayerIds(), that.getPlayerIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getGroupName(),
                getFavoriteGameId(), getGameIds(),
                getGameOutcomeIds(), getPlayerIds());
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

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
        public Builder withGroupFavoriteGame(String favoriteGameId) {
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

        public GroupModel build() {
            return new GroupModel(groupId, groupName, favoriteGameId, gameIds, gameOutcomeIds, playerIds);
        }


    }
}
