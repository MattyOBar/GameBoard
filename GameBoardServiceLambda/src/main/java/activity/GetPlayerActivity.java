package activity;

import activity.request.GetPlayerRequest;
import activity.result.GetPlayerResult;
import converters.ModelConverter;
import dynamodb.PlayerDao;
import dynamodb.models.Player;
import exceptions.PlayerInvalidException;
import models.PlayerModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * This API allows the user to retrieve a specific Player object.
 */
public class GetPlayerActivity {
    private final Logger log = LogManager.getLogger();
    private final PlayerDao playerDao;

    /**
     * Instantiates a GetPlayerActivity object.
     * @param playerDao PlayerDao to access the Players table.
     */
    @Inject
    public GetPlayerActivity(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }


    /**
     * This method handles the incoming request by retrieving the player from the database.
     * @param getPlayerRequest the request object containing the playerId.
     * @return getPlayerResult object containing the API defined PlayerModel.
     */
    public GetPlayerResult handleRequest(final GetPlayerRequest getPlayerRequest) {
        log.info("Received GetPlayerRequest {}", getPlayerRequest);
        String playerId = getPlayerRequest.getPlayerId();
        if (playerId == null || !playerId.startsWith("P") ||
                playerId.length() != 6) {
            throw new PlayerInvalidException("PlayerId " + playerId + " is invalid.");
        }
        Player player = playerDao.getPlayer(playerId);
        PlayerModel playerModel = new ModelConverter().toPlayerModel(player);
        return GetPlayerResult.builder()
                .withPlayer(playerModel)
                .build();
    }
}
