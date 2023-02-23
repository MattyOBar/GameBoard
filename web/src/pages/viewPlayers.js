import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewPlayers extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayGroupName' , 'displayPlayersInGroup', 'addPlayerToGroup', 'removePlayerFromGroup'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        document.getElementById('groupName').innerText = "(Loading please wait...)"
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        const group = await this.client.getGroup(groupId);
        this.dataStore.set('group', group);
        await this.displayGroupName();
        await this.displayPlayersInGroup();
    }

    async mount() {
        document.getElementById("addPlayerButton").addEventListener('click', this.addPlayerToGroup);
        document.getElementById("removePlayerButton").addEventListener('click', this.removePlayerFromGroup);
        this.header.addHeaderToPage();
        this.client = new GameBoardClient();
        await this.clientLoaded();
    }

    async displayGroupName() {
        const group = this.dataStore.get('group');
        document.getElementById('groupName').innerText = group.groupName;
    }

    async displayPlayersInGroup() {
        const playerNamesList = document.querySelector("dl");
        var child = playerNamesList.firstElementChild;
        while (child) {
            playerNamesList.removeChild(child);
            child = playerNamesList.firstElementChild;
        }
        const group = this.dataStore.get('group');
        document.getElementById('groupName').innerText = group.groupName;
        const playerIdsInGroupSet = group.playerIds;
        for (let i = 0; i < playerIdsInGroupSet.length; i++) {
            var player = await this.client.getPlayer(playerIdsInGroupSet[i]);
            let newDiv = document.createElement("dt");
            newDiv.innerText = player.playerName + "   |    " + player.playerId;
            playerNamesList.appendChild(newDiv);
        }
    }

    async clearDisplay() {
        const playerNamesList = document.querySelector("dl");
        var child = playerNamesList.firstElementChild;
        while (child) {
            playerNamesList.removeChild(child);
            child = playerNamesList.firstElementChild;
        }
        const inputField = document.getElementById("inputField");
        inputField.innerText = ""
        document.getElementById("loadingMessage").style.display="none";
        document.getElementById("userInput").value = "";
    }

    async addPlayerToGroup() {
        const playerId = document.getElementById("userInput").value;
        const group = this.dataStore.get('group');

        var player = await this.client.getPlayer(playerId, (error) => {
            var warningMessage = document.getElementById("warningMessage");
            warningMessage.innerText = `Error: ${error.message}`;
        });
        var playerGroupIds = player.groupIds;
        if (playerGroupIds.length == 1) {
            if (playerGroupIds[0] == "should've used an optional") {
            playerGroupIds[0] = group.groupId;
            }
        }
        playerGroupIds.push(group.groupId);
        player = await this.client.updatePlayer(player.playerId, player.playerName, playerGroupIds);
        var updatedPlayerIds = group.playerIds;
        updatedPlayerIds.push(playerId);
        await this.client.updateGroup(group.groupId, group.groupName, group.favoriteGameId, group.gameIds, group.gameOutcomeIds, updatedPlayerIds);

        this.displayPlayersInGroup();
    }

    async removePlayerFromGroup() {
        const playerId = document.getElementById("userInput").value;
        var group = this.dataStore.get('group');
        var player = await this.client.getPlayer(playerId);
        var updatedGroupIds = new Array();
        for (let j = 0; j < player.groupIds.length; j++) {
            if (player.groupIds[j] != group.groupId) {
                updatedGroupIds.push(player.groupIds[j]);
            }
        }
        if (updatedGroupIds.length == 0) {
            updatedGroupIds.push("should've used an optional");
        }
        player = await this.client.updatePlayer(player.playerId, player.playerName, updatedGroupIds);

        var updatedPlayerIds = new Array();
        for (let i = 0; i < group.playerIds.length; i++) {
            if (group.playerIds[i] != playerId) {
                updatedPlayerIds.push(group.playerIds[i]);
            }
        }
        group = await this.client.updateGroup(group.groupId, group.groupName, group.favoriteGameId, group.gameIds, group.gameOutcomeIds, updatedPlayerIds);
        this.dataStore.set('group', group);

        this.displayPlayersInGroup();
    }
}

const main = async () => {
    const viewPlayers = new ViewPlayers();
    viewPlayers.mount();
};

window.addEventListener('DOMContentLoaded', main);