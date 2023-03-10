import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewGameOutcomes extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayGroupName', 'createNewGameOutcome',
        'loadPlayersDropDownAdd', 'loadPlayersDropDownRemove', 'displayGameOutcomes', 'deleteGameOutcome'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        document.getElementById('groupName').innerText = "(Loading please wait...)";
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        const group = await this.client.getGroup(groupId);
        this.dataStore.set('group', group);
        await this.displayGroupName();
        await this.displayGameOutcomes();
    }

    async mount() {
        document.getElementById("addGameOutcomesButton").addEventListener('click', this.loadPlayersDropDownAdd);
        document.getElementById("submitButton").addEventListener('click', this.createNewGameOutcome);
        document.getElementById("removeGameOutcomesButton").addEventListener('click', this.loadPlayersDropDownRemove);
        document.getElementById("submitButtonRemove").addEventListener('click', this.deleteGameOutcome);
        this.header.addHeaderToPage();
        this.client = new GameBoardClient();
        await this.clientLoaded();
    }

    async displayGroupName() {
        const group = this.dataStore.get('group');
        document.getElementById('groupName').innerText = group.groupName;
    }

    async displayGameOutcomes() {
        const gameOutcomeDataList = document.querySelector("dl");
        var child = gameOutcomeDataList.firstElementChild;
        while (child) {
            gameOutcomeDataList.removeChild(child);
            child = gameOutcomeDataList.firstElementChild;
        }
        document.getElementById('playersWinDropDown').style.display = 'none';
        document.getElementById('loadGamesDropDown').style.display = 'none';
        document.getElementById('showPlayerMessage').style.display = 'none';
        document.getElementById('showGameMessage').style.display = 'none';
        document.getElementById('submitButton').style.display = 'none';
        document.getElementById('playersWinDropDownRemove').style.display = 'none';
        document.getElementById('loadGamesDropDownRemove').style.display = 'none';
        document.getElementById('showPlayerMessageRemove').style.display = 'none';
        document.getElementById('showGameMessageRemove').style.display = 'none';
        document.getElementById('submitButtonRemove').style.display = 'none';
        const group = this.dataStore.get('group');
        const gameOutcomeEl = document.getElementById("GameOutcomesList")
        const gameIds = group.gameIds;
        const gameOutcomeIds = group.gameOutcomeIds;
        for (let i = 0; i < gameOutcomeIds.length; i++) {
            var gameOutcome = await this.client.getGameOutcome(gameOutcomeIds[i]);
            const gameId = gameOutcome.gameId;
            var game = await this.client.getGame(gameId);
            var player = await this.client.getPlayer(gameOutcome.playerWinId);
            let element = document.createElement("dt");
            const printThis = "The Game is: " + game.gameName + ".  The winner is " + player.playerName;
            element.innerText = printThis;
            gameOutcomeEl.append(element);
        }
    }

    async loadPlayersDropDownAdd() {
        const group = this.dataStore.get('group');
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

    async loadPlayersDropDownRemove() {
            const group = this.dataStore.get('group');
            const playerIds = group.playerIds;

            const selectPlayer = document.getElementById('playersWinDropDownRemove');
            for (let i = 0; i < playerIds.length; i++) {
                const player = await this.client.getPlayer(playerIds[i]);
                const el = document.createElement("option");
                el.textContent = player.playerName;
                el.value = player.playerId;
                selectPlayer.appendChild(el);
            }
            const gameIds = group.gameIds;
            const selectGame = document.getElementById('loadGamesDropDownRemove');
            for (let i = 0; i < gameIds.length; i++) {
                const game = await this.client.getGame(gameIds[i]);
                const el = document.createElement("option");
                el.textContent = game.gameName;
                el.value = game.gameId;
                selectGame.appendChild(el);
            }
        }

    async createNewGameOutcome() {
        const group = this.dataStore.get('group');
        const gameId = document.getElementById("loadGamesDropDown").value;
        const playerWinId = document.getElementById("playersWinDropDown").value;
        const gameOutcome = await this.client.createGameOutcome(group.groupId, gameId, playerWinId);
        var updatedGameOutcomeIds = group.gameOutcomeIds;
        updatedGameOutcomeIds.push(gameOutcome.gameOutcomeId);
        await this.client.updateGroup(group.groupId, group.groupName, group.favoriteGameId, group.gameIds, updatedGameOutcomeIds, group.playerIds);
        this.displayGameOutcomes();
    }



    async deleteGameOutcome() {
        var group = this.dataStore.get('group');
        const gameId = document.getElementById("loadGamesDropDownRemove").value;
        const playerId = document.getElementById("playersWinDropDownRemove").value;
        var gameOutcomeModelList = await this.client.getGameOutcomeByGroupId(group.groupId, gameId);
        var deleted = false;
        for (let i = 0; i < gameOutcomeModelList.length; i++) {
            if (playerId == gameOutcomeModelList[i].playerWinId && deleted == false) {
                await this.client.deleteGameOutcome(gameOutcomeModelList[i].gameOutcomeId);
                var outcomes = group.gameOutcomeIds;
                console.log(outcomes);
                var updatedGameOutcomeIds = new Array();
                for (let j = 0; j < outcomes.length; j++) {
                    if (outcomes[j] != gameOutcomeModelList[i].gameOutcomeId){
                        updatedGameOutcomeIds.push(outcomes[j]);
                    }
                }
                console.log(updatedGameOutcomeIds)
                group = await this.client.updateGroup(group.groupId, group.groupName, group.favoriteGameId, group.gameIds, updatedGameOutcomeIds, group.playerIds);
                this.dataStore.set('group', group);
                deleted = true;

            }
        }
        this.displayGameOutcomes();
    }

}

const main = async () => {
    const viewGameOutcomes = new ViewGameOutcomes();
    viewGameOutcomes.mount();
};

window.addEventListener('DOMContentLoaded', main);