package activity;

import activity.request.GetGameOutcomesByGroupIdRequest;
import activity.result.GetGameOutcomesByGroupIdResult;
import converters.ModelConverter;
import dynamodb.GameOutcomeDao;
import dynamodb.GroupDao;
import dynamodb.models.GameOutcome;
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

    private final GroupDao groupDao;

    @Inject
    public GetGameOutcomesByGroupIdActivity(GameOutcomeDao gameOutcomeDao, GroupDao groupDao) {
        this.gameOutcomeDao = gameOutcomeDao;
        this.groupDao = groupDao;
    }

    public GetGameOutcomesByGroupIdResult handleRequest(final GetGameOutcomesByGroupIdRequest getGameOutcomesByGroupIdRequest) {
        log.info("Received GetGameOutcomesByGroupIdRequest {}", getGameOutcomesByGroupIdRequest);
        String groupId = getGameOutcomesByGroupIdRequest.getGroupId();
        if (groupId == null || !groupId.startsWith("GRP") || groupId.length() != 8) {
            throw new GroupInvalidException("GroupId: " + groupId + " is invalid!");
        }

        List<GameOutcome> gameOutcomeList = gameOutcomeDao.getGameOutcomesByGroupId(groupId);
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
