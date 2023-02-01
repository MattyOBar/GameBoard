package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;

import dynamodb.models.Game;
import exceptions.GameNotFoundException;
import metrics.MetricsConstants;
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

    /**
     * Makes a DynamoDB call to retrieve the specified Game.
     * @param gameId the parameter that indicates the specified Game.
     * @return the Game object retrieved from DynamoDB.
     */
    public Game getGame(String gameId) {
        Game game = this.dynamoDBMapper.load(Game.class, gameId);
        if (game == null) {
            metricsPublisher.addCount(MetricsConstants.GETGAME_GAMENOTFOUND_COUNT, 1);
            throw new GameNotFoundException();
        }
        metricsPublisher.addCount(MetricsConstants.GETGAME_GAMENOTFOUND_COUNT, 0);
        return game;
    }

    /**
     * Makes a DynamoDB call to save a game object.
     * @param game the Game object to be saved to DynamoDB.
     * @return the Game object is then returned.
     */
    public Game saveGame(Game game) {
        this.dynamoDBMapper.save(game);
        return game;
    }

}
