"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class TravelClub {
    constructor(name, intro) {
        this.name = name;
        this.intro = intro;
        this.name = name;
        this.intro = intro;
        this.foundedDate = new Date();
    }
    get getName() {
        return this.name;
    }
    set setName(name) {
        this.name = name;
    }
    get getIntro() {
        return this.intro;
    }
    set setIntro(intro) {
        this.intro = intro;
    }
    get getFoundedDate() {
        return this.foundedDate;
    }
}
exports.default = TravelClub;
