package converters;

import dynamodb.models.Group;
import dynamodb.models.Player;
import models.GroupModel;
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

    @Test
    void toGroupModel_convertsGroupToGroupModel_returnsGroupModel() {
        //GIVEN
        String groupId = "testId";
        String groupName = "testName";
        String favoriteGameId = "favoriteGame";
        Set<String> gameIds = Set.of("gameId1", "gameId2");
        Set<String> gameOutcomeIds = Set.of("gameOutcome1", "gameOutcome2");
        Set<String> playerIds = Set.of("player1", "player2");

        Group group = new Group();
        group.setGroupId(groupId);
        group.setGroupName(groupName);
        group.setFavoriteGameId(favoriteGameId);
        group.setGameIds(gameIds);
        group.setGameOutcomeIds(gameOutcomeIds);
        group.setPlayerIds(playerIds);

        //WHEN
        GroupModel groupModel = modelConverter.toGroupModel(group);

        //THEN
        assertEquals(group.getGroupId(), groupModel.getGroupId());
        assertEquals(group.getGroupName(), groupModel.getGroupName());
        assertEquals(group.getFavoriteGameId(), groupModel.getFavoriteGameId());
        assertEquals(group.getGameIds(), groupModel.getGameIds());
        assertEquals(group.getGameOutcomeIds(), groupModel.getGameOutcomeIds());
        assertEquals(group.getPlayerIds(), groupModel.getPlayerIds());
    }
}
