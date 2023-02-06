package activity;

import activity.request.GetGameRequest;
import activity.result.GetGameResult;
import converters.ModelConverter;
import dynamodb.GameDao;
import dynamodb.models.Game;
import exceptions.GameInvalidException;
import models.GameModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * This API allows the user to retrieve a specific Game object.
 */
public class GetGameActivity {

    private final Logger log = LogManager.getLogger();
    private final GameDao gameDao;

    /**
     * Instaniates a GetGameActivity object.
     * @param gameDao GameDao to access the Games table.
     */
    @Inject
    public GetGameActivity(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    /**
     * This method handles the incoming request by retrieving the game from the database.
     * @param getGameRequest the request object containing the gameId.
     * @return getGameResult object containing the API defined GameModel.
     */
    public GetGameResult handleRequest(final GetGameRequest getGameRequest) {
        log.info("Received GetGameRequest {}", getGameRequest);
        String gameId = getGameRequest.getGameId();
        if (gameId == null || !gameId.startsWith("GM") || gameId.length() != 7) {
            throw new GameInvalidException("GameId: " + gameId + " is invalid.");
        }
        Game game = gameDao.getGame(gameId);
        GameModel gameModel = new ModelConverter().toGameModel(game);
        return GetGameResult.builder()
                .withGameModel(gameModel)
                .build();
    }
}
