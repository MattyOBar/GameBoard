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
        this.bindClassMethods(['mount', 'login', 'redirectToViewGroups', 'createProfile', 'redirectToCreateProfile'], this);
        this.dataStore = new DataStore();
        this.dataStore.addChangeListener(this.redirectToWorkout);
        this.dataStore.addChangeListener(this.redirectToCreateProfile);
        this.header = new Header(this.dataStore);
    }

    /**
     * Add the header to the page and load GameBoardClient
     */
    mount() {
        document.getElementById('login').addEventListener('click', this.login);
        document.getElementById('create-profile').addEventListener('click', this.createProfile)

        this.header.addHeaderToPage();

        this.client = new GameBoardClient();
    }

    /**
     * Method to run when the login button is pressed. Call the GameBoardService to login
     * the user.
     */
    async login() {
        document.getElementById('login').addEventListener('click', this.client.login);
        this.redirectToViewGroups;
    }

    /**
     * When the user has logged in, redirect to the page to viewGroupsByPlayer page.
     */
    async redirectToViewGroups() {
        // TODO
        window.alert("Redirect to view a player's group page.")
    }

    /**
     * Method to run when the create profile button is pressed. Call the GameBoardService to login
     * the user.
     */
    async createProfile() {
        document.getElementById('create-profile').addEventListener('click', this.client.login);
        this.redirectToCreateProfile;
    }

    /**
     * When the user has created a login information, redirect to create profile page.
     */
    async redirectToCreateProfile() {
        // TODO
        window.alert("Redirect to create profile page.")
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