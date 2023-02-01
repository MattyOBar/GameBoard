package activity.result;

import models.GroupModel;

public class GetGroupResult {

    private final GroupModel groupModel;

    private GetGroupResult(GroupModel groupModel) {
        this.groupModel = groupModel;
    }

    public GroupModel getGroupModel() {
        return groupModel;
    }

    @Override
    public String toString() {
        return "GetGroupResult{" +
                "groupModel=" + groupModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GroupModel groupModel;

        public Builder withGroup(GroupModel groupModel) {
            this.groupModel = groupModel;
            return this;
        }

        public GetGroupResult build() {
            return new GetGroupResult(groupModel);
        }
    }
}
