package converters;

import dynamodb.models.Game;
import dynamodb.models.Group;
import dynamodb.models.Player;
import models.GameModel;
import models.GroupModel;
import models.PlayerModel;

/**
 * Converts between Data and Coral models.
 */
public class ModelConverter {

    /**
     * Converts a provided {@link Player} into a {@link PlayerModel} representation.
     * @param player the player to convert
     * @return the converted PlayerModel
     */
    public PlayerModel toPlayerModel(Player player) {
        return PlayerModel.builder()
                .withPlayerId(player.getPlayerId())
                .withPlayerName(player.getPlayerName())
                .withGroupIds(player.getGroupIds())
                .build();
    }

    /**
     * Converts a provided {@link Group} into a {@link GroupModel} representation.
     * @param group the group to convert
     * @return the converted GroupModel
     */
    public GroupModel toGroupModel(Group group) {
        return GroupModel.builder()
                .withGroupId(group.getGroupId())
                .withGroupName(group.getGroupName())
                .withGroupFavoriteGame(group.getFavoriteGameId())
                .withGameIds(group.getGameIds())
                .withGameOutcomeIds(group.getGameOutcomeIds())
                .withPlayerIds(group.getPlayerIds())
                .build();


    }
    /**
     * Converts a provided {@link Game} into a {@link GameModel} representation.
     * @param game the game to convert
     * @return the converted GameModel
     */
    public GameModel toGameModel(Game game) {
        return GameModel.builder()
                .withGameId(game.getGameId())
                .withGameName(game.getGameName())
                .withRulesLink(game.getRulesLink())
                .withPurchaseLink(game.getPurchaseLink())
                .build();


    }
}
