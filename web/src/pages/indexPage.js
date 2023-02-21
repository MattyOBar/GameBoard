import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

/**
 * Logic needed for the create playlist page of the website.
 */
class Index extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['mount', 'login'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToViewGroups);
        this.dataStore.addChangeListener(this.redirectToCreateProfile);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load GameBoardClient
     */
    mount() {
        document.getElementById('loginButton').addEventListener('click', this.login);

        this.header.addHeaderToPage();

        this.client = new GameBoardClient();
    }

    /**
     * Method to run when the login button is pressed. Call the GameBoardService to login
     * the user.
     */
    async login() {
        console.log("YAY");
        this.client.login();
    }
}

/**
 * Main method to run when the page contents have loaded.
 */
const main = async () => {
    const login = new Index();
    login.mount();
};

window.addEventListener('DOMContentLoaded', main);