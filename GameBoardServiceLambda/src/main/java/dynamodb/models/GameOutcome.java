package dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.*;

import java.util.Objects;
import java.util.Set;

@DynamoDBTable(tableName = "gameOutcomes")
public class GameOutcome {
    public static final String GROUPANDGAME_INDEX = "GroupIdAndGameIdIndex";
    private String gameOutcomeId;
    private String groupId;
    private String gameId;
    private String playerWinId;

    @DynamoDBHashKey(attributeName = "gameOutcomeId")
    public String getGameOutcomeId() {
        return gameOutcomeId;
    }

    @DynamoDBAttribute(attributeName = "groupId")
    @DynamoDBIndexHashKey(globalSecondaryIndexNames = {GROUPANDGAME_INDEX})
    public String getGroupId() {
        return groupId;
    }

    @DynamoDBAttribute(attributeName = "gameId")
    @DynamoDBIndexRangeKey(globalSecondaryIndexNames = {GROUPANDGAME_INDEX})
    public String getGameId() {
        return gameId;
    }

    @DynamoDBAttribute(attributeName = "playerWinId")
    public String getPlayerWinId() {
        return playerWinId;
    }

    public void setGameOutcomeId(String gameOutcomeId) {
        this.gameOutcomeId = gameOutcomeId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    public void setPlayerWinId(String playerWinId) {
        this.playerWinId = playerWinId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameOutcome that = (GameOutcome) o;
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

    @Override
    public String toString() {
        return "GameOutcome{" +
                "gameOutcomeId='" + gameOutcomeId + '\'' +
                ", groupId='" + groupId + '\'' +
                ", gameId='" + gameId + '\'' +
                ", playerWinId='" + playerWinId +
                '}';
    }
}
