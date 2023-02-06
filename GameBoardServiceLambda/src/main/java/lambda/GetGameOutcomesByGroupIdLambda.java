package lambda;

import activity.request.GetGameOutcomesByGroupIdRequest;
import activity.result.GetGameOutcomesByGroupIdResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetGameOutcomesByGroupIdLambda
        extends LambdaActivityRunner<GetGameOutcomesByGroupIdRequest, GetGameOutcomesByGroupIdResult>
        implements RequestHandler<LambdaRequest<GetGameOutcomesByGroupIdRequest>, LambdaResponse> {
    @Override
    public LambdaResponse handleRequest(LambdaRequest<GetGameOutcomesByGroupIdRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromPath(path ->
                    GetGameOutcomesByGroupIdRequest.builder()
                            .withGroupId(path.get("groupId"))
                            .build()),
    (request, serviceComponent) ->
            serviceComponent.provideGetGameOutcomesByGroupIdActivity().handleRequest(request)
            );
    }
}
