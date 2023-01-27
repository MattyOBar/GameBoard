package activity.request;

import java.util.Set;

public class CreatePlayerRequest {
    private final String playerId;
    private final String playerName;
    private final Set<String> groupIds;

    private CreatePlayerRequest(String playerId, String playerName, Set<String> groupIds) {
        this.playerId = playerId;
        this.playerName = playerName;
        this.groupIds = groupIds;
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Set<String> getGroupIds() {
        return groupIds;
    }

    @Override
    public String toString() {
        return "CreatePlayerRequest{" +
                "playerId='" + playerId + '\'' +
                ", playerName='" + playerName + '\'' +
                ", groupIds=" + groupIds +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String playerId;
        private String playerName;
        private Set<String> groupIds;
    }
}
