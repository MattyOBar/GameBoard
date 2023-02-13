package activity;

import activity.request.CreatePlayerRequest;
import activity.result.CreatePlayerResult;
import dynamodb.PlayerDao;
import dynamodb.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreatePlayerActivityTest {
    @Mock
    private PlayerDao playerDao;

    private CreatePlayerActivity createPlayerActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        createPlayerActivity = new CreatePlayerActivity(playerDao);
    }

    @Test
    public void handleRequest_CreatesAndSavesNewPlayer_ReturnCreatedPlayer() {
        //GIVEN
        String expectedPlayerId = "testId@gmail.com";
        String expectedPlayerName = "TestName";
        Set<String> expectedGroupIds = Set.of("G0001", "G0002", "G0003");

        CreatePlayerRequest request = CreatePlayerRequest.builder()
                                            .withPlayerId(expectedPlayerId)
                                            .withPlayerName(expectedPlayerName)
                                            .withGroupIds(expectedGroupIds)
                                            .build();
        //WHEN
        CreatePlayerResult result = createPlayerActivity.handleRequest(request);

        //THEN
        verify(playerDao).savePlayer(any(Player.class));

        assertEquals(expectedPlayerId, result.getPlayer().getPlayerId());
        assertEquals(expectedPlayerName, result.getPlayer().getPlayerName());
        assertEquals(expectedGroupIds, result.getPlayer().getGroupIds());
    }
}
