package activity;

import activity.result.GetGamesByGroupIdResult;
import dynamodb.GroupDao;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.inject.Inject;

public class GetGamesByGroupIdActivity {
    private final Logger log = LogManager.getLogger();
    private final GroupDao groupDao;

    @Inject
    public GetGamesByGroupIdActivity(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    public GetGamesByGroupIdActivity handleRequest(final GetGamesByGroupIdResult getGamesByGroupIdResult) {
        log.info("Received GetGamesByGroupIdRequest {}", getGamesByGroupIdResult);
    }
}
