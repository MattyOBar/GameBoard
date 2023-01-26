package dynamodb.models;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

import java.util.Objects;

@DynamoDBTable(tableName = "games")
public class Game {
    private String gameId;
    private String gameName;
    private String rulesLink;
    private String purchaseLink;

    @DynamoDBHashKey(attributeName = "gameId")
    public String getGameId() {
        return gameId;
    }

    public void setGameId(String gameId) {
        this.gameId = gameId;
    }

    @DynamoDBAttribute(attributeName = "gameName")
    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @DynamoDBAttribute(attributeName = "rulesLink")
    public String getRulesLink() {
        return rulesLink;
    }

    public void setRulesLink(String rulesLink) {
        this.rulesLink = rulesLink;
    }

    @DynamoDBAttribute(attributeName = "purchaseLink")
    public String getPurchaseLink() {
        return purchaseLink;
    }

    public void setPurchaseLink(String purchaseLink) {
        this.purchaseLink = purchaseLink;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Game game = (Game) o;
        return Objects.equals(getGameId(), game.getGameId()) &&
                Objects.equals(getGameName(), game.getGameName()) &&
                Objects.equals(getRulesLink(), game.getRulesLink()) &&
                Objects.equals(getPurchaseLink(), game.getPurchaseLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getGameName(),
                getRulesLink(), getPurchaseLink());
    }

    @Override
    public String toString() {
        return "Game{" +
                "gameId='" + gameId + '\'' +
                ", gameName='" + gameName + '\'' +
                ", rulesLink='" + rulesLink + '\'' +
                ", purchaseLink='" + purchaseLink + '\'' +
                '}';
    }
}
