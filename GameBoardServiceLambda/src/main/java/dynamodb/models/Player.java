package dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;
import java.util.Set;
@DynamoDBTable(tableName = "players")
public class Player {

    private String playerId;
    private String playerName;
    private Set<String> groupIds;

    @DynamoDBHashKey(attributeName = "playerId")
    public String getPlayerId() {
        return playerId;
    }

    @DynamoDBAttribute(attributeName = "playerName")
    public String getPlayerName() {
        return playerName;
    }

    @DynamoDBAttribute(attributeName = "groupIds")
    public Set<String> getGroupIds() {
        return groupIds;
    }
    
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setGroupIds(Set<String> groupIds) {
        this.groupIds = groupIds;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Player player = (Player) o;
        return Objects.equals(getPlayerId(), player.getPlayerId()) &&
                Objects.equals(getPlayerName(), player.getPlayerName()) &&
                Objects.equals(getGroupIds(), player.getGroupIds());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerId(), getPlayerName(), getGroupIds());
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", groupIds=" + groupIds +
                '}';
    }
}
