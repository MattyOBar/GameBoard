package activity;

import activity.request.CreateGroupRequest;
import activity.result.CreateGroupResult;
import dynamodb.GroupDao;
import dynamodb.models.Group;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateGroupActivityTest {

    @Mock
    private GroupDao groupDao;

    private CreateGroupActivity createGroupActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        createGroupActivity = new CreateGroupActivity(groupDao);
    }

    @Test
    public void handleRequest_CreatesAndSavesNewGroup_ReturnsCreatedGroup() {
        //GIVEN
        String groupName = "testname";
        String favoriteGameId = "GME001";
        Set<String> gameIds = Set.of("GME001", "GME002", "GME003");
        Set<String> gameOutcomeIds = Set.of("GO001", "GO002", "GO003");
        Set<String> playerIds = Set.of("P001", "P002", "P003");


        CreateGroupRequest request = CreateGroupRequest.builder()
                .withGroupName(groupName)
                .withFavoriteGameId(favoriteGameId)
                .withGameIds(gameIds)
                .withGameOutcomeIds(gameOutcomeIds)
                .withPlayerIds(playerIds)
                .build();

        //WHEN
        CreateGroupResult result = createGroupActivity.handleRequest(request);

        //THEN
        verify(groupDao).saveGroup(any(Group.class));
        assertNotNull(result.getGroupModel().getGroupId());
        assertEquals(groupName, result.getGroupModel().getGroupName());
        assertEquals(favoriteGameId, result.getGroupModel().getFavoriteGameId());
        assertEquals(gameIds, result.getGroupModel().getGameIds());
        assertEquals(gameOutcomeIds, result.getGroupModel().getGameOutcomeIds());
        assertEquals(playerIds, result.getGroupModel().getPlayerIds());
    }
}
