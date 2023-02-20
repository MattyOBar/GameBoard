package activity;

import activity.request.CreateGameOutcomeRequest;
import activity.result.CreateGameOutcomeResult;
import converters.ModelConverter;
import dynamodb.GameOutcomeDao;
import dynamodb.models.GameOutcome;
import models.GameOutcomeModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Set;
import java.util.UUID;
import javax.inject.Inject;


/**
 * This API allows the user to create a new GameOutcome object.
 */
public class CreateGameOutcomeActivity {
    private final Logger log = LogManager.getLogger();
    private final GameOutcomeDao gameOutcomeDao;

    /**
     * Instantiates a CreateGameOutcomeActivity object.
     * @param gameOutcomeDao GameOutcomeDao to access the GameOutcomes table.
     */
    @Inject
    public CreateGameOutcomeActivity(GameOutcomeDao gameOutcomeDao) {
        this.gameOutcomeDao = gameOutcomeDao;
    }

    /**
     * This method handles the incoming request by creating a new CreateGameOutcome object
     * and saving it to GameOutcomes table.
     * @param createGameOutcomeRequest the request object containing data the build a CreateGameOutcome object.
     * @return createGameOutcomeResult containing the newly created GameOutcomeModel.
     */
    public CreateGameOutcomeResult handleRequest(final CreateGameOutcomeRequest createGameOutcomeRequest) {
        log.info("Received CreateGameOutcomeRequest {}", createGameOutcomeRequest);

        String gameOutcomeId = "GO".concat(UUID.randomUUID().toString().substring(0, 5));
        String groupId = createGameOutcomeRequest.getGroupId();
        String gameId = createGameOutcomeRequest.getGameId();
        String playerWinId = createGameOutcomeRequest.getPlayerWinId();

        GameOutcome gameOutcome = new GameOutcome();
        gameOutcome.setGameOutcomeId(gameOutcomeId);
        gameOutcome.setGroupId(groupId);
        gameOutcome.setGameId(gameId);
        gameOutcome.setPlayerWinId(playerWinId);

        gameOutcomeDao.saveGameOutcome(gameOutcome);
        GameOutcomeModel gameOutcomeModel = new ModelConverter().toGameOutcomeModel(gameOutcome);

        return CreateGameOutcomeResult.builder()
                .withGameOutcomeModel(gameOutcomeModel)
                .build();
    }
}
