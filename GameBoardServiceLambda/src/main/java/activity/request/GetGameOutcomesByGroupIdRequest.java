package activity.request;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;

@JsonDeserialize(builder = GetGameOutcomesByGroupIdRequest.Builder.class)
public class GetGameOutcomesByGroupIdRequest {
    private final String groupId;
    private final String gameId;

    /**
     * Request object for getting gameOutcomes by groupId.
     * @param groupId the groupId for the specific group that GameOutcomes should be returned for.
     */
    public GetGameOutcomesByGroupIdRequest(String groupId, String gameId) {
        this.groupId = groupId;
        this.gameId = gameId;
    }

    public String getGroupId() {
        return groupId;
    }

    public String getGameId() {
        return gameId;
    }

    @Override
    public String toString() {
        return "GetGameOutcomesByGroupIdRequest{" +
                "groupId='" + groupId + '\'' +
                ", gameId='" + gameId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    @JsonPOJOBuilder
    public static class Builder {
        private String groupId;

        private String gameId;

        public Builder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public Builder withGameId(String gameId) {
            this.gameId = gameId;
            return this;
        }

        public GetGameOutcomesByGroupIdRequest build() {
            return new GetGameOutcomesByGroupIdRequest(groupId, gameId);
        }
    }
}
