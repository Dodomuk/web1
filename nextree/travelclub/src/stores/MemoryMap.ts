import TravelClub from "../entity/TravelClub";

let uniqueInstance: any;

class MemoryMap {

    private _clubMap: any;

    constructor() {
        if (uniqueInstance) {
            return uniqueInstance;
        }
        this._clubMap = new Map();
        uniqueInstance = this; //자기 자신을 참조한다...?
    }

    get clubMap(): Map<string, TravelClub> {
        return this._clubMap;
    }

}
export default new MemoryMap();