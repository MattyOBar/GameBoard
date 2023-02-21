package activity;

import activity.request.CreateGameOutcomeRequest;
import activity.result.CreateGameOutcomeResult;
import dynamodb.GameOutcomeDao;
import dynamodb.models.GameOutcome;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateGameOutcomeActivityTest {
    @Mock
    private GameOutcomeDao gameOutcomeDao;

    private CreateGameOutcomeActivity createGameOutcomeActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        createGameOutcomeActivity = new CreateGameOutcomeActivity(gameOutcomeDao);
    }

    @Test
    public void handleRequest_CreatesAndSavesNewGameOutcome_returnsCreatedGameOutcome() {
        //GIVEN
        String gameId = "GME001";
        String groupId = "GR001";
        String playerWinId = "P0001";

        CreateGameOutcomeRequest request = CreateGameOutcomeRequest.builder()
                .withGameId(gameId)
                .withGroupId(groupId)
                .withPlayerWinId(playerWinId)
                .build();

        //WHEN
        CreateGameOutcomeResult result = createGameOutcomeActivity.handleRequest(request);

        //THEN
        verify(gameOutcomeDao).saveGameOutcome(any(GameOutcome.class));
        assertNotNull(result.getGameOutcomeModel().getGameOutcomeId());
        assertEquals(gameId, result.getGameOutcomeModel().getGameId());
        assertEquals(groupId, result.getGameOutcomeModel().getGroupId());
        assertEquals(playerWinId, result.getGameOutcomeModel().getPlayerWinId());
    }
}
