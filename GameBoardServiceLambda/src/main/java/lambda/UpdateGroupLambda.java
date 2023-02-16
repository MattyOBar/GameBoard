package lambda;

import activity.request.UpdateGroupRequest;
import activity.result.UpdateGroupResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class UpdateGroupLambda
        extends LambdaActivityRunner<UpdateGroupRequest, UpdateGroupResult>
        implements RequestHandler<LambdaRequest<UpdateGroupRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<UpdateGroupRequest> input, Context context) {
        return super.runActivity(
            () -> {
                UpdateGroupRequest request = input.fromBody(UpdateGroupRequest.class);
                return input.fromPath(path ->
                    UpdateGroupRequest.builder()
                        .withGroupId(path.get("groupId"))
                        .withGroupName(request.getGroupName())
                        .withFavoriteGameId(request.getFavoriteGameId())
                        .withGameIds(request.getGameIds())
                        .withPlayerIds(request.getPlayerIds())
                        .withGameOutcomeIds(request.getGameOutcomeIds())
                        .build());
            },
            (request, serviceComponent) -> serviceComponent.provideUpdateGroupAcitivty().handleRequest(request)
        );
    }
}
