import TravelClub from "../entity/club/TravelClub";
import CommunityMember from "../entity/club/CommunityMember";

let uniqueInstance: any;

class MemoryMap {

    private _clubMap: any;
    private _memberMap : any;

    constructor() {
        if (uniqueInstance) {
            return uniqueInstance;
        }
        this._clubMap = new Map();
        this._memberMap = new Map();
        uniqueInstance = this; //자기 자신을 참조한다...?
    }

    get clubMap(): Map<string, TravelClub> {
        return this._clubMap;
    }

    get memberMap(): Map<string, CommunityMember>{
        return this._memberMap;
    }
}
export default new MemoryMap();