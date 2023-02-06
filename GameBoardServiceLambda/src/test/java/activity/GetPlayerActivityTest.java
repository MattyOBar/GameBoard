package activity;

import activity.request.GetPlayerRequest;
import activity.result.GetPlayerResult;
import dynamodb.PlayerDao;
import dynamodb.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mock;


import java.util.Set;



import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetPlayerActivityTest {
    @Mock
    private PlayerDao playerDao;

    private GetPlayerActivity getPlayerActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getPlayerActivity = new GetPlayerActivity(playerDao);
    }

    @Test
    public void handleRequest_savedPlayerFound_ReturnsPlayerModelInResult() {
        //GIVEN
        String playerId = "testId";
        String playerName = "testName";
        Set<String> groupIds = Set.of("groupId1", "groupId2");

        Player player = new Player();
        player.setPlayerId(playerId);
        player.setPlayerName(playerName);
        player.setGroupIds(groupIds);

        when(playerDao.getPlayer(playerId)).thenReturn(player);

        GetPlayerRequest request = GetPlayerRequest.builder()
                .withPlayerID(playerId)
                .build();

        //WHEN
        GetPlayerResult result = getPlayerActivity.handleRequest(request);

        //THEN
        assertEquals(playerId, result.getPlayerModel().getPlayerId());
        assertEquals(playerName, result.getPlayerModel().getPlayerName());
        assertEquals(groupIds, result.getPlayerModel().getGroupIds());
    }


}
