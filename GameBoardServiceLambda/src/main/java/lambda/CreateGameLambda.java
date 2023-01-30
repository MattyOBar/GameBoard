package lambda;

import activity.request.CreateGameRequest;
import activity.result.CreateGameResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateGameLambda
        extends LambdaActivityRunner<CreateGameRequest, CreateGameResult>
        implements RequestHandler<LambdaRequest<CreateGameRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateGameRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromBody(CreateGameRequest.class),
            (request, serviceComponent) ->
                serviceComponent.provideCreateGameActivity().handleRequest(request)
        );
    }
}
