"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
let uniqueInstance;
class MemoryMap {
    constructor() {
        if (uniqueInstance) {
            return uniqueInstance;
        }
        this._clubMap = new Map();
        this._memberMap = new Map();
        uniqueInstance = this; //자기 자신을 참조한다...?
    }
    get clubMap() {
        return this._clubMap;
    }
    get memberMap() {
        return this._memberMap;
    }
}
exports.default = new MemoryMap();
