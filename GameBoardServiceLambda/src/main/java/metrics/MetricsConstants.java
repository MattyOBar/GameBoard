package metrics;

public class MetricsConstants {

    public static final String SERVICE = "Service";
    public static final String SERVICE_NAME = "GameBoard";
    public static final String NAMESPACE_NAME = "GameBoard";

    public static final String GETPLAYER_PLAYERNOTFOUND_COUNT =
            "GetPlayer.PlayerNotFoundException.Count";

    public static final String GETGROUP_GROUPNOTFOUND_COUNT =
            "GetGroup.GroupNotFoundException.Count";

    public static final String GETGAME_GAMENOTFOUND_COUNT =
            "GetGame.GameNotFoundException.Count";
    public static final String GETGAMEOUTCOME_GAMEOUTCOMENOTFOUND_COUNT =
            "GetGameOutcome.GameOutcomeNotFoundException.Count";
}
