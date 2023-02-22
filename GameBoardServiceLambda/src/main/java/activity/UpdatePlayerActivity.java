package activity;

import activity.request.UpdatePlayerRequest;
import activity.result.UpdatePlayerResult;
import converters.ModelConverter;
import dynamodb.PlayerDao;
import dynamodb.models.Player;
import exceptions.PlayerInvalidException;
import models.PlayerModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdatePlayerActivity {
    private final Logger log = LogManager.getLogger();
    private final PlayerDao playerDao;

    /**
     * Instantiates a UpdatePlayerActivity object.
     * @param playerDao playerDao to access the players table.
     */
    @Inject
    public UpdatePlayerActivity(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    /**
     * This method handles the request by retrieving the specified player object, updating it, and saving it.
     * @param updatePlayerRequest the request with the updated data.
     * @return the UpdatePlayerResult object containing the API defined PlayerModel.
     */
    public UpdatePlayerResult handleRequest(final UpdatePlayerRequest updatePlayerRequest) {
        log.info("Received UpdatePlayerRequest {}", updatePlayerRequest);
        if (updatePlayerRequest.getPlayerId() == null) {
            throw new PlayerInvalidException();
        }

        Player player = playerDao.getPlayer(updatePlayerRequest.getPlayerId());
        if (updatePlayerRequest.getPlayerName() != null) {
            player.setPlayerName(updatePlayerRequest.getPlayerName());
        }
        if (updatePlayerRequest.getGroupIds() != null) {
            player.setGroupIds(updatePlayerRequest.getGroupIds());
        }

        playerDao.savePlayer(player);
        PlayerModel playerModel = new ModelConverter().toPlayerModel(player);

        return UpdatePlayerResult.builder().withPlayerModel(playerModel).build();
    }
}
