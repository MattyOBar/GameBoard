package lambda;

import activity.request.GetGameOutcomesByGroupIdRequest;
import activity.result.GetGameOutcomesByGroupIdResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class GetGameOutcomesByGroupIdLambda
        extends LambdaActivityRunner<GetGameOutcomesByGroupIdRequest, GetGameOutcomesByGroupIdResult>
        implements RequestHandler<LambdaRequest<GetGameOutcomesByGroupIdRequest>, LambdaResponse> {

    protected final Logger log = LogManager.getLogger();

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetGameOutcomesByGroupIdRequest> input, Context context) {
        return super.runActivity(
            () -> {
                GetGameOutcomesByGroupIdRequest request = input.fromBody(GetGameOutcomesByGroupIdRequest.class);
                log.info(request);
                return input.fromPath(path ->
                        GetGameOutcomesByGroupIdRequest.builder()
                                .withGroupId(path.get("groupId"))
                                .withGameId(request.getGameId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideGetGameOutcomesByGroupIdActivity().handleRequest(request)
        );
    }
}
