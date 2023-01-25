package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Group;
import exceptions.GroupNotFoundException;
import metrics.MetricsConstants;
import metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GroupDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private MetricsPublisher metricsPublisher;

    private GroupDao groupDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        groupDao = new GroupDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getGroup_withGroupId_callsMapperWithPartitionKey() {
        // GIVEN
        String groupId = "groupId";
        when(dynamoDBMapper.load(Group.class, groupId)).thenReturn(new Group());

        // WHEN
        Group group = groupDao.getGroup(groupId);

        // THEN
        assertNotNull(group);
        verify(dynamoDBMapper).load(Group.class, groupId);
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETGROUP_GROUPNOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void getGroup_groupIdNotFound_throwsGroupNotFoundException() {
        // GIVEN
        String nonExistentGroupId = "NotReal";
        when(dynamoDBMapper.load(Group.class, nonExistentGroupId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(GroupNotFoundException.class, () -> groupDao.getGroup(nonExistentGroupId));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETGROUP_GROUPNOTFOUND_COUNT), anyDouble());
    }
}
