package dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Player;


public class PlayerDao {
    private final DynamoDBMapper dynamoDBMapper;
    public PlayerDao(DynamoDBMapper dynamoDBMapper) {
        this.dynamoDBMapper = dynamoDBMapper;
    }

    public Player getPlayer(String playerId) {
        Player player = this.dynamoDBMapper.load(Player.class, playerId);
        return player;
    }
}

