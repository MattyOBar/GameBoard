import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class SelectGroup extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'createPlayerIfNewUser', 'displayName', 'displayGroups'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        document.getElementById('welcome-name').innerText = "(Loading please wait...)"
        const currentUser = await this.client.getIdentity();
        this.dataStore.set('currentUser', currentUser);
        await this.createPlayerIfNewUser();
        await this.displayName();
        await this.displayGroups();
    }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new GameBoardClient();
        await this.clientLoaded();
    }

    sync createPlayerIfNewUser() {
        const currentUser = this.dataStore.get('currentUser');
        console.log("currentUser " + currentUser);
        try {
            const player = await this.client.getPlayer(currentUser.email);
            this.dataStore.set('player', player);
        } catch (error) {
        console.log("error was caught");
            var player = await this.client.createPlayer(["should've used an optional"]);
            this.dataStore.set('player', player);
        }

    }
    async displayName() {
        const player = this.dataStore.get('player');
        document.getElementById('welcome-name').innerText = player.playerName;
    }

    async displayGroups() {
        const player = this.dataStore.get('player');
        const groupsList = player.groupIds;
        document.getElementById('loading-message').innerText = "(Loading Please Wait...)";
        const groupButtons = document.getElementById('groupName-buttons');
        if (groupsList.length == 1 && groupsList[0].equals("should've used an optional")) {
            console.log("you need to create or join a group!");
            document.getElementById('loading-message').innerText = "You need to create or join a group!";
        } else {
            for (let i = 0; i < groupsList.length; i++) {
                const group = await this.client.getGroup(groupsList[i]);
                let button = document.createElement("button");
                button.innerHTML = group.groupName;
                button.onclick = function () {
                    window.location.href = "/viewGroup.html?groupId=" + group.groupId;
                };
                groupButtons.appendChild(button);
            }
            document.getElementById('loading-message').innerText = "";
        }




    }

}

const main = async () => {
    const selectGroup = new SelectGroup();
    selectGroup.mount();
};

window.addEventListener('DOMContentLoaded', main);