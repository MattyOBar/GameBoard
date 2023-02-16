package activity;

import activity.request.DeleteGameOutcomeRequest;
import activity.result.DeleteGameOutcomeResult;
import dynamodb.GameOutcomeDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class DeleteGameOutcomeActivity {

    private final Logger log = LogManager.getLogger();

    private final GameOutcomeDao gameOutcomeDao;

    @Inject
    public DeleteGameOutcomeActivity(GameOutcomeDao gameOutcomeDao) {
        this.gameOutcomeDao = gameOutcomeDao;
    }

    public DeleteGameOutcomeResult handleRequest(final DeleteGameOutcomeRequest deleteGameOutcomeRequest) {
        log.info("Received DeleteGameOutcomeRequest {}", deleteGameOutcomeRequest);

    }
}
