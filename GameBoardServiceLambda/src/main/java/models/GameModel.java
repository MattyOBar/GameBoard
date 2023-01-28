package models;

import java.util.Objects;

public class GameModel {
    private final String gameId;
    private final String gameName;
    private final String rulesLink;
    private final String purchaseLink;

    private GameModel(String gameId, String gameName, String rulesLink, String purchaseLink) {
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
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        GameModel gameModel = (GameModel) o;
        return Objects.equals(getGameId(), gameModel.getGameId()) &&
                Objects.equals(getGameName(), gameModel.getGameName()) &&
                Objects.equals(getRulesLink(), gameModel.getRulesLink()) &&
                Objects.equals(getPurchaseLink(), gameModel.getPurchaseLink());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getGameId(), getGameName(),
                getRulesLink(), getPurchaseLink());
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

        public GameModel build() {
            return new GameModel(gameId, gameName, rulesLink, purchaseLink);
        }
    }
}
