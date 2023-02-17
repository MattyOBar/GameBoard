package lambda;

import activity.request.GetPlayerRequest;
import activity.result.GetPlayerResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetPlayerLambda
        extends LambdaActivityRunner<GetPlayerRequest, GetPlayerResult>
        implements RequestHandler<LambdaRequest<GetPlayerRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetPlayerRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                      GetPlayerRequest.builder()
                            .withPlayerID(path.get("playerId"))
                            .build()),
            (getPlayerRequest, serviceComponent) ->
                    serviceComponent.provideGetPlayerActivity().handleRequest(getPlayerRequest)
        );
    }
}
