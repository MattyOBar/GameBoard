package activity;

import activity.request.GetGroupsByPlayerIdRequest;
import activity.result.GetGroupsByPlayerIdResult;
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

/**
 * This API allows the user to retrieve a set of all the groups a player belongs to.
 */


public class GetGroupsByPlayerIdActivity {
    private final Logger log = LogManager.getLogger();
    private GroupDao groupDao;

    /**
     * Instantiates a GetGroupsByPlayerActivity object.
     * @param groupDao GroupDao to access the groups table.
     */
    @Inject
    public GetGroupsByPlayerIdActivity(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    /**
     * This method handles the request by returning the Groups that contain the playerId.
     * @param getGroupsByPlayerRequest the request object containing the playerId.
     * @return the group objects that contain the playerId.
     */
    public GetGroupsByPlayerIdResult handleRequest(final GetGroupsByPlayerIdRequest getGroupsByPlayerRequest) {
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
        return GetGroupsByPlayerIdResult.builder()
                .withGroupModelList(playerIsIn)
                .build();


    }
}
