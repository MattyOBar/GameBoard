package lambda;

import activity.request.CreateGameOutcomeRequest;
import activity.result.CreateGameOutcomeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateGameOutcomeLambda
        extends LambdaActivityRunner<CreateGameOutcomeRequest, CreateGameOutcomeResult>
        implements RequestHandler<LambdaRequest<CreateGameOutcomeRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateGameOutcomeRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromBody(CreateGameOutcomeRequest.class),
            (request, serviceComponent) ->
                    serviceComponent.provideCreateGameOutcomeActivity().handleRequest(request)
        );
    }
}
