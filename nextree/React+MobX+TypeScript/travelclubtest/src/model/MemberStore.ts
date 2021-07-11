import memoryMap from './MemoryMap';
import CommunityMember from '../entity/club/CommunityMember';

class MemberStore {

    private memberMap: any;

    constructor() {
        this.memberMap = memoryMap.memberMap;
    }

    create(member: CommunityMember): string {
        if (this.exists(member.getId)) {
            throw new Error('해당 아이디의 멤버가 이미 존재합니다.');
        }
        this.memberMap.set(member.getId, member);
        return member.getId;
    }

    retrieve(memberId: string): CommunityMember {
        return this.memberMap.get(memberId);
    }

    retrieveByName(name: string): CommunityMember[] {

        let list = [];

        for (let key of this.memberMap) {
                list.push(key);
        }
        console.log(list);
        return list;
    }
    update(member: CommunityMember): void {
        this.memberMap.set(member.getId, member);
    }

    delete(memberId: string): void {
        this.memberMap.delete(memberId);
    }

    exists(memberId: string): boolean {
        return this.memberMap.has(memberId);
    }
}
let ms = new MemberStore();
let newMember = new CommunityMember('nextree@naver.com', 'sss', 'aaa');
let newMember2 = new CommunityMember('nextree2@naver.com', 'sss', 'aaa');
ms.create(newMember);
ms.create(newMember2);
console.log(newMember);
console.log('===============');
ms.retrieveByName('sss');

export default MemberStore;