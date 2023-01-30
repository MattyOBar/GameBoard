package activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonDeserialize(builder = CreateGameRequest.Builder.class)
public class CreateGameRequest {
    private final String gameId;
    private final String gameName;
    private final String rulesLink;
    private final String purchaseLink;

    private CreateGameRequest(String gameId, String gameName, String rulesLink, String purchaseLink) {
        this.gameId = gameId;
        this.gameName = gameName;
        this.rulesLink = rulesLink;
        this.purchaseLink = purchaseLink;
    }

    public String getGameId() {
        return gameId;
    }

    public String getGameName() {
        return gameName;
    }

    public String getRulesLink() {
        return rulesLink;
    }

    public String getPurchaseLink() {
        return purchaseLink;
    }

    @Override
    public String toString() {
        return "CreateGameRequest{" +
                "gameId='" + gameId + '\'' +
                ", gameName='" + gameName + '\'' +
                ", rulesLink='" + rulesLink + '\'' +
                ", purchaseLink='" + purchaseLink + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String gameId;
        private String gameName;
        private String rulesLink;
        private String purchaseLink;

        public Builder withGameId(String gameId) {
            this.gameId = gameId;
            return this;
        }

        public Builder withGameName(String gameName) {
            this.gameName = gameName;
            return this;
        }

        public Builder withRulesLink(String rulesLink) {
            this.rulesLink = rulesLink;
            return this;
        }

        public Builder withPurchaseLink(String purchaseLink) {
            this.purchaseLink = purchaseLink;
            return this;
        }

        public CreateGameRequest build() {
            return new CreateGameRequest(gameId, gameName, rulesLink, purchaseLink);
        }

    }
}
