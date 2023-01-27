package dynamodb;


import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Game;
import metrics.MetricsPublisher;

import javax.inject.Inject;

/**
 * Access data for our game objects.
 */
public class GameDao {
    private final DynamoDBMapper dynamoDBMapper;

    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a GameDao object.
     * @param dynamoDBMapper the link used to interact with the Games table.
     * @param metricsPublisher the link used to record metrics.
     */
    @Inject
    public GameDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    public Game getGame(String gameId)


}
