package activity;

import activity.request.GetGroupRequest;
import activity.result.GetGroupResult;
import converters.ModelConverter;
import dynamodb.GroupDao;
import dynamodb.models.Group;
import models.GroupModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * This API allows the user to retrieve a specific Group object.
 */

public class GetGroupActivity {

    private final Logger log = LogManager.getLogger();

    private final GroupDao groupDao;

    /**
     * Instantiates a GetGroupActivity object.
     * @param groupDao GroupDao to access the Groups table.
     */
    @Inject
    public GetGroupActivity(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    /**
     * his method handles the incoming request by retrieving the group from the database.
     * @param getGroupRequest the request object containing the groupId.
     * @return getGroupResult object containing the API defined GroupModel.
     */
    public GetGroupResult handleRequest(final GetGroupRequest getGroupRequest) {
        log.info("Received GetGroupRequest {}", getGroupRequest);
        String groupId = getGroupRequest.getGroupId();
        Group group = groupDao.getGroup(groupId);
        GroupModel groupModel = new ModelConverter().toGroupModel(group);
        return GetGroupResult.builder()
                .withGroup(groupModel)
                .build();
    }



}
