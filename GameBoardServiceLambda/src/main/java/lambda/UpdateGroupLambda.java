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
        return null;
    }
}
