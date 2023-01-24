package dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "groups")
public class Group {
    private String groupId;
    private String groupName;
    private String favoriteGameId;
    private Set<String> gameIds;
    private Set<String> gameOutcomeIds;
    private Set<String> playerIds;

    @DynamoDBHashKey(attributeName = "groupId")
    public String getGroupId() {
        return groupId;
    }

    @DynamoDBAttribute(attributeName = "groupName")
    public String getGroupName() {
        return groupName;
    }

    @DynamoDBAttribute(attributeName = "favoriteGameId")
    public String getFavoriteGameId() {
        return favoriteGameId;
    }

    @DynamoDBAttribute(attributeName = "gameIds")
    public Set<String> getGameIds() {
        return gameIds;
    }

    @DynamoDBAttribute(attributeName = "gameOutcomeIds")
    public Set<String> getGameOutcomeIds() {
        return gameOutcomeIds;
    }

    @DynamoDBAttribute(attributeName = "playerIds")
    public Set<String> getPlayerIds() {
        return playerIds;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setFavoriteGameId(String favoriteGameId) {
        this.favoriteGameId = favoriteGameId;
    }

    public void setGameIds(Set<String> gameIds) {
        this.gameIds = gameIds;
    }

    public void setGameOutcomeIds(Set<String> gameOutcomeIds) {
        this.gameOutcomeIds = gameOutcomeIds;
    }

    public void setPlayerIds(Set<String> playerIds) {
        this.playerIds = playerIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Group group = (Group) o;
        return Objects.equals(getGroupId(), group.getGroupId()) &&
                Objects.equals(getGroupName(), group.getGroupName()) &&
                Objects.equals(getFavoriteGameId(), group.getFavoriteGameId()) &&
                Objects.equals(getGameIds(), group.getGameIds()) &&
                Objects.equals(getGameOutcomeIds(), group.getGameOutcomeIds()) &&
                Objects.equals(getPlayerIds(), group.getPlayerIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGroupId(), getGroupName(),
                getFavoriteGameId(), getGameIds(),
                getGameOutcomeIds(), getPlayerIds());
    }

    @Override
    public String toString() {
        return "Group{" +
                "groupId='" + groupId + '\'' +
                ", groupName='" + groupName + '\'' +
                ", favoriteGameId='" + favoriteGameId + '\'' +
                ", gameIds=" + gameIds +
                ", gameOutcomeIds=" + gameOutcomeIds +
                ", playerIds=" + playerIds +
                '}';
    }
}
