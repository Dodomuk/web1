"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const readline_sync_1 = require("readline-sync");
const ClubWindow_1 = __importDefault(require("./ClubWindow"));
class ClubMenu {
    constructor() {
        this._clubWindow = new ClubWindow_1.default();
    }
    displayMenu() {
        console.log("");
        console.log("..............................");
        console.log(" Club menu ");
        console.log("..............................");
        console.log(" 1. Register");
        console.log(" 2. Find");
        console.log(" 3. Modify");
        console.log(" 4. Remove");
        console.log("..............................");
        console.log(" 5. Membership menu");
        console.log("..............................");
        console.log(" 0. Previous"); //임시로 exit 으로 설정
        console.log("..............................");
    }
    selectMenu() {
        let menuNumber = parseInt(readline_sync_1.question('select : '));
        if (menuNumber >= 0 && menuNumber <= 5) {
            return menuNumber;
        }
        else {
            console.log('Invalid number => ' + menuNumber);
            return -1;
        }
    }
    exitProgram() {
        console.log('종료합니다..');
        process.exit(0);
    }
    show() {
        let inputNumber = 0;
        while (true) {
            this.displayMenu();
            inputNumber = this.selectMenu();
            switch (inputNumber) {
                case 1:
                    this._clubWindow.register();
                    break;
                case 2:
                    this._clubWindow.find();
                    break;
                case 3:
                    this._clubWindow.modify();
                    break;
                case 4:
                    this._clubWindow.remove();
                    break;
                case 5: break;
                case 0:
                    this.exitProgram();
                    break;
            }
        }
    }
}
new ClubMenu().show();
