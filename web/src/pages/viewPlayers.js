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
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        const group = await this.client.getGroup(groupId);
        document.getElementById('groupName').innerText = group.groupName;
    }

    async displayPlayersInGroup() {
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');

        const group = await this.client.getGroup(groupId);
        document.getElementById('groupName').innerText = group.groupName;

        const playerIdsInGroupSet = group.playerIds;

        const playerNames = document.getElementById('displayPlayerNames')
        for (let i = 0; i < playerIdsInGroupSet.length; i++) {
            var player = await this.client.getPlayer(playerIdsInGroupSet[i]);
            let newDiv = document.createElement("div");
            newDiv.innerText = player.playerName;
            playerNames.appendChild(newDiv);
        }
    }

    async addPlayerToGroup() {
        const playerId = document.getElementById("userInput").value;
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        const group = await this.client.getGroup(groupId);
        var updatedPlayerIds = group.playerIds;
        updatedPlayerIds.push(playerId);
        await this.client.updateGroup(group.groupId, group.groupName, group.favoriteGameId, group.gameIds, group.gameOutcomeIds, updatedPlayerIds);
        location.reload();
    }

    async removePlayerFromGroup() {
        const playerId = document.getElementById("userInput").value;
        const urlParams = new URLSearchParams(window.location.search);
        const groupId = urlParams.get('groupId');
        const group = await this.client.getGroup(groupId);
        var updatedPlayerIds = new Array();
        for (let i = 0; i < group.playerIds.length; i++) {
            if (group.playerIds[i] != playerId) {
                updatedPlayerIds.push(group.playerIds[i]);
            }
        }
        await this.client.updateGroup(group.groupId, group.groupName, group.favoriteGameId, group.gameIds, group.gameOutcomeIds, updatedPlayerIds);
        location.reload();
    }
}

const main = async () => {
    const viewPlayers = new ViewPlayers();
    viewPlayers.mount();
};

window.addEventListener('DOMContentLoaded', main);