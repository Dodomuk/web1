"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
let uniqueInstance;
class MapStorage {
    constructor() {
        if (uniqueInstance) {
            return uniqueInstance;
        }
        this.clubMap = new Map();
        uniqueInstance = this; //자기 자신을 참조한다...?
    }
    get getClubMap() {
        return this.clubMap;
    }
}
const map1 = new MapStorage();
const map2 = new MapStorage();
console.log(map1 === map2);
console.log(map1.getClubMap === map2.getClubMap);
exports.default = new MapStorage();
