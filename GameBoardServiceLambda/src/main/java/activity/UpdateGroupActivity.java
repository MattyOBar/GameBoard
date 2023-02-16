package activity;

import activity.request.UpdateGroupRequest;
import activity.result.UpdateGroupResult;
import converters.ModelConverter;
import dynamodb.GroupDao;
import dynamodb.models.Group;
import exceptions.GroupInvalidException;
import models.GroupModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class UpdateGroupActivity {
    private final Logger log = LogManager.getLogger();
    private final GroupDao groupDao;

    /**
     * Instantiates a UpdateGroupActivity object.
     * @param groupDao GroupDao to access the groups table.
     */
    @Inject
    public UpdateGroupActivity(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    /**
     * This method handles the request by retrieving the group object, updating it, and saving it back to the table.
     * @param updateGroupRequest The request object containing the data to update.
     * @return the UpdateGroupResult object containing the API defined GroupModel.
     */
    public UpdateGroupResult handleRequest(final UpdateGroupRequest updateGroupRequest) {
        log.info("Received UpdateGroupRequest {}", updateGroupRequest);
        if (updateGroupRequest.getGroupId() == null) {
            throw new GroupInvalidException();
        }

        Group group = groupDao.getGroup(updateGroupRequest.getGroupId());
        if (!group.getGroupId().equals(updateGroupRequest.getGroupId())) {
            throw new GroupInvalidException();
        }

        if (updateGroupRequest.getGroupName() != null) {
            group.setGroupName(updateGroupRequest.getGroupName());
        }

        if (updateGroupRequest.getFavoriteGameId() != null) {
            group.setFavoriteGameId(updateGroupRequest.getFavoriteGameId());
        }

        if (updateGroupRequest.getPlayerIds() != null) {
            group.setPlayerIds(updateGroupRequest.getPlayerIds());
        }

        if (updateGroupRequest.getGameIds() != null) {
            group.setGameIds(updateGroupRequest.getGameIds());
        }

        if (updateGroupRequest.getGameOutcomeIds() != null) {
            group.setGameOutcomeIds(updateGroupRequest.getGameOutcomeIds());
        }

        groupDao.saveGroup(group);
        GroupModel groupModel = new ModelConverter().toGroupModel(group);

        return UpdateGroupResult.builder()
                .withGroupModel(groupModel)
                .build();
    }
}
