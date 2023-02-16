package dynamodb;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import dynamodb.models.Player;
import exceptions.PlayerNotFoundException;
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

public class PlayerDaoTest {
    @Mock
    private DynamoDBMapper dynamoDBMapper;

    @Mock
    private MetricsPublisher metricsPublisher;

    private PlayerDao playerDao;

    @BeforeEach
    public void setUp() {
        openMocks(this);
        playerDao = new PlayerDao(dynamoDBMapper, metricsPublisher);
    }

    @Test
    public void getPlayer_withPlayerId_callsMapperWithPartitionKey() {
        // GIVEN
        String playerId = "playerId";
        when(dynamoDBMapper.load(Player.class, playerId)).thenReturn(new Player());

        // WHEN
        Player player = playerDao.getPlayer(playerId);

        // THEN
        assertNotNull(player);
        verify(dynamoDBMapper).load(Player.class, playerId);
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETPLAYER_PLAYERNOTFOUND_COUNT), anyDouble());
    }

    @Test
    public void getPlayer_playerIdNotFound_throwsPlayerNotFoundException() {
        // GIVEN
        String nonexistentPlayerId = "NotReal";
        when(dynamoDBMapper.load(Player.class, nonexistentPlayerId)).thenReturn(null);

        // WHEN + THEN
        assertThrows(PlayerNotFoundException.class, () -> playerDao.getPlayer(nonexistentPlayerId));
        verify(metricsPublisher).addCount(eq(MetricsConstants.GETPLAYER_PLAYERNOTFOUND_COUNT), anyDouble());
    }
    @Test
    public void savePlayer_CallsMapperWithPlayer() {
        // GIVEN
        Player player = new Player();

        //WHEN
        Player result = playerDao.savePlayer(player);

        // THEN
        verify(dynamoDBMapper).save(player);
        assertEquals(player, result);
    }
}
