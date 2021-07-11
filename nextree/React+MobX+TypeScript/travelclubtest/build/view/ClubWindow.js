"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const readline_sync_1 = require("readline-sync");
const TravelClub_1 = __importDefault(require("../entity/TravelClub"));
const ClubCoordinator_1 = __importDefault(require("../service/ClubCoordinator"));
class ClubWindow {
    constructor() {
        this._clubCoordinator = new ClubCoordinator_1.default();
    }
    register() {
        let newClub = null;
        while (true) {
            let clubName = readline_sync_1.question('club name(0.Club menu) : ');
            if (clubName === '0' || clubName === null || clubName === '') {
                break;
            }
            if (this._clubCoordinator.hasClubs(clubName)) {
                console.log(`Club Name already exists. ==> ${clubName}`);
                continue;
            }
            let intro = readline_sync_1.question('club intro(0.club Menu) : ');
            if (intro === '0') {
                break;
            }
            newClub = new TravelClub_1.default(clubName, intro);
            this._clubCoordinator.register(newClub);
            console.log(`Registered club : ${clubName}`);
        }
    }
    findAll() {
        if (this._clubCoordinator.hasClubs()) {
            console.log('해당 클럽이 존재하지 않습니다.');
            return;
        }
        let allClubs = this._clubCoordinator.findAll();
        console.log('....찾은 클럽 ...');
        for (let value of allClubs) {
            console.log(`Club Name : ${value.name}`);
            console.log(`Club Intro : ${value.intro}`);
            console.log(`Fouded Date : ${value.foundedDate}`);
        }
    }
    findOne() {
        let foundClub = null;
        // if(!this._clubCoordinator.hasClubs()){
        //     console.log('클럽이 존재하지 않습니다.');
        // }
        while (true) {
            let clubName = readline_sync_1.question('Club name to find(0.Club menu):');
            if (clubName === '0') {
                break;
            }
            if (this._clubCoordinator.hasClubs(clubName)) {
                foundClub = this._clubCoordinator.find(clubName);
                break;
            }
            else {
                console.log(`No such club in the storage => ${clubName}`);
            }
        }
        return foundClub;
    }
    find() {
        let foundClub = this.findOne();
        if (foundClub) {
            console.log(`Club Name : ${foundClub.getName}`);
            console.log(`Club Intro : ${foundClub.getIntro}`);
            console.log(`Fouded Date : ${foundClub.getFoundedDate}`);
        }
    }
    modify() {
        let targetClub = this.findOne();
        let newIntro = readline_sync_1.question('New intro(0.Club menu) : ');
        if (newIntro === '0') {
            return;
        }
        this._clubCoordinator.modify(targetClub.getName, newIntro);
        console.log('클럽 소개글이 변경되었습니다.');
    }
    remove() {
        let targetClub = this.findOne();
        let confirmStr = readline_sync_1.question(' Remove this club ?? (Y : yes , N : no) : ');
        if (confirmStr.toLowerCase() === 'y' || confirmStr.toLowerCase() === 'yes') {
            this._clubCoordinator.remove(targetClub.getName);
            console.log(`Removing a club => ${targetClub.getName}`);
        }
        else {
            console.log(`Removing cancelled => ${targetClub.getName}`);
        }
    }
}
exports.default = ClubWindow;
