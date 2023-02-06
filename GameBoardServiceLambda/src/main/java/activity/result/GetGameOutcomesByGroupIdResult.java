package activity.result;

import models.GameOutcomeModel;

import java.util.List;

public class GetGameOutcomesByGroupIdResult {
    private final List<GameOutcomeModel> gameOutcomeModelList;

    public GetGameOutcomesByGroupIdResult(List<GameOutcomeModel> gameOutcomeModelList) {
        this.gameOutcomeModelList = gameOutcomeModelList;
    }

    public List<GameOutcomeModel> getGameOutcomeModel() {
        return gameOutcomeModelList;
    }

    @Override
    public String toString() {
        return "GetGameOutcomesByGroupIdResult{" +
                "gameOutcomeModelList=" + gameOutcomeModelList +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private List<GameOutcomeModel> gameOutcomeModelList;

        public Builder withGameOutcomeModelList(List<GameOutcomeModel> gameOutcomeModelList) {
            this.gameOutcomeModelList = gameOutcomeModelList;
            return this;
        }
        public GetGameOutcomesByGroupIdResult build() {
            return new GetGameOutcomesByGroupIdResult(gameOutcomeModelList);
        }
    }
}
