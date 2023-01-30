package lambda;

import activity.request.CreateGroupRequest;
import activity.result.CreateGroupResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class CreateGroupLambda
        extends LambdaActivityRunner<CreateGroupRequest, CreateGroupResult>
        implements RequestHandler<LambdaRequest<CreateGroupRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<CreateGroupRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromBody(CreateGroupRequest.class),
            (request, serviceComponent) ->
                    serviceComponent.provideCreateGroupActivity().handleRequest(request)
        );
    }
}
