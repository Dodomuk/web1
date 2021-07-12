import CommunityMember from "../entity/club/CommunityMember";

class MemberDto {

    private _nickName: any;
    private _birthDay: any;
    
    constructor(
        private email: string,
        private name: string,
        private phoneNumber: string
    ) {
        this.email = email;
        this.name = name;
        this.phoneNumber = phoneNumber;
    }

    MemberDtoSet(member : CommunityMember){
        this.email = member.getId;
        this.name = member.getName;
        this.phoneNumber = member.getPhoneNumber;
        this._birthDay = member.getBirthDay;
        this._nickName = member.getNickName;
    }
    
    toMember() : CommunityMember{

        let member = new CommunityMember(this.email,this.name,this.phoneNumber);

        member.setNickName = this._nickName;
        member.setBirthDay = this._birthDay;

        return member;
    }

    get getEmail() {
        return this.email;
    }

    set setEmail(email: string) { 
        this.email = email;
    }

    get getName() {
        return this.name;
    }

    set setName(name : string){
        this.name = name;
    }
    
    get getNickName() {
        return this._nickName;
    }

    set setNickName(nickName: string) {
        this._nickName = nickName;
    }

    get getPhoneNumber() {
        return this.phoneNumber;
    }

    set setPhoneNumber(phoneNumber : string){
        this.phoneNumber = phoneNumber;
    }

    get getBirthDay() {
        return this._birthDay;
    }

    set setBirthDay(birthDay: string) {
        this._birthDay = birthDay;
    }
}

export default MemberDto;