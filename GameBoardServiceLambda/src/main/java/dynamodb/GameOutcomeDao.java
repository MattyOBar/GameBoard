package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBQueryExpression;
import com.amazonaws.services.dynamodbv2.datamodeling.PaginatedQueryList;

import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import dynamodb.models.GameOutcome;
import exceptions.GameOutcomeNotFoundException;
import metrics.MetricsConstants;
import metrics.MetricsPublisher;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;


/**
 * Access data for our game objects.
 */
public class GameOutcomeDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instantiates a GameOutcomeDao object.
     * @param dynamoDBMapper the link used to interact with the GameOutcomes table.
     * @param metricsPublisher the link used to record metrics.
     */
    @Inject
    public GameOutcomeDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Makes a DynamoDb call to retrieve the specified GameOutcome.
     * @param gameOutcomeId the parameter that indicates the specified GameOutcome.
     * @return the GameOutcome object retrieved from DynamoDb.
     */
    public GameOutcome getGameOutcome(String gameOutcomeId) {
        GameOutcome gameOutcome = this.dynamoDBMapper.load(GameOutcome.class, gameOutcomeId);
        if (gameOutcome == null) {
            metricsPublisher.addCount(MetricsConstants.GETGAMEOUTCOME_GAMEOUTCOMENOTFOUND_COUNT, 1);
            throw new GameOutcomeNotFoundException();
        }
        metricsPublisher.addCount(MetricsConstants.GETGAMEOUTCOME_GAMEOUTCOMENOTFOUND_COUNT, 0);
        return gameOutcome;
    }

    /**
     *  Makes a DynamoDb call to save a gameOutcome object.
     * @param gameOutcome the GameOutcome object to be saved to DynamoDb.
     * @return the GameOutcome object that was saved to DynamoDb.
     */
    public GameOutcome saveGameOutcome(GameOutcome gameOutcome) {
        this.dynamoDBMapper.save(gameOutcome);
        return gameOutcome;
    }

    /**
     * This method retrieves a List of GameOutcomes for a specific group.
     * @param groupId the GroupId needed for the lookup.
     * @return the List of GameOutcomes specific to the groupId.
     */
    public List<GameOutcome> getGameOutcomesByGroupId(String groupId, String gameId) {
        Map<String, AttributeValue> valueMap = new HashMap<>();
        valueMap.put(":groupId", new AttributeValue().withS(groupId));
        valueMap.put(":gameId", new AttributeValue().withS(gameId));

        DynamoDBQueryExpression<GameOutcome> queryExpression = new DynamoDBQueryExpression<GameOutcome>()
                .withIndexName(GameOutcome.GROUPANDGAME_INDEX)
                .withConsistentRead(false)
                .withKeyConditionExpression("groupId = :groupId and gameId = :gameId")
//                .withFilterExpression("playerWinId = :playerWinId")
                .withExpressionAttributeValues(valueMap);

        PaginatedQueryList<GameOutcome> gameOutcomeList = dynamoDBMapper.query(GameOutcome.class, queryExpression);
        return gameOutcomeList;
    }

    /**
     * Deletes the specified GameOutcome.
     * @param gameOutcome the GameOutcome to delete.
     */
    public void deleteGameOutcome(GameOutcome gameOutcome) {
        gameOutcome.setGameId(gameOutcome.getGameOutcomeId());
        this.dynamoDBMapper.delete(gameOutcome);
    }

}
