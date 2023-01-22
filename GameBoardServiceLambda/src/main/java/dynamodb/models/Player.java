package dynamodb.models;

import java.util.Set;

public class Player {

    private String playerId;
    private String playerName;
    private Set<String> groups;

    public String getPlayerId() {
        return playerId;
    }

    public String getPlayerName() {
        return playerName;
    }

    public Set<String> getGroups() {
        return groups;
    }
    
    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public void setGroups(Set<String> groups) {
        this.groups = groups;
    }
}
