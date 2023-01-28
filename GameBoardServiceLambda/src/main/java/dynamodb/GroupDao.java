package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Group;
import exceptions.GroupNotFoundException;
import metrics.MetricsConstants;
import metrics.MetricsPublisher;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Access data for our group objects.
 */
@Singleton
public class GroupDao {
    private final DynamoDBMapper dynamoDBMapper;
    private final MetricsPublisher metricsPublisher;

    /**
     * Instaniates a GroupDao object.
     * @param dynamoDBMapper the link used to interact with the Players table.
     * @param metricsPublisher the link used to record metrics.
     */
    @Inject
    public GroupDao(DynamoDBMapper dynamoDBMapper, MetricsPublisher metricsPublisher) {
        this.dynamoDBMapper = dynamoDBMapper;
        this.metricsPublisher = metricsPublisher;
    }

    /**
     * Makes a dynamodb call to retrieve the specified Group.
     * @param groupId the parameter that indicates the specified Group.
     * @return the group object retrieved from DynamoDB.
     */
    public Group getGroup(String groupId) {
        Group group = this.dynamoDBMapper.load(Group.class, groupId);
        if (group == null) {
            metricsPublisher.addCount(MetricsConstants.GETGROUP_GROUPNOTFOUND_COUNT, 1);
            throw new GroupNotFoundException("Could not find group: " + groupId);
        }
        metricsPublisher.addCount(MetricsConstants.GETGROUP_GROUPNOTFOUND_COUNT, 0);
        return group;
    }

    /**
     * Makes a DynamoDB call to save a group object.
     * @param group the Group object to be saved to DynamoDB.
     * @return the Group object is then returned.
     */
    public Group saveGroup(Group group) {
        this.dynamoDBMapper.save(group);
        return group;
    }
}
