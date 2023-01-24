package converters;

import dynamodb.models.Player;
import models.PlayerModel;
import org.junit.jupiter.api.Test;

import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;

public class ModelConverterTest {
    private ModelConverter modelConverter = new ModelConverter();

    @Test
    void toPlayerModel_convertsPlayerToPlayerModel_returnsPlayerModel() {
        //GIVEN
        String playerId = "testId";
        String playerName = "testName";
        Set<String> groupIds = Set.of("groupId1", "groupId2");

        Player player = new Player();
        player.setPlayerId(playerId);
        player.setPlayerName(playerName);
        player.setGroups(groupIds);

        //WHEN
        PlayerModel playerModel = modelConverter.toPlayerModel(player);

        //THEN
        assertEquals(player.getPlayerId(), playerModel.getPlayerId());
        assertEquals(player.getPlayerName(), playerModel.getPlayerName());
        assertEquals(player.getGroupIds(), playerModel.getGroupIds());
    }
}
