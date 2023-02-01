package lambda;

import activity.request.GetGameOutcomeRequest;
import activity.result.GetGameOutcomeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetGameOutcomeLambda
        extends LambdaActivityRunner<GetGameOutcomeRequest, GetGameOutcomeResult>
        implements RequestHandler<LambdaRequest<GetGameOutcomeRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetGameOutcomeRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetGameOutcomeRequest.builder()
                            .withGameOutcomeId(path.get("gameOutcomeId"))
                            .build()),
            (getGameOutcomeRequest, serviceComponent) ->
                    serviceComponent.provideGetGameOutcomeActivity().handleRequest(getGameOutcomeRequest)
        );
    }
}
