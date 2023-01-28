package lambda;

import activity.request.GetGameRequest;
import activity.result.GetGameResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetGameLambda
        extends LambdaActivityRunner<GetGameRequest, GetGameResult>
        implements RequestHandler<LambdaRequest<GetGameRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetGameRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetGameRequest.builder()
                            .withGameID(path.get("gameId"))
                            .build()),
            (getGameRequest, serviceComponent) ->
                    serviceComponent.provideGetGameActivity().handleRequest(getGameRequest)
        );
    }
}
