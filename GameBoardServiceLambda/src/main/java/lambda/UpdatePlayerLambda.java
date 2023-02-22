package lambda;

import activity.request.UpdatePlayerRequest;
import activity.result.UpdatePlayerResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdatePlayerLambda
        extends LambdaActivityRunner<UpdatePlayerRequest, UpdatePlayerResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdatePlayerRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdatePlayerRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdatePlayerRequest unauthenticatedRequest = input.fromBody(UpdatePlayerRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdatePlayerRequest.builder()
                                    .withPlayerId(unauthenticatedRequest.getPlayerId())
                                    .withPlayerName(unauthenticatedRequest.getPlayerName())
                                    .withGroupIds(unauthenticatedRequest.getGroupIds())
                                    .build());
                },
                (request, serviceComponent) -> serviceComponent.provideUpdatePlayerActivity().handleRequest(request)
        );
    }
}
