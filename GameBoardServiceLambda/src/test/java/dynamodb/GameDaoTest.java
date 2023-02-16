package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Game;
import exceptions.GameNotFoundException;
import metrics.MetricsConstants;
import metrics.MetricsPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.eq;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.openMocks;

public class GameDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private MetricsPublisher metricsPublisher;

    private GameDao gameDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        gameDao = new GameDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getGame_withGameId_callsMapperWithPartitionKey() {
        // GIVEN
        String gameId = "GME00001";
        when(dynamoDBMapper.load(Game.class, gameId)).thenReturn(new Game());

        // WHEN
        Game game = gameDao.getGame(gameId);

        // THEN
        assertNotNull(game);
        verify(dynamoDBMapper).load(Game.class, gameId);
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETGAME_GAMENOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void getGame_gameIdNotFound_throwsGameNotFoundException() {
        // GIVEN
        String nonExistentGameId = "NotReal";
        when(dynamoDBMapper.load(Game.class, nonExistentGameId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(GameNotFoundException.class, () -> gameDao.getGame(nonExistentGameId));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETGAME_GAMENOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void saveGame_CallsMapperWithGame() {
        // GIVEN
        Game game = new Game();

        // WHEN
        Game result = gameDao.saveGame(game);

        // THEN
        verify(dynamoDBMapper).save(game);
        assertEquals(game, result);
    }
}
