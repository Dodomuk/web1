"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
class CommunityMember {
    constructor(email, name, phoneNumber) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }
    get getId() {
        return this.email;
    }
    set setEmail(email) {
        if (!this.isValidEmailAddress(email)) {
            throw new Error(`이메일 형식이 올바르지 않습니다. ==> ${email}`);
        }
        this.email = email;
    }
    get getName() {
        return this.name;
    }
    get getNickName() {
        return this._nickName;
    }
    set setNickName(nickName) {
        this._nickName = nickName;
    }
    get getPhoneNumber() {
        return this.phoneNumber;
    }
    get getBirthDay() {
        return this._birthDay;
    }
    set setBirthDay(birthDay) {
        this._birthDay = birthDay;
    }
    isValidEmailAddress(email) {
        const ePattern = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$/;
        if (!ePattern.test(email)) {
            return false;
        }
        else {
            return true;
        }
    }
}
exports.default = CommunityMember;
