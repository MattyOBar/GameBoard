package activity;

import activity.request.GetGameOutcomeRequest;
import activity.result.GetGameOutcomeResult;
import converters.ModelConverter;
import dynamodb.GameOutcomeDao;
import dynamodb.models.GameOutcome;
import exceptions.GameOutcomeInvalidException;
import models.GameOutcomeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

/**
 * This API allows the user to retrieve a specific GameOutcome object.
 */
public class GetGameOutcomeActivity {
    private final Logger log = LogManager.getLogger();
    private final GameOutcomeDao gameOutcomeDao;

    /**
     * Instantiates a GetGameOutcome object.
     * @param gameOutcomeDao GameOutcomeDao to access the GameOutcome table.
     */
    @Inject
    public GetGameOutcomeActivity(GameOutcomeDao gameOutcomeDao) {
        this.gameOutcomeDao = gameOutcomeDao;
    }

    /**
     * This method handles the incoming request by retrieving the GameOutcome from the table.
     * @param getGameOutcomeRequest The request object containing the GameOutcomeId.
     * @return getGameOutcomeResult object containing the API defined GameOutcomeModel.
     */
    public GetGameOutcomeResult handleRequest(final GetGameOutcomeRequest getGameOutcomeRequest) {
        log.info("Received GetGameOutcomeRequest {}", getGameOutcomeRequest);
        String gameOutcomeId = getGameOutcomeRequest.getGameOutcomeId();
        if (gameOutcomeId == null || !gameOutcomeId.startsWith("GO") || gameOutcomeId.length() != 7) {
            throw new GameOutcomeInvalidException("GameOutcomeId: " + gameOutcomeId + " is invalid.");
        }
        GameOutcome gameOutcome = gameOutcomeDao.getGameOutcome(gameOutcomeId);
        GameOutcomeModel gameOutcomeModel = new ModelConverter().toGameOutcomeModel(gameOutcome);
        return GetGameOutcomeResult.builder()
                .withGameOutcomeModel(gameOutcomeModel)
                .build();
    }

}
