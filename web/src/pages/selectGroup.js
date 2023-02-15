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
//        this.displayGroupsOnPage();
//        const groupList = await this.client.getGroupsByPlayerId();
//        this.dataStore.set('groupList', groupList);
    }

    async mount() {
        this.header.addHeaderToPage();
        document.getElementById('look-up-groups-button').addEventListener('click', this.displayGroupsOnPage)
        this.client = new GameBoardClient();
        await this.clientLoaded();
    }

//    async displayNameOnPage() {
//
//    }

    async displayGroupsOnPage() {
//        document.getElementById('group-buttons').innerText = "(Loading Groups)";
        const playerId = document.getElementById('emailInput').value;
        const player = await this.client.getPlayer(playerId);
        this.dataStore.set('player', player);
        document.getElementById('welcome-name').innerText = "Welcome to GameBoard " + player.playerName;
        const groupsList = player.groupIds;
        console.log(groupsList);

        const groupButtons = document.getElementById('groupName-buttons');
        for (let i = 0; i < groupsList.length; i++) {
            const group = await this.client.getGroup(groupsList[i]);
            let button = document.createElement("button");
            button.innerHTML = group.groupName;
            groupButtons.appendChild(button);
        }
    }
}

const main = async () => {
    const selectGroup = new SelectGroup();
    selectGroup.mount();
};

window.addEventListener('DOMContentLoaded', main);