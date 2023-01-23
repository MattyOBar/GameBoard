package dynamodb;


import Exceptions.PlayerNotFoundException;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Player;
import metrics.MetricsConstants;
import metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Access data for our player objects.
 */
@Singleton
public class PlayerDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a PlayerDao object
     * @param dynamoDBMapper the link used to interact with the Players Table
     * @param metricsPublisher the link used to record metrics
     */
    @Inject
    public PlayerDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Makes a dynamoDb call to retrieve the specified Player.
     * @param playerId the parameter that indicates the specified Player.
     * @return the player object retrieved from DynamoDB.
     */
    public Player getPlayer(String playerId) {
        Player player = this.dynamoDBMapper.load(Player.class, playerId);
        if (player == null) {
            metricsPublisher.addCount(MetricsConstants.GETPLAYER_PLAYERNOTFOUND_COUNT, 1);
            throw new PlayerNotFoundException("Could not find player: " + playerId);
        }
        metricsPublisher.addCount(MetricsConstants.GETPLAYER_PLAYERNOTFOUND_COUNT, 0);
        return player;
    }


    /**
     * Makes a DynamoDB call to save the given player.
     * @param player the player used to specify the player to be saved.
     */
    public void savePlayer(Player player) {
        this.dynamoDBMapper.save(player);
    }

}

