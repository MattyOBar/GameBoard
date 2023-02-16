package activity.result;

import models.GroupModel;

public class UpdateGroupResult {
    private final GroupModel groupModel;

    /**
     * The result object for the UpdateGroupActivity.
     * @param groupModel the updated groupModel.
     */
    public UpdateGroupResult(GroupModel groupModel) {
        this.groupModel = groupModel;
    }

    public GroupModel getGroupModel() {
        return groupModel;
    }

    @Override
    public String toString() {
        return "UpdateGroupResult{" +
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

        public UpdateGroupResult build() {
            return new UpdateGroupResult(groupModel);
        }
    }
}
