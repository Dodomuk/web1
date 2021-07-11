"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const MemoryMap_1 = __importDefault(require("./MemoryMap"));
class TravelClubStore {
    constructor() {
        this.clubMap = MemoryMap_1.default.clubMap;
    }
    count() {
        return this.clubMap.size;
    }
    create(newClub) {
        if (this.exists(newClub.getName)) {
            throw new Error('해당 이름의 클럽이 이미 존재합니다.');
        }
        this.clubMap.set(newClub.getName, newClub);
        return newClub.getName;
    }
    retrieve(clubId) {
        return this.clubMap.get(clubId);
    }
    //추후 확인 뒤 retrieveByName으로 바꿀수도 있음 ***미구현
    // retriveByName(name : string): TravelClub | undefined{
    //     let foundClub : TravelClub;
    //     console.log(this.clubMap);
    //     return undefined;
    // }
    update(newClub) {
        this.clubMap.set(newClub.getName, newClub);
    }
    delete(clubId) {
        this.clubMap.delete(clubId);
    }
    exists(clubId) {
        return this.clubMap.has(clubId);
    }
}
exports.default = new TravelClubStore();
