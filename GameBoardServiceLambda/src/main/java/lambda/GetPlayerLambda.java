package lambda;

import activity.request.GetPlayerRequest;
import activity.result.GetPlayerResult;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class GetPlayerLambda
        extends LambdaActivityRunner<GetPlayerRequest, GetPlayerResult>
        implements RequestHandler<AuthenticatedLambdaRequest<GetPlayerRequest>, LambdaResponse> {

    @Override
    public LambdaResponse handleRequest(AuthenticatedLambdaRequest<GetPlayerRequest> input, Context context) {
        return super.runActivity(
            () -> input.fromUserClaims(claims ->
                    GetPlayerRequest.builder()
                            .withPlayerID(claims.get("email"))
                            .build()),
            (getPlayerRequest, serviceComponent) ->
                    serviceComponent.provideGetPlayerActivity().handleRequest(getPlayerRequest)
        );
    }
}
