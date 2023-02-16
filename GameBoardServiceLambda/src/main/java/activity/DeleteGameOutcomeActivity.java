package activity;

import activity.request.DeleteGameOutcomeRequest;
import activity.result.DeleteGameOutcomeResult;
import converters.ModelConverter;
import dynamodb.GameOutcomeDao;
import dynamodb.models.GameOutcome;
import exceptions.GameOutcomeNotFoundException;
import models.GameOutcomeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * This API allows the user to delete a specific GameOutcome.
 */

public class DeleteGameOutcomeActivity {

    private final Logger log = LogManager.getLogger();

    private final GameOutcomeDao gameOutcomeDao;

    /**
     * Instantiates a DeleteGameOutcomeActivity object.
     * @param gameOutcomeDao GameOutcomeDao to access the GameOutcomes table.
     */
    @Inject
    public DeleteGameOutcomeActivity(GameOutcomeDao gameOutcomeDao) {
        this.gameOutcomeDao = gameOutcomeDao;
    }

    /**
     * This method handles the incoming request by retrieving the gameOutcomeId from the request
     * and deleting it from the GameOutcome table.
     * @param deleteGameOutcomeRequest the request object containing the GameOutcomeId.
     * @return the deleteGameOutcomeResult containing the API defined GameOutcomeModel that was just deleted.
     */
    public DeleteGameOutcomeResult handleRequest(final DeleteGameOutcomeRequest deleteGameOutcomeRequest) {
        log.info("Received DeleteGameOutcomeRequest {}", deleteGameOutcomeRequest);
        if (deleteGameOutcomeRequest.getGameOutcomeId() == null) {
            throw new GameOutcomeNotFoundException();
        }
        if (gameOutcomeDao.getGameOutcome(deleteGameOutcomeRequest.getGameOutcomeId()) == null) {
            throw new GameOutcomeNotFoundException();
        }

        GameOutcome gameOutcome = gameOutcomeDao.getGameOutcome(deleteGameOutcomeRequest.getGameOutcomeId());

        gameOutcomeDao.deleteGameOutcome(gameOutcome);
        GameOutcomeModel gameOutcomeModel = new ModelConverter().toGameOutcomeModel(gameOutcome);
        return DeleteGameOutcomeResult.builder()
                .withGameOutcomeModel(gameOutcomeModel)
                .build();
    }
}
