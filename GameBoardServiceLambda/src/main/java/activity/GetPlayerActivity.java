package activity;

import Models.PlayerModel;
import activity.request.GetPlayerRequest;
import activity.result.GetPlayerResult;
import converters.ModelConverter;
import dynamodb.PlayerDao;
import dynamodb.models.Player;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetPlayerActivity {
    private final Logger log = LogManager.getLogger();
    private final PlayerDao playerDao;

    @Inject
    public GetPlayerActivity(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public GetPlayerResult handleRequest(final GetPlayerRequest getPlayerRequest) {
        log.info("Received GetPlayerRequest {}", getPlayerRequest);
        String playerId = getPlayerRequest.getPlayerId();
        Player player = playerDao.getPlayer(playerId);
        PlayerModel playerModel = new ModelConverter().toPlayerModel(player);
        return GetPlayerResult.builder()
                .withPlayer(playerModel)
                .build();
    }
}
