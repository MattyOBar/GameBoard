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
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        const group = await this.client.getGroup(groupId);
        document.getElementById('groupName').innerText = group.groupName;
    }

    //populates the dropdown menu to select winners
    async loadPlayersDropDownAdd() {
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

    async loadPlayersDropDownRemove() {
            const urlParams = new URLSearchParams(window.location.search);
            const groupId = urlParams.get('groupId');
            const group = await this.client.getGroup(groupId);
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
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        var group = await this.client.getGroup(groupId);
        const gameId = document.getElementById("loadGamesDropDown").value;
        const playerWinId = document.getElementById("playersWinDropDown").value;
        var gameOutcome = {groupId:groupId, gameId: gameId, playerWinId: playerWinId};
        gameOutcome = await this.client.createGameOutcome(gameOutcome);

        var updatedGameOutcomeIds = group.gameOutcomeIds;
        updatedGameOutcomeIds.push(gameOutcome.gameOutcomeId);
        group.gameOutcomeIds = updatedGameOutcomeIds;
        await this.client.updateGroup(group);
        location.reload();
    }

    async displayGameOutcomes() {
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        var group = await this.client.getGroup(groupId);

        const gameOutcomeEl = document.getElementById("displayGameOutcomesHere")
        var gameOutcomeIds = group.gameOutcomeIds;
        for (let i = 0; i < gameOutcomeIds.length; i++) {
            const gameOutcome = await this.client.getGameOutcome(gameOutcomeIds[i]);
            const game = await this.client.getGame(gameOutcome.gameId);
            const player = await this.client.getPlayer(gameOutcome.playerWinId);
            let element = document.createElement("div");
            const printThis = "The Game is: " + game.gameName + ".  The winner is " + player.playerName;
            element.innerText = printThis;
            gameOutcomeEl.append(element);
        }
    }

    async deleteGameOutcome() {
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        var group = await this.client.getGroup(groupId);
        const gameId = document.getElementById("loadGamesDropDownRemove").value;
        const playerId = document.getElementById("playersWinDropDownRemove").value;
        console.log("groupId"+ groupId);
        console.log("GameId" + gameId);
        var gameOutcomeModelList = await this.client.getGameOutcomeByGroupId(groupId, gameId);

        console.log("gameOutcomeList" + gameOutcomeModelList);
        var deleted = false;
        console.log(gameOutcomeModelList[0]);
        for (let i = 0; i < gameOutcomeModelList.length; i++) {
            if (playerId == gameOutcomeModelList[i].playerWinId && deleted == false) {
            console.log("YOU GOT HERE");
                await this.client.deleteGameOutcome(gameOutcomeModelList[i]);
                var outcomes = group.gameOutcomeIds;
                console.log(outcomes);
                var updatedGameOutcomeIds = new Array();
                for (let j = 0; j < outcomes.length; j++) {
                    if (outcomes[j] != gameOutcomeModelList[i].gameOutcomeId){
                        updatedGameOutcomeIds.push(outcomes[j]);
                    }
                }
                console.log(updatedGameOutcomeIds)
                group.gameOutcomeIds = updatedGameOutcomeIds;
                await this.client.updateGroup(group);
                deleted = true;
                location.reload()
            }
        }
    }

}

const main = async () => {
    const viewGameOutcomes = new ViewGameOutcomes();
    viewGameOutcomes.mount();
};

window.addEventListener('DOMContentLoaded', main);