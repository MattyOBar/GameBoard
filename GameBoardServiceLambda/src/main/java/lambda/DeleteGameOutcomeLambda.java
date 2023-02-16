package lambda;

import activity.request.DeleteGameOutcomeRequest;
import activity.result.DeleteGameOutcomeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class DeleteGameOutcomeLambda
        extends LambdaActivityRunner<DeleteGameOutcomeRequest, DeleteGameOutcomeResult>
        implements RequestHandler<LambdaRequest<DeleteGameOutcomeRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<DeleteGameOutcomeRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path -> DeleteGameOutcomeRequest.builder()
                    .withGameOutcomeId(path.get("gameOutcomeId"))
                    .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideDeleteGameOutcomeActivity().handleRequest(request)
        );
    }
}
