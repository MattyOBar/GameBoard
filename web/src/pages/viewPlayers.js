import GameBoardClient from '../api/gameBoardClient';
import Header from '../components/header';
import BindingClass from '../util/bindingClass';
import DataStore from '../util/DataStore';

class ViewPlayers extends BindingClass {
    constructor() {
        super();
        this.bindClassMethods(['clientLoaded', 'mount', ], this);
                this.dataStore = new DataStore();
                this.header = new Header(this.dataStore);
    }

}