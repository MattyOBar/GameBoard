package lambda;

import activity.request.CreatePlayerRequest;
import activity.result.CreatePlayerResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreatePlayerLambda
        extends LambdaActivityRunner<CreatePlayerRequest, CreatePlayerResult>
        implements RequestHandler<AuthenticatedLambdaRequest<CreatePlayerRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<CreatePlayerRequest> input, Context context) {
        return super.runActivity(
            () -> {
                CreatePlayerRequest unauthenticatedRequest =  input.fromBody(CreatePlayerRequest.class);
                return input.fromUserClaims(claims ->
                        CreatePlayerRequest.builder()
                                .withPlayerId(claims.get("email"))
                                .withPlayerName(claims.get("name"))
                                .withGroupIds(unauthenticatedRequest.getGroupIds())
                                .build());
            },
            (request, serviceComponent) ->
                    serviceComponent.provideCreatePlayerActivity().handleRequest(request)
        );
    }
}
