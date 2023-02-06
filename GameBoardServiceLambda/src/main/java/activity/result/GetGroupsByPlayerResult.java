package activity.result;

import models.GroupModel;

import java.util.List;

public class GetGroupsByPlayerResult {
    public final List<GroupModel> groupModelList;

    /**
     * Result object containing the List of Groups that a Player belongs to.
     * @param groupModelList List of all the GroupModels that the player belongs to.
     */
    public GetGroupsByPlayerResult(List<GroupModel> groupModelList) {
        this.groupModelList = groupModelList;
    }

    public List<GroupModel> getGroupModelList() {
        return groupModelList;
    }

    //CHECKSTYLE:OFF:Builder

    public static Builder builder(){
        return new Builder();
    }

    public static class Builder {
        private List<GroupModel> groupModelList;

        public Builder withGroupModelList(List<GroupModel> groupModelList) {
            this.groupModelList = groupModelList;
            return this;
        }

        public GetGroupsByPlayerResult build() {
            return new GetGroupsByPlayerResult(groupModelList);
        }
    }
}
