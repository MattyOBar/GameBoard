import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewGroup extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'loadGroupId', 'redirectToViewPlayers','redirectToViewGames','redirectToViewGameOutcomes'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        document.getElementById('groupName').innerText = "(Loading Please Wait...)";
        document.getElementById('favoriteGame').innerText = "(Loading Please Wait...)";
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        const group = await this.client.getGroup(groupId);
        this.dataStore.set('group', group);
        this.loadGroupId();
        this.redirectToViewPlayers();
        this.redirectToViewGames();
        this.redirectToViewGameOutcomes();
    }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new GameBoardClient();
        await this.clientLoaded();
    }

    async loadGroupId() {
        const group = this.dataStore.get('group');
        document.getElementById('groupName').innerText = group.groupName;
        const favoriteGame = await this.client.getGame(group.favoriteGameId);
        console.log(favoriteGame);
        document.getElementById('favoriteGame').innerText = "The Group's favorite game is " + favoriteGame.gameName;
    }

    async redirectToViewPlayers() {
        const viewPlayersButton = document.getElementById('viewPlayersButton');
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        let playersButton = document.createElement("button");
        playersButton.innerHTML = "View Players";
        playersButton.onclick = function () {
            window.location.href = "/viewPlayers.html?groupId=" + groupId;
        };
        viewPlayersButton.appendChild(playersButton);
    }

    async redirectToViewGames() {
        const viewGamesButton = document.getElementById('viewGamesButton');
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        let gamesButton = document.createElement("button");
        gamesButton.innerHTML = "View Games";
        gamesButton.onclick = function () {
            window.location.href = "/viewGames.html?groupId=" + groupId;
        };
        viewGamesButton.appendChild(gamesButton);
    }

    async redirectToViewGameOutcomes() {
        const viewGameOutcomesButton = document.getElementById('viewGameOutcomesButton');
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        let gameOutcomeButton = document.createElement("button");
        gameOutcomeButton.innerHTML = "View GameOutcomes";
        gameOutcomeButton.onclick = function () {
            window.location.href = "/viewGameOutcomes.html?groupId=" + groupId;
        };
        viewGameOutcomesButton.appendChild(gameOutcomeButton);
    }

}

const main = async () => {
    const viewGroup = new ViewGroup();
    viewGroup.mount();
};

window.addEventListener('DOMContentLoaded', main);