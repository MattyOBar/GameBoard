package activity;

import activity.request.CreateGameRequest;
import activity.result.CreateGameResult;
import converters.ModelConverter;
import dynamodb.GameDao;
import dynamodb.models.Game;
import models.GameModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;
import javax.inject.Inject;

/**
 * This API allows the user to create a new Game object.
 */
public class CreateGameActivity {

    private final Logger log = LogManager.getLogger();

    private final GameDao gameDao;

    /**
     * Instantiates a CreateGameActivity object.
     * @param gameDao GameDao to access the Games table.
     */
    @Inject
    public CreateGameActivity(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    /**
     * This method handles the incoming request by saving a new Game object to the DAO.
     * @param createGameRequest the request object containing the Game data.
     * @return CreateGameResult object containing the API defined GameModel.
     */

    public CreateGameResult handleRequest(final CreateGameRequest createGameRequest) {
        log.info("Received CreateGameRequest {}", createGameRequest);
        String gameId = UUID.randomUUID().toString().substring(0, 5);
        String gameName = createGameRequest.getGameName();
        String rulesLink = createGameRequest.getRulesLink();
        String purchaseLink = createGameRequest.getPurchaseLink();

        Game game = new Game();
        game.setGameId(gameId);
        game.setGameName(gameName);
        game.setRulesLink(rulesLink);
        game.setPurchaseLink(purchaseLink);

        gameDao.saveGame(game);
        GameModel gameModel = new ModelConverter().toGameModel(game);

        return CreateGameResult.builder()
                .withGameModel(gameModel)
                .build();
    }
}
