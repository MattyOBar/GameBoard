package activity;

import activity.request.CreatePlayerRequest;
import activity.result.CreatePlayerResult;
import converters.ModelConverter;
import dynamodb.PlayerDao;
import dynamodb.models.Player;
import models.PlayerModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;

/**
 * This API allows a user to create a new Player object.
 */

public class CreatePlayerActivity {
    private final Logger log = LogManager.getLogger();
    private final PlayerDao playerDao;

    /**
     * Instantiates a CreatePlayerActivity object.
     * @param playerDao PlayerDao to access the Players table.
     */
    @Inject
    public CreatePlayerActivity(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    /**
     * This method handles the incoming request by building a new Player object and saving it to the PlayersDao.
     * @param createPlayerRequest the request object containing the new Player data.
     * @return CreatePlayerResult object containing the API defined PlayerModel.
     */
    public CreatePlayerResult handleRequest(final CreatePlayerRequest createPlayerRequest) {
        log.info("Received CreatePlayerRequest {}", createPlayerRequest);
        String playerId = createPlayerRequest.getPlayerId();
        String playerName = createPlayerRequest.getPlayerName();
        Set<String> groupIds = createPlayerRequest.getGroupIds();

        Player player = new Player();
        player.setPlayerId(playerId);
        player.setPlayerName(playerName);
        player.setGroupIds(groupIds);

        playerDao.savePlayer(player);
        PlayerModel playerModel = new ModelConverter().toPlayerModel(player);

        return CreatePlayerResult.builder()
                .withPlayer(playerModel)
                .build();
    }

}
