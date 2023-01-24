package lambda;

import activity.request.GetGroupRequest;
import activity.result.GetGroupResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetGroupLambda
        extends LambdaActivityRunner<GetGroupRequest, GetGroupResult>
        implements RequestHandler<LambdaRequest<GetGroupRequest>, LambdaResponse> {


    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetGroupRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetGroupRequest.builder()
                            .withGroupID(path.get("groupId"))
                            .build()),
            (getGroupRequest, serviceComponent) ->
                    serviceComponent.provideGetGroupActivity().handleRequest(getGroupRequest)
        );
    }
}
