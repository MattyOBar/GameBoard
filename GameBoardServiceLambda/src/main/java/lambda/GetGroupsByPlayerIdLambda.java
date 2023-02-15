package lambda;

import activity.request.GetGroupsByPlayerIdRequest;
import activity.result.GetGroupsByPlayerIdResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetGroupsByPlayerIdLambda
        extends LambdaActivityRunner<GetGroupsByPlayerIdRequest, GetGroupsByPlayerIdResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetGroupsByPlayerIdRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetGroupsByPlayerIdRequest> input, Context context) {
        return super.runActivity(() -> input.fromUserClaims(claims ->
                        GetGroupsByPlayerIdRequest.builder()
                                .withPlayerID(claims.get("email"))
                                .build()),
            (request, serviceComponent) ->
                    serviceComponent.provideGetGroupsByPlayerIdActivity().handleRequest(request)
        );
    }
}
