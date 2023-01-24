package converters;

import dynamodb.models.Player;
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
}
