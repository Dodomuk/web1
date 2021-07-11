"use strict";
var __importDefault = (this && this.__importDefault) || function (mod) {
    return (mod && mod.__esModule) ? mod : { "default": mod };
};
Object.defineProperty(exports, "__esModule", { value: true });
const MemoryMap_1 = __importDefault(require("./MemoryMap"));
const CommunityMember_1 = __importDefault(require("../entity/club/CommunityMember"));
class MemberStore {
    constructor() {
        this.memberMap = MemoryMap_1.default.memberMap;
    }
    create(member) {
        if (this.exists(member.getId)) {
            throw new Error('해당 아이디의 멤버가 이미 존재합니다.');
        }
        this.memberMap.set(member.getId, member);
        return member.getId;
    }
    retrieve(memberId) {
        return this.memberMap.get(memberId);
    }
    retrieveByName(name) {
        let list = [];
        for (let key of this.memberMap) {
            list.push(key);
        }
        console.log(list);
        return list;
    }
    update(member) {
        this.memberMap.set(member.getId, member);
    }
    delete(memberId) {
        this.memberMap.delete(memberId);
    }
    exists(memberId) {
        return this.memberMap.has(memberId);
    }
}
let ms = new MemberStore();
let newMember = new CommunityMember_1.default('nextree@naver.com', 'sss', 'aaa');
let newMember2 = new CommunityMember_1.default('nextree2@naver.com', 'sss', 'aaa');
ms.create(newMember);
ms.create(newMember2);
console.log(newMember);
console.log('===============');
ms.retrieveByName('sss');
exports.default = MemberStore;
