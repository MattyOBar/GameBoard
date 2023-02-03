package lambda;

import activity.request.GetGroupsByPlayerRequest;
import activity.result.GetGroupsByPlayerResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetGroupByPlayerLambda
        extends LambdaActivityRunner<GetGroupsByPlayerRequest, GetGroupsByPlayerResult>
        implements RequestHandler<LambdaRequest<GetGroupsByPlayerRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetGroupsByPlayerRequest> input, Context context) {
        return super.runActivity(
                () -> input.fromPath(path ->
                        GetGroupsByPlayerRequest.builder()
                                .withPlayerID(path.get("playerId"))
                                .build()),
                (request, serviceComponent) ->
                        serviceComponent.provideGetGroupsByPlayerActivity().handleRequest(request)
        );

    }
}
