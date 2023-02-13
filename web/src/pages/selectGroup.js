import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class SelectGroup extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayGroupsOnPage'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        document.getElementById('group-buttons').innerText = "(Loading Groups)";
        const groupList = await this.client.getGroupsByPlayerId();
        this.dataStore.set('groupList', groupList);
    }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new GameBoardClient();
        await this.clientLoaded();
    }

    async displayGroupsOnPage() {
        const groupList = this.datastore.get('groupList');
        console.log(groupList);

        if (!groupList) {
            return;
        }

        let container = document.getElementById('group-buttons');
        for (let i = 0; i < groupList.size(); i++) {
            let button = document.createElement("div");
            button.innerText = groupList[i].getGroupName();
            container.append(button);
        }

    }
}

const main = async () => {
    const selectGroup = new SelectGroup();
    selectGroup.mount();
};

window.addEventListener('DOMContentLoaded', main);