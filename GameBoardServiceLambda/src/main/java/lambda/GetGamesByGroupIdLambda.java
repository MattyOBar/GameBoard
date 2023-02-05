package lambda;

import activity.request.GetGamesByGroupIdRequest;
import activity.result.GetGamesByGroupIdResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetGamesByGroupIdLambda
        extends LambdaActivityRunner<GetGamesByGroupIdRequest, GetGamesByGroupIdResult>
        implements RequestHandler<LambdaRequest<GetGamesByGroupIdRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetGamesByGroupIdRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetGamesByGroupIdRequest.builder()
                            .withGroupID(path.get("groupId"))
                            .build()),
            (getGamesByGroupIdRequest, serviceComponent) ->
                    serviceComponent.provideGetGamesByGroupIdActivity().handleRequest(getGamesByGroupIdRequest)
        );
    }
}
