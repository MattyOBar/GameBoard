package activity;

import activity.request.GetGroupsByPlayerRequest;
import activity.result.GetGroupsByPlayerResult;
import converters.ModelConverter;
import dynamodb.GroupDao;
import dynamodb.models.Group;
import models.GroupModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.inject.Inject;

public class GetGroupsByPlayerActivity {
    private final Logger log = LogManager.getLogger();
    private GroupDao groupDao;

    @Inject
    public GetGroupsByPlayerActivity(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public GetGroupsByPlayerResult handleRequest(final GetGroupsByPlayerRequest getGroupsByPlayerRequest) {
        log.info("Received GetGroupsByPlayerActivity {}", getGroupsByPlayerRequest);
        String playerId = getGroupsByPlayerRequest.getPlayerId();
        List<Group> groupList = groupDao.getGroupsByPlayer();
        List<GroupModel> playerIsIn = new ArrayList<>();
        for (Group group : groupList) {
            Set<String> playerIds = group.getPlayerIds();
            if (playerIds.contains(playerId)) {
                GroupModel groupModel = new ModelConverter().toGroupModel(group);
                playerIsIn.add(groupModel);
            }
        }
        return GetGroupsByPlayerResult.builder()
                .withGroupModelList(playerIsIn)
                .build();


    }
}
