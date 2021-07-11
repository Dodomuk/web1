"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
exports.SocialBoard = void 0;
class SocialBoard {
    constructor(clubId, name, email) {
        this.clubId = clubId;
        this.name = name;
        this.email = email;
        this.clubId = clubId;
        this.name = name;
        this.email = email;
        this.seq = 0;
        this.createDate = new Date();
    }
    get getClubId() {
        return this.clubId;
    }
    get getName() {
        return this.name;
    }
    get getEmail() {
        return this.email;
    }
    get nextPostingId() {
        return (this.seq++).toString();
    }
    printDetails() {
        console.log(`${this.clubId}\t${this.seq}\t${this.email}
        \t${this.name}\t,${this.createDate}`);
    }
}
exports.SocialBoard = SocialBoard;
