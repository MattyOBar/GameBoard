package lambda;

import activity.request.UpdateGroupRequest;
import activity.result.UpdateGroupResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateGroupLambda
        extends LambdaActivityRunner<UpdateGroupRequest, UpdateGroupResult>
        implements RequestHandler<AuthenticatedLambdaRequest<UpdateGroupRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<UpdateGroupRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdateGroupRequest unauthenticatedRequest = input.fromBody(UpdateGroupRequest.class);
                return input.fromUserClaims(claims ->
                    UpdateGroupRequest.builder()
                        .withGroupId(unauthenticatedRequest.getGroupId())
                        .withGroupName(unauthenticatedRequest.getGroupName())
                        .withFavoriteGameId(unauthenticatedRequest.getFavoriteGameId())
                        .withGameIds(unauthenticatedRequest.getGameIds())
                        .withPlayerIds(unauthenticatedRequest.getPlayerIds())
                        .withGameOutcomeIds(unauthenticatedRequest.getGameOutcomeIds())
                        .build());
            },
            (request, serviceComponent) -> serviceComponent.provideUpdateGroupActivity().handleRequest(request)
        );
    }
}
