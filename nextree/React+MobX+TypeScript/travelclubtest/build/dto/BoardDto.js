"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.BoardDto = void 0;
const SocialBoard_1 = require("../entity/board/SocialBoard");
class BoardDto {
    constructor(clubId, name, adminEmail) {
        this.clubId = clubId;
        this.name = name;
        this.adminEmail = adminEmail;
        this.clubId = clubId;
        this.name = name;
        this.adminEmail = adminEmail;
    }
    get getBoardId() {
        return this.clubId;
    }
    get getBoardName() {
        return this.name;
    }
    get getBoardEmail() {
        return this.adminEmail;
    }
    toBoard() {
        let socialBoard = new SocialBoard_1.SocialBoard(this.clubId, this.name, this.adminEmail);
        return socialBoard;
    }
    printDtoDetails() {
        console.log(`${this.clubId}\t${this.name}\t
        ${this.adminEmail}`);
    }
}
exports.BoardDto = BoardDto;
