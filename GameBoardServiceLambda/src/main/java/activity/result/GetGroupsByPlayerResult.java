package activity.result;

import models.GroupModel;

import java.util.List;
import java.util.Set;

public class GetGroupsByPlayerResult {
    public final List<GroupModel> groupModelList;

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
