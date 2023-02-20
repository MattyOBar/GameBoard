package activity;

import activity.request.GetGameOutcomesByGroupIdRequest;
import activity.result.GetGameOutcomesByGroupIdResult;
import converters.ModelConverter;
import dynamodb.GameOutcomeDao;
import dynamodb.models.GameOutcome;
import exceptions.GameInvalidException;
import exceptions.GroupInvalidException;
import models.GameOutcomeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;


public class GetGameOutcomesByGroupIdActivity {
    private final Logger log = LogManager.getLogger();

    private final GameOutcomeDao gameOutcomeDao;

    /**
     * Instantiates a GetGameOutcomesByGroupIdActivity object.
     * @param gameOutcomeDao GameOutcomeDao to access the GameOutcomes table.
     */
    @Inject
    public GetGameOutcomesByGroupIdActivity(GameOutcomeDao gameOutcomeDao) {
        this.gameOutcomeDao = gameOutcomeDao;
    }

    /**
     * This method handles the incoming request by retrieving all the GameOutcomes that relate to a specific groupId.
     * @param getGameOutcomesByGroupIdRequest the request object containing the groupId.
     * @return getGameOutcomesByGroupIdResult the object containing the list of GameOutcomes
     */
    public GetGameOutcomesByGroupIdResult handleRequest(final GetGameOutcomesByGroupIdRequest getGameOutcomesByGroupIdRequest) {
        log.info("Received GetGameOutcomesByGroupIdRequest {}", getGameOutcomesByGroupIdRequest);
        String groupId = getGameOutcomesByGroupIdRequest.getGroupId();
        if (groupId == null || !groupId.startsWith("GRP") || groupId.length() != 8) {
            throw new GroupInvalidException("GroupId: " + groupId + " is invalid!");
        }
        String gameId = getGameOutcomesByGroupIdRequest.getGameId();
        if (gameId == null || !gameId.startsWith("GM") || gameId.length() != 7) {
            throw new GameInvalidException("GameId: " + gameId + " is invalid!");
        }

        List<GameOutcome> gameOutcomeList = gameOutcomeDao.getGameOutcomesByGroupId(groupId, gameId);
        List<GameOutcomeModel> gameOutcomeModelList = new ArrayList<>();
        for (GameOutcome gameOutcome : gameOutcomeList) {
            GameOutcomeModel gameOutcomeModel = new ModelConverter().toGameOutcomeModel(gameOutcome);
            gameOutcomeModelList.add(gameOutcomeModel);
        }

        return GetGameOutcomesByGroupIdResult.builder()
               .withGameOutcomeModelList(gameOutcomeModelList)
               .build();
    }
}
