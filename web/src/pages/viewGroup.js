import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewGroup extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'loadGroupId'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        document.getElementById('groupName').innerText = "(Loading Please Wait...)";
        document.getElementById('favoriteGame').innerText = "(Loading Please Wait...)";
        this.loadGroupId();
    }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new GameBoardClient();
        await this.clientLoaded();
    }

    async loadGroupId() {
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        console.log(groupId);
        const group = await this.client.getGroup(groupId);
        this.dataStore.set('group', group);
        console.log(group);
        document.getElementById('groupName').innerText = group.groupName;
        const favoriteGame = await this.client.getGame(group.favoriteGameId);
        console.log(favoriteGame);
        document.getElementById('favoriteGame').innerText = favoriteGame.gameName;
    }


}

const main = async () => {
    const viewGroup = new ViewGroup();
    viewGroup.mount();
};

window.addEventListener('DOMContentLoaded', main);