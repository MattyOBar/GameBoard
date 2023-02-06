package activity;

import activity.request.GetGamesByGroupIdRequest;
import activity.result.GetGamesByGroupIdResult;
import converters.ModelConverter;
import dynamodb.GameDao;
import dynamodb.GroupDao;
import dynamodb.models.Game;
import dynamodb.models.Group;
import exceptions.GameOutcomeInvalidException;
import exceptions.GroupInvalidException;
import models.GameModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

/**
 * This API allows the user to retrieve a list of games that a specific group plays.
 */

public class GetGamesByGroupIdActivity {
    private final Logger log = LogManager.getLogger();
    private final GroupDao groupDao;

    private final GameDao gameDao;

    /**
     * Instantiates a GetGamesByGroupIdActivity object.
     * @param groupDao GroupDao to access the groups table.
     * @param gameDao GameDao to access the games table.
     */
    @Inject
    public GetGamesByGroupIdActivity(GroupDao groupDao, GameDao gameDao) {
        this.groupDao = groupDao;
        this.gameDao = gameDao;
    }

    /**
     * This method handles the incoming request by retrieving the groupId from the
     * request, making a call to the group table to retrieve the set of gameIds,
     * then making a call to the Games database for the specific game object.
     * @param getGamesByGroupIdRequest the request object containing the groupId.
     * @return a set of all the games played by the group.
     */
    public GetGamesByGroupIdResult handleRequest(final GetGamesByGroupIdRequest getGamesByGroupIdRequest) {
        log.info("Received GetGamesByGroupIdRequest {}", getGamesByGroupIdRequest);
        String groupId = getGamesByGroupIdRequest.getGroupId();
        if (groupId == null || !groupId.startsWith("GRP") ||
        groupId.length() != 8) {
            throw new GroupInvalidException("GroupId: " + groupId + " is invalid.");
        }
        Group group = groupDao.getGroup(groupId);
        Set<String> gameIds = group.getGameIds();
        Set<GameModel> gameSet = new HashSet<>();
        for (String gameId : gameIds) {
            Game game = gameDao.getGame(gameId);
            GameModel gameModel = new ModelConverter().toGameModel(game);
            gameSet.add(gameModel);
        }
        return GetGamesByGroupIdResult.builder()
                .withGameModelSet(gameSet)
                .build();
    }
}
