package activity;

import activity.request.CreateGroupRequest;
import activity.result.CreateGroupResult;
import converters.ModelConverter;
import dynamodb.GroupDao;
import dynamodb.models.Group;
import models.GroupModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;

/**
 * This API allows a user to create a new Group object.
 */
public class CreateGroupActivity {
    private final Logger log = LogManager.getLogger();
    private final GroupDao groupDao;

    /**
     * Instantiates a CreateGroupActivity object.
     * @param groupDao GroupDao to access the Groups table.
     */
    @Inject
    public CreateGroupActivity(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    /**
     * This method handles the incoming request by building a new Group object.
     * @param createGroupRequest the request object containing the new Group object.
     * @return CreateGroupResult object containing the API defined GroupModel.
     */
    public CreateGroupResult handleRequest(final CreateGroupRequest createGroupRequest) {
        log.info("Received CreateGroupRequest {}", createGroupRequest);
        String groupId = "GRP".concat(UUID.randomUUID().toString().substring(0, 5));
        String groupName = createGroupRequest.getGroupName();
        String favoriteGameId = createGroupRequest.getFavoriteGameId();
        Set<String> gameIds = createGroupRequest.getGameIds();
        Set<String> gameOutcomeIds = createGroupRequest.getGameOutcomeIds();
        Set<String> playerIds = createGroupRequest.getPlayerIds();

        Group group = new Group();
        group.setGroupId(groupId);
        group.setGroupName(groupName);
        group.setFavoriteGameId(favoriteGameId);
        group.setGameIds(gameIds);
        group.setGameOutcomeIds(gameOutcomeIds);
        group.setPlayerIds(playerIds);

        groupDao.saveGroup(group);
        GroupModel groupModel = new ModelConverter().toGroupModel(group);

        return CreateGroupResult.builder()
                .withGroupModel(groupModel)
                .build();
    }

}
