//package activity;
//
//import activity.request.GetGamesByGroupIdRequest;
//import activity.result.GetGamesByGroupIdResult;
//import dynamodb.GameDao;
//import dynamodb.GroupDao;
//import dynamodb.models.Game;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mock;
//
//import static org.mockito.Mockito.when;
//import static org.mockito.MockitoAnnotations.openMocks;
//
//public class GetGamesByGroupIdActivityTest {
//    @Mock
//    private GameDao gameDao;
//
//    @Mock
//    private GroupDao groupDao;
//
//    private GetGamesByGroupIdActivity getGamesByGroupIdActivity;
//
//    @BeforeEach
//    public void setUp() {
//        openMocks(this);
//        getGamesByGroupIdActivity = new GetGamesByGroupIdActivity(groupDao, gameDao);
//    }
//
//    @Test
//    public void handleRequest_gamesPlayedByGroupByGroupId_returnsGameModelSet() {
//        //GIVEN
//        String groupId = "GR00001";
//
//
//
//        GetGamesByGroupIdRequest request =  GetGamesByGroupIdRequest.builder()
//                .withGroupID(groupId)
//                .build();
//
//        //WHEN
//        GetGamesByGroupIdResult result = getGamesByGroupIdActivity.handleRequest(request);
//
//        //THEN
//
//    }
//}
