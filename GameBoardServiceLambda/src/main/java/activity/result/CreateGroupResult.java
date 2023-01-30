package activity.result;

import models.GroupModel;

public class CreateGroupResult {
    private final GroupModel groupModel;

    private CreateGroupResult(GroupModel groupModel) {
        this.groupModel = groupModel;
    }

    public GroupModel getGroupModel() {
        return groupModel;
    }

    @Override
    public String toString() {
        return "CreateGroupResult{" +
                "groupModel=" + groupModel +
                '}';
    }

    //CHECKSTYLE:OFF:Builder
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        private GroupModel groupModel;

        public Builder withGroupModel(GroupModel groupModel) {
            this.groupModel = groupModel;
            return this;
        }

        public CreateGroupResult build() {
            return new CreateGroupResult(groupModel);
        }
    }
}
