package activity.request;

public class GetGamesByGroupIdRequest {
    private final String groupId;

    public GetGamesByGroupIdRequest(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "GetGamesByGroupIdRequest{" +
                "groupId='" + groupId + '\'' +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private String groupId;

        public Builder withGroupID(String groupId) {
            this.groupId = groupId;
            return this;
        }

        public GetGamesByGroupIdRequest build() {
            return new GetGamesByGroupIdRequest(groupId);
        }
    }
}
