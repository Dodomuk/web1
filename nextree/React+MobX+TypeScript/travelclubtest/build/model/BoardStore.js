"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class BoardStore {
    constructor(boardMap) {
        this.boardMap = boardMap;
        if (boardMap == null) {
            this.boardMap = new Map();
        }
        else {
            this.boardMap = boardMap;
        }
    }
    create(board) {
        if (this.boardMap.get(board.getClubId) !== undefined) {
            this.boardMap.set(board.getClubId, board);
        }
        else {
            throw new Error('클럽 아이디가 게시판에 존재하지 않습니다.');
        }
        return board.getClubId;
    }
    retrieve(boardId) {
        return this.boardMap.get(boardId);
    }
    //    retrieveByName(name : string) : List<SocialBoard>,
    update(board) {
        this.boardMap.set(board.getClubId, board); //어쩌피 중복될 경우 덮어씀
    }
    delete(boardId) {
        this.boardMap.delete(boardId);
    }
    exists(boardId) {
        return this.boardMap.has(boardId);
    }
}
exports.default = BoardStore;
