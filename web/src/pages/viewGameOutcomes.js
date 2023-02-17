import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewGameOutcomes extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayGroupName'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        document.getElementById('groupName').innerText = "(Loading please wait...)";
        await this.displayGroupName();
    }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new GameBoardClient();
        await this.clientLoaded();
    }

    async displayGroupName() {
            const urlParams = new URLSearchParams(window.location.search);
            const groupId = urlParams.get('groupId');
            const group = await this.client.getGroup(groupId);
            document.getElementById('groupName').innerText = group.groupName;
        }

}

const main = async () => {
    const viewGameOutcomes = new ViewGameOutcomes();
    viewGameOutcomes.mount();
};

window.addEventListener('DOMContentLoaded', main);