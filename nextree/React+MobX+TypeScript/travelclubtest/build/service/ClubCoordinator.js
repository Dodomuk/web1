"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const TravelClubStore_1 = __importDefault(require("../model/TravelClubStore"));
class ClubCoordinator {
    constructor() {
        this._clubStore = TravelClubStore_1.default;
    }
    hasClubs(name) {
        return this._clubStore.exists(name);
    }
    register(newClub) {
        return this._clubStore.create(newClub);
    }
    find(name) {
        return this._clubStore.retrieve(name);
    }
    modify(name, intro) {
        if (!this._clubStore.exists(name)) {
            return;
        }
        let foundClub = this._clubStore.retrieve(name);
        foundClub.setIntro = intro;
        this._clubStore.update(foundClub);
    }
    remove(name) {
        if (!this._clubStore.exists(name)) {
            return;
        }
        this._clubStore.delete(name);
    }
}
exports.default = ClubCoordinator;
