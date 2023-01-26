package activity.request;

import activity.result.UpdatePlayersGroupsInResult;
import converters.ModelConverter;
import dynamodb.GroupDao;
import dynamodb.PlayerDao;
import dynamodb.models.Player;
import metrics.MetricsPublisher;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;


public class UpdatePlayersGroupsInActivity {

    private final Logger log = LogManager.getLogger();
    private final PlayerDao playerDao;
    private final MetricsPublisher metricsPublisher;

    @Inject
    public UpdatePlayersGroupsInActivity(PlayerDao playerDao, MetricsPublisher metricsPublisher) {
        this.playerDao = playerDao;
        this.metricsPublisher = metricsPublisher;
    }

    public UpdatePlayersGroupsInResult handleRequest(final UpdatePlayersGroupsInRequest updatePlayersGroupsInRequest) {
        log.info("Received UpdatePlayersGroupsInRequest {}", updatePlayersGroupsInRequest);

        Player player = playerDao.getPlayer(updatePlayersGroupsInRequest.getPlayerId());

        player.setGroupIds(updatePlayersGroupsInRequest.getGroupIds());
        playerDao.savePlayer(player);
        return UpdatePlayersGroupsInResult.builder()
                .withPlayer(new ModelConverter().toPlayerModel(player))
                .build();
    }
}
