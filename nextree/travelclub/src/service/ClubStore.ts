import { action, computed, makeObservable, observable } from "mobx";
import TravelClub from "../entity/TravelClub";

class ClubStore {

    private _clubStore: Map<string, TravelClub>;
    private _foundedDate : Date;

    @observable
    private _clubs : TravelClub[] = []; 

    constructor(
    ) {
        makeObservable(this);
        this._foundedDate = new Date();
        this._clubStore = new Map<string,TravelClub>();
        this._clubs.forEach(club => this._clubStore.set(club.getName,club));
    }

    @action
    setTravelClub(name:string,intro:string){
      let travelClub = new TravelClub(name,intro);
    }

    @computed
    get getFoundedDate(){
        return this._foundedDate;
    }

    
    @action
    register(newClub : ClubStore): void {

        if(this.exists(newClub.getName)){
           return alert("이미 존재하는 클럽입니다.");
        }
        
        this._clubStore.set(newClub.getName,newClub);
        return alert("클럽이 등록되었습니다.");
    }

    // @action
    // findClub(name: string): TravelClub | undefined{
    //     return this._clubStore.get(name);
    // }

    // modify(name: string, intro: string): void {
    //     if (!this._clubStore.exists(name)) {
    //         return;
    //     }
    //     let foundClub = this._clubStore.retrieve(name);
    //     foundClub.setIntro = intro;
    //     this._clubStore.update(foundClub);

    // }

    // remove(name: string) {
    //     if (!this._clubStore.exists(name)) {
    //         return;
    //     }
    //     this._clubStore.delete(name);
    // }

    @action
    exists(clubName : string) : boolean {
        return this._clubStore.has(clubName);
    }
}

export default ClubStore;