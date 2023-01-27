package activity;

import activity.request.CreatePlayerRequest;
import activity.result.CreatePlayerResult;
import converters.ModelConverter;
import dynamodb.PlayerDao;
import dynamodb.models.Player;
import models.PlayerModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;
import java.util.Set;
import java.util.UUID;

public class CreatePlayerActivity {
    private final Logger log = LogManager.getLogger();
    private final PlayerDao playerDao;

    @Inject
    public CreatePlayerActivity(PlayerDao playerDao) {
        this.playerDao = playerDao;
    }

    public CreatePlayerResult handleRequest(final CreatePlayerRequest createPlayerRequest) {
        log.info("Received CreatePlayerRequest {}", createPlayerRequest);
        String playerId = UUID.randomUUID().toString().substring(0, 5);
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
