package activity;

import activity.request.GetGameRequest;
import activity.result.GetGameResult;
import dynamodb.GameDao;
import dynamodb.models.Game;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GetGameActivityTest {
    @Mock
    private GameDao gameDao;

    private GetGameActivity getGameActivity;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        getGameActivity = new GetGameActivity(gameDao);
    }

    @Test
    public void handleRequest_savedGameFound_ReturnsGameModelInResult() {
        //GIVEN
        String gameId = "GM00001";
        String gameName = "Monopoly";
        String rulesLink = "www.monopoly.com";
        String purchaseLink = "www.amazon.com";

        Game game = new Game();
        game.setGameId(gameId);
        game.setGameName(gameName);
        game.setRulesLink(rulesLink);
        game.setPurchaseLink(purchaseLink);

        when(gameDao.getGame(gameId)).thenReturn(game);
        GetGameRequest request = GetGameRequest.builder()
                .withGameID(gameId)
                .build();

        //WHEN
        GetGameResult result = getGameActivity.handleRequest(request);

        //THEN
        assertNotNull(result);
        assertEquals(game.getGameId(), result.getGameModel().getGameId());
        assertEquals(game.getGameName(), result.getGameModel().getGameName());
        assertEquals(game.getRulesLink(), result.getGameModel().getRulesLink());
        assertEquals(game.getPurchaseLink(), result.getGameModel().getPurchaseLink());

    }
}
