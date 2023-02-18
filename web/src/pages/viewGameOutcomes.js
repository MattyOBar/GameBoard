import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewGameOutcomes extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayGroupName', 'createNewGameOutcome', 'loadPlayersDropDown', 'loadGamesDropDown'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        document.getElementById('groupName').innerText = "(Loading please wait...)";
        await this.displayGroupName();
    }

    async mount() {
        document.getElementById("addGameOutcomesButton").addEventListener('click', this.loadPlayersDropDown);
//        await document.getElementById("addGameOutcomesButton").addEventListener('click', this.loadGamesDropDown);
        document.getElementById("submitButton").addEventListener('click', this.createNewGameOutcome);
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

    //populates the dropdown menu to select winners
    async loadPlayersDropDown() {
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        const group = await this.client.getGroup(groupId);
        const playerIds = group.playerIds;

        const selectPlayer = document.getElementById('playersWinDropDown');
        for (let i = 0; i < playerIds.length; i++) {
            const player = await this.client.getPlayer(playerIds[i]);
            const el = document.createElement("option");
            el.textContent = player.playerName;
            el.value = player.playerId;
            selectPlayer.appendChild(el);
        }
        const gameIds = group.gameIds;
        const selectGame = document.getElementById('loadGamesDropDown');
        for (let i = 0; i < gameIds.length; i++) {
            const game = await this.client.getGame(gameIds[i]);
            const el = document.createElement("option");
            el.textContent = game.gameName;
            el.value = game.gameId;
            selectGame.appendChild(el);
        }
    }

    async loadGamesDropDown() {
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        const group = await this.client.getGroup(groupId);
        const gameIds = group.gameIds;

        const select = document.getElementById('loadGamesDropDown');
        for (let i = 0; i < gameIds.length; i++) {
            const game = await this.client.getGame(gameIds[i]);
            const el = document.createElement("option");
            el.textContent = game.gameName;
            el.value = game.gameId;
            select.appendChild(el);
        }
    }

    async createNewGameOutcome() {
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        const group = await this.client.getGroup(groupId);
        const gameId = document.getElementById("loadGamesDropDown");
        const playerWinId = document.getElementById("playersWinDropDown");
        const gameOutcome = await this.client.createGameOutcome(groupId, gameId, playerWinId);
        console.log(gameOutcome);

    }

}

const main = async () => {
    const viewGameOutcomes = new ViewGameOutcomes();
    viewGameOutcomes.mount();
};

window.addEventListener('DOMContentLoaded', main);