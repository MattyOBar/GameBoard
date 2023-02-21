package activity;

import activity.request.CreateGameOutcomeRequest;
import activity.request.GetGameOutcomeRequest;
import activity.result.GetGameOutcomeResult;
import dynamodb.GameOutcomeDao;
import dynamodb.models.GameOutcome;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetGameOutcomeActivityTest {

    @Mock
    private GameOutcomeDao gameOutcomeDao;

    private GetGameOutcomeActivity getGameOutcomeActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getGameOutcomeActivity = new GetGameOutcomeActivity(gameOutcomeDao);
    }

    @Test
    public void handleRequest_SavedGameOutcomeFound_ReturnsGameOutcomeModel() {
        //GIVEN
        String gameOutcomeId = "GO00001";
        String gameId = "GM00001";
        String groupId = "GRP00001";
        String playerWinId = "P00001";

        GameOutcome gameOutcome = new GameOutcome();
        gameOutcome.setGameOutcomeId(gameOutcomeId);
        gameOutcome.setGameId(gameId);
        gameOutcome.setGroupId(groupId);
        gameOutcome.setPlayerWinId(playerWinId);

        when(gameOutcomeDao.getGameOutcome(gameOutcomeId)).thenReturn(gameOutcome);

        GetGameOutcomeRequest request = GetGameOutcomeRequest.builder()
                .withGameOutcomeId(gameOutcomeId)
                .build();

        //WHEN
        GetGameOutcomeResult result = getGameOutcomeActivity.handleRequest(request);

        //THEN
        assertEquals(gameOutcome.getGameOutcomeId(), result.getGameOutcomeModel().getGameOutcomeId());
        assertEquals(gameOutcome.getGameId(), result.getGameOutcomeModel().getGameId());
        assertEquals(gameOutcome.getGroupId(), result.getGameOutcomeModel().getGroupId());
        assertEquals(gameOutcome.getPlayerWinId(), result.getGameOutcomeModel().getPlayerWinId());
    }
}
