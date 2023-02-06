package activity;

import activity.request.GetGroupsByPlayerRequest;
import activity.result.GetGroupsByPlayerResult;
import converters.ModelConverter;
import dynamodb.GroupDao;
import dynamodb.models.Group;
import exceptions.PlayerInvalidException;
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


public class GetGroupsByPlayerActivity {
    private final Logger log = LogManager.getLogger();
    private GroupDao groupDao;

    /**
     * Instantiates a GetGroupsByPlayerActivity object.
     * @param groupDao GroupDao to access the groups table.
     */
    @Inject
    public GetGroupsByPlayerActivity(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    /**
     * This method handles the request by returning the Groups that contain the playerId.
     * @param getGroupsByPlayerRequest the request object containing the playerId.
     * @return the group objects that contain the playerId.
     */
    public GetGroupsByPlayerResult handleRequest(final GetGroupsByPlayerRequest getGroupsByPlayerRequest) {
        log.info("Received GetGroupsByPlayerActivity {}", getGroupsByPlayerRequest);
        String playerId = getGroupsByPlayerRequest.getPlayerId();
        if (playerId == null || !playerId.startsWith("P") ||
        playerId.length() != 7) {
            throw new PlayerInvalidException("PlayerId: " + playerId + " is invalid.");
        }
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
