package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.GameOutcome;
import exceptions.GameOutcomeNotFoundException;
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

public class GameOutcomeDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private MetricsPublisher metricsPublisher;

    private GameOutcomeDao gameOutcomeDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        gameOutcomeDao = new GameOutcomeDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getGameOutcome_withPlayerId_callsMapperWithPartitionKey() {
        //GIVEN
        String gameOutcomeId = "GO00001";
        when(dynamoDBMapper.load(GameOutcome.class, gameOutcomeId)).thenReturn(new GameOutcome());

        //WHEN
        GameOutcome gameOutcome = gameOutcomeDao.getGameOutcome(gameOutcomeId);

        //THEN
        assertNotNull(gameOutcome);
        verify(dynamoDBMapper).load(GameOutcome.class, gameOutcomeId);
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETGAMEOUTCOME_GAMEOUTCOMENOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void getGameOutcome_gameOutcomeIdNotFound_throwsGameOutcomeNotFoundExecption() {
        // GIVEN
        String nonExistantGameOutcomeId = "NotReal";
        when(dynamoDBMapper.load(GameOutcome.class, nonExistantGameOutcomeId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(GameOutcomeNotFoundException.class, () -> gameOutcomeDao.getGameOutcome(nonExistantGameOutcomeId));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETGAMEOUTCOME_GAMEOUTCOMENOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void saveGameOutcome_CallsMapperWithGameOutcome() {
        // GIVEN
        GameOutcome gameOutcome = new GameOutcome();

        // WHEN
        GameOutcome result = gameOutcomeDao.saveGameOutcome(gameOutcome);

        // THEN
        verify(dynamoDBMapper).save(gameOutcome);
        assertEquals(gameOutcome, result);
    }
}
