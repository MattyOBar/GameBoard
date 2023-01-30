package activity;

import activity.request.CreateGameRequest;
import activity.request.CreatePlayerRequest;
import activity.result.CreateGameResult;
import activity.result.CreatePlayerResult;
import dynamodb.GameDao;
import dynamodb.models.Game;
import dynamodb.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.MockitoAnnotations.openMocks;

public class CreateGameActivityTest {

    @Mock
    private GameDao gameDao;

    private CreateGameActivity createGameActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        createGameActivity = new CreateGameActivity(gameDao);
    }

    @Test
    public void handleRequest_CreatesAndSavesNewGame_ReturnCreatedGame() {
        //GIVEN
        String expectedGameName = "TestName";
        String expectedRulesLink = "www.gamerules.com";
        String expectedPurchaseLink = "www.amazon.com";

        CreateGameRequest request = CreateGameRequest.builder()
                .withGameName(expectedGameName)
                .withRulesLink(expectedRulesLink)
                .withPurchaseLink(expectedPurchaseLink)
                .build();

        //WHEN
        CreateGameResult result = createGameActivity.handleRequest(request);

        //THEN
        verify(gameDao).saveGame(any(Game.class));

        assertNotNull(result.getGameModel().getGameId());
        assertEquals(expectedGameName, result.getGameModel().getGameName());
        assertEquals(expectedRulesLink, result.getGameModel().getRulesLink());
        assertEquals(expectedPurchaseLink, result.getGameModel().getPurchaseLink());
    }
}
