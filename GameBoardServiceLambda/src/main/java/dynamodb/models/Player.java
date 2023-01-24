package dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.List;
import java.util.Objects;
import java.util.Set;
@DynamoDBTable(tableName = "players")
public class Player {

    private String playerId;
    private String playerName;
    private Set<String> groups;

    @DynamoDBHashKey(attributeName = "playerId")
    public String getPlayerId() {
        return playerId;
    }

    @DynamoDBAttribute(attributeName = "playerName")
    public String getPlayerName() {
        return playerName;
    }

    @DynamoDBAttribute(attributeName = "groupIds")
    public Set<String> getGroups() {
        return groups;
    }
    
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return Objects.equals(getPlayerId(), player.getPlayerId()) &&
                Objects.equals(getPlayerName(), player.getPlayerName()) &&
                Objects.equals(getGroups(), player.getGroups());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPlayerId(), getPlayerName(), getGroups());
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", groups=" + groups +
                '}';
    }
}
