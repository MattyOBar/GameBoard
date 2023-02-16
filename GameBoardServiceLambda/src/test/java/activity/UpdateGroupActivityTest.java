package activity;

import activity.request.UpdateGroupRequest;
import activity.result.UpdateGroupResult;
import dynamodb.GroupDao;
import dynamodb.models.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class UpdateGroupActivityTest {
    @Mock
    private GroupDao groupDao;

    private UpdateGroupActivity updateGroupActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        updateGroupActivity = new UpdateGroupActivity(groupDao);
    }

    @Test
    public void handleRequest_successfulRequest_ReturnsUpdatedGame(){
        // GIVEN
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

        String updatedGroupName = "Never Lose";
        Set<String> updatedGameIds = Set.of("GM00001", "GM00002", "GM00004", "GM00015");

        UpdateGroupRequest request = UpdateGroupRequest.builder()
                .withGroupId(groupId)
                .withGroupName(updatedGroupName)
                .withGameIds(updatedGameIds)
                .build();

        when(groupDao.getGroup(groupId)).thenReturn(group);

        // WHEN
        UpdateGroupResult result = updateGroupActivity.handleRequest(request);

        // THEN
        assertEquals(updatedGroupName, result.getGroupModel().getGroupName());
        assertEquals(updatedGameIds, result.getGroupModel().getGameIds());


    }
}
