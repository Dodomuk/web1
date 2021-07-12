import TravelClub from "../entity/club/TravelClub";
import CommunityMember from "../entity/club/CommunityMember";
import Posting from "../entity/board/Posting";

let uniqueInstance: any;

class MemoryMap {

    private _clubMap: any;
    private _memberMap : any;
    private _postingMap : any;
    private _autoIdMap : any;

    constructor() {
        if (uniqueInstance) {
            return uniqueInstance;
        }
        this._clubMap = new Map();
        this._memberMap = new Map();
        this._postingMap = new Map();
        this._autoIdMap = new Map();
        uniqueInstance = this; //자기 자신을 참조한다...?
    }

    get clubMap(): Map<string, TravelClub> {
        return this._clubMap;
    }

    get memberMap(): Map<string, CommunityMember>{
        return this._memberMap;
    }

    get postingMap() : Map<string, Posting>{
        return this._postingMap;
    }

    get autoIdMap() : Map<string, number>{
        return this._autoIdMap;
    }
}
export default new MemoryMap();