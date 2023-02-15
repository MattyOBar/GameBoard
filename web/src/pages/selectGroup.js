import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class SelectGroup extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', 'displayNameAndGroupsOnPage', 'displayGroupsOnPage'], this);
        this.dataStore = new DataStore();
        this.header = new Header(this.dataStore);
    }

    async clientLoaded() {
        document.getElementById('welcome-name').innerText = "(Loading please wait...)"

        this.displayNameAndGroupsOnPage();
//        this.displayGroupsOnPage();
    }

    async mount() {
        this.header.addHeaderToPage();
        this.client = new GameBoardClient();
        await this.clientLoaded();
    }

    async displayNameAndGroupsOnPage() {
        const currentUser = await this.client.getIdentity();
        const playerId = currentUser.email;
        const player = await this.client.getPlayer(playerId);
        this.dataStore.set('player', player);
        document.getElementById('welcome-name').innerText = player.playerName;
        const groupsList = player.groupIds;
        console.log(groupsList);
        document.getElementById('loading-message').innerText = "(Loading Please Wait...)";
        const groupButtons = document.getElementById('groupName-buttons');
            for (let i = 0; i < groupsList.length; i++) {
                const group = await this.client.getGroup(groupsList[i]);
                let button = document.createElement("button");
                button.innerHTML = group.groupName;
                button.onclick = function () {
                    location.href = "/viewGroup.html"
                };
                groupButtons.appendChild(button);
            }
        document.getElementById('loading-message').innerText = "";
    }

    async displayGroupsOnPage() {
        const groupsList = player.groupIds;
        console.log(groupsList);

        const groupButtons = document.getElementById('groupName-buttons');
        for (let i = 0; i < groupsList.length; i++) {
            const group = await this.client.getGroup(groupsList[i]);
            let button = document.createElement("button");
            button.innerHTML = group.groupName;
            button.onclick = function () {
                location.href = "/viewGroup.html"
            };
            groupButtons.appendChild(button);
        }
    }
}

const main = async () => {
    const selectGroup = new SelectGroup();
    selectGroup.mount();
};

window.addEventListener('DOMContentLoaded', main);