import TravelClub from "../entity/club/TravelClub";
import memoryMap from "./MemoryMap";

class TravelClubStore {

    private clubMap: any;

    constructor() {
        this.clubMap = memoryMap.clubMap;
    }

    count(): number {
        return this.clubMap.size;
    }

    create(newClub: TravelClub): string{
        if (this.exists(newClub.getName)) {
           throw new Error('해당 이름의 클럽이 이미 존재합니다.');
        }
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
