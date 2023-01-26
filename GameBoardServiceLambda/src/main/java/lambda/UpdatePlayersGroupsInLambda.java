package lambda;

import activity.request.UpdatePlayersGroupsInActivity;
import activity.request.UpdatePlayersGroupsInRequest;
import activity.result.UpdatePlayersGroupsInResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdatePlayersGroupsInLambda
        extends LambdaActivityRunner<UpdatePlayersGroupsInRequest, UpdatePlayersGroupsInResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdatePlayersGroupsInRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdatePlayersGroupsInRequest> input, Context context) {
        return super.runActivity(
                () -> {
                    UpdatePlayersGroupsInRequest unauthenticatedRequest = input.fromBody(UpdatePlayersGroupsInRequest.class);
                    return input.fromUserClaims(claims ->
                            UpdatePlayersGroupsInRequest.builder()
                                    .withPlayerId(unauthenticatedRequest.getPlayerId())
                                    .withPlayerName(unauthenticatedRequest.getPlayerName())
                                    .withGroupIds(unauthenticatedRequest.getGroupIds())
                                    .build());
                },
                (request, serviceComponent) ->
                        serviceComponent.provideUpdatePlayersGroupsInActivity().handleRequest(request)
        );
    }
}
