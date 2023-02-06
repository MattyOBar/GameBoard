package activity.request;

public class GetGameOutcomesByGroupIdRequest {
    private final String groupId;

    public GetGameOutcomesByGroupIdRequest(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "GetGameOutcomesByGroupIdRequest{" +
                "groupId='" + groupId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String groupId;

        public Builder withGroupId(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public GetGameOutcomesByGroupIdRequest build() {
            return new GetGameOutcomesByGroupIdRequest(groupId);
        }
    }
}
