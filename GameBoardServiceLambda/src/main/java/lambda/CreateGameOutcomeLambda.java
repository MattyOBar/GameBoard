package lambda;

import activity.request.CreateGameOutcomeRequest;
import activity.result.CreateGameOutcomeResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateGameOutcomeLambda
        extends LambdaActivityRunner<CreateGameOutcomeRequest, CreateGameOutcomeResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreateGameOutcomeRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreateGameOutcomeRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreateGameOutcomeRequest unauthenticatedRequest = input.fromBody(CreateGameOutcomeRequest.class);
                return input.fromUserClaims(claims ->
                        CreateGameOutcomeRequest.builder()
                                .withGroupId(unauthenticatedRequest.getGroupId())
                                .withGameId(unauthenticatedRequest.getGameId())
                                .withPlayerWinId(unauthenticatedRequest.getPlayerWinId())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreateGameOutcomeActivity().handleRequest(request)
        );
    }
}
