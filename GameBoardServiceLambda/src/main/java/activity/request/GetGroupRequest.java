package activity.request;

public class GetGroupRequest {
    private final String groupId;

    private GetGroupRequest(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupId() {
        return groupId;
    }

    @Override
    public String toString() {
        return "GetGroupRequest{" +
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

        public GetGroupRequest build() {
            return new GetGroupRequest(groupId);
        }
    }

}
