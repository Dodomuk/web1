import TravelClub from "../entity/club/TravelClub";
import memoryMap from "./MemoryMap";

class TravelClubStore {

    private clubMap: any;
    private autoIdMap : any;

    constructor() {
        this.clubMap = memoryMap.clubMap;
        this.autoIdMap = memoryMap.autoIdMap;
    }

    count(): number {
        return this.clubMap.size;
    }

    create(newClub: TravelClub): string{

        if (this.exists(newClub.getName)) {
           throw new Error('해당 이름의 클럽이 이미 존재합니다.');
        }

        if(this.autoIdMap.size === 0){
            this.autoIdMap.put("key",1);
        }

        let keySequence = this.autoIdMap.get("key");

        let blankCnt = 5 - keySequence.toString().length;
        let result = '';
        for(let i= 0; i <= blankCnt; i++){
            result = result + '0';
        }
        
        newClub.setAutoId(String(result + keySequence))
        
        this.clubMap.set(newClub.getName, newClub);
        return newClub.getName;
    }

    retrieve(clubId : string): TravelClub {
        return this.clubMap.get(clubId);
    }

    //추후 확인 뒤 retrieveByName으로 바꿀수도 있음 ***미구현
    // retriveByName(name : string): TravelClub | undefined{
       
    //     let foundClub : TravelClub;
        
    //     console.log(this.clubMap);

    //     return undefined;
    // }

    update(newClub: TravelClub): void {
        this.clubMap.set(newClub.getName, newClub);
    }

    delete(clubId: string) {
        this.clubMap.delete(clubId);
    }

    exists(clubId: string): boolean {
        return this.clubMap.has(clubId);
    }
}
export default new TravelClubStore();
