import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewGames extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayGroupName', 'displayGames'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        document.getElementById('groupName').innerText = "(Loading please wait...)"
        await this.displayGroupName();
        await this.displayGames();
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

    async displayGames() {
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        const group = await this.client.getGroup(groupId);
        const gameNames = document.getElementById("displayGames");
        for (let i = 0; i < group.gameIds.length; i++) {
            var game = await this.client.getGame(group.gameIds[i]);
            let newDiv = document.createElement("div");
            newDiv.innerText = game.gameName;
            gameNames.appendChild(newDiv);
        }
    }
}

const main = async () => {
    const viewGames = new ViewGames();
    viewGames.mount();
};

window.addEventListener('DOMContentLoaded', main);