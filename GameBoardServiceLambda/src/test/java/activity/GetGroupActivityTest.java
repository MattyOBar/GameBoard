package activity;

import activity.request.GetGroupRequest;
import activity.result.GetGroupResult;
import dynamodb.GroupDao;
import dynamodb.models.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetGroupActivityTest {
    @Mock
    private GroupDao groupDao;

    private GetGroupActivity getGroupActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getGroupActivity = new GetGroupActivity(groupDao);
    }

    @Test
    public void handleRequest_savedPlayerFound_ReturnsPlayerModelInResult() {
        //GIVEN
        String groupId = "GRP17232";
        String groupName = "testName";
        String favoriteGameId = "GM00001";
        Set<String> gameIds = Set.of("GM00001", "GM00002");
        Set<String> gameOutcomeIds = Set.of("GO00001", "GO01012");
        Set<String> playerIds = Set.of("P02321", "P02932");

        Group group = new Group();
        group.setGroupId(groupId);
        group.setGroupName(groupName);
        group.setFavoriteGameId(favoriteGameId);
        group.setGameIds(gameIds);
        group.setGameOutcomeIds(gameOutcomeIds);
        group.setPlayerIds(playerIds);

        when(groupDao.getGroup(groupId)).thenReturn(group);

        GetGroupRequest request = GetGroupRequest.builder()
                .withGroupID(groupId)
                .build();

        //WHEN
        GetGroupResult result = getGroupActivity.handleRequest(request);

        //THEN
        assertEquals(group.getGroupId(), result.getGroupModel().getGroupId());
        assertEquals(group.getGroupName(), result.getGroupModel().getGroupName());
        assertEquals(group.getFavoriteGameId(), result.getGroupModel().getFavoriteGameId());
        assertEquals(group.getGameIds(), result.getGroupModel().getGameIds());
        assertEquals(group.getGameOutcomeIds(), result.getGroupModel().getGameOutcomeIds());
        assertEquals(group.getPlayerIds(), result.getGroupModel().getPlayerIds());

    }
}
