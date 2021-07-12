import MemberDto from "../dto/MemberDto";
import memberStore from "../model/MemberStore";

class MemberService{
    
    private _memberStore : any;

    constructor(){
        this._memberStore = memberStore;
    }

    register(memberDto : MemberDto) : void{
        let email = memberDto.getEmail;
        if(this._memberStore.retrieve(email)){
           throw new Error("해당 이메일을 가진 멤버가 이미 존재합니다."); 
        }
        this._memberStore.create(memberDto.toMember);
    }

    find(memberName : string) : MemberDto[] {
        
        let members = this._memberStore.retrieveByName(memberName);

        if(members.length === 0){
            throw new Error(`해당 이름을 가진 멤버가 존재하지 않습니다 : ${memberName}`)
        }

        let memberList = [];
        for(let value of members){
            memberList.push(value);
        }

        return memberList;
    }

    modify(memberDto : MemberDto) : void{
        let targetMember = this._memberStore.retrieve(memberDto.getEmail);

        if(!targetMember){
            throw new Error("해당 이메일을 가진 멤버가 존재하지 않습니다.");
        }
        
        if(memberDto.getNickName === ''){
            memberDto.setName = targetMember.getName;
        }
        if(memberDto.getPhoneNumber === ''){
            memberDto.setPhoneNumber = targetMember.getPhoneNumber;
        }
        if(memberDto.getBirthDay === ''){
            memberDto.setBirthDay = targetMember.getBirthDay;
        }

        this._memberStore.update(memberDto.toMember);
    }

    remove(memberId : string) : void{
        if(this._memberStore.exists(memberId)){
            throw new Error(`해당 이메일의 멤버가 존재하지 않습니다 : ${memberId}`);
        }

        this._memberStore.delete(memberId);
    }
}
export default MemberService;