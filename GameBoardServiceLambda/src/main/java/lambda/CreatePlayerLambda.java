package lambda;

import activity.request.CreatePlayerRequest;
import activity.result.CreatePlayerResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreatePlayerLambda
        extends LambdaActivityRunner<CreatePlayerRequest, CreatePlayerResult>
        implements RequestHandler<LambdaRequest<CreatePlayerRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreatePlayerRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromBody(CreatePlayerRequest.class),
                (request, serviceComponent) ->
                        serviceComponent.provideCreatePlayerActivity().handleRequest(request)
        );
    }
}
