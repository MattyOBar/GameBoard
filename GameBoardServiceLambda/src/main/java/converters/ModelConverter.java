package converters;

import dynamodb.models.Group;
import dynamodb.models.Player;
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
}
