import { action, computed, makeObservable, observable, toJS } from "mobx";

class ClubStore {

    @observable
    private clubMap: Map<string, string>;
    @observable
    private _foundedDate : Date;
    @observable
    private _clubs : Map<string,string>[] = []; 
    @observable
    private travelClub : {
        name : ''; 
        intro : '';
    };
    @observable
    name : string = '';
    @observable
    intro : string = '';

    constructor(
    ) {
        makeObservable(this);
        this._foundedDate = new Date();
        this.clubMap = new Map<string,string>();
    }

    @computed
    get getName(){
        return this.name;
    }

    @computed
    get getIntro(){
        return this.intro;
    }
    @computed
    get getList(){

        return toJS(this._clubs);
    }
    @computed
    get getClub(){
        return this.clubMap;
    }

    @action
    setName(name:string){
        this.name = name;
    }

    @action
    setIntro(intro:string){
        this.intro = intro;
    }

    @computed
    get getFoundedDate(){
        return this._foundedDate;
    }

    
    @action
    register(name : string, intro : string): void {

        if(this.exists(name)){
           return alert("이미 존재하는 클럽입니다.");
        }
        this.clubMap.set(name,intro);
        console.log("map : " + this.clubMap.get(name) + "~" + this.clubMap.values());
        this._clubs.push(this.clubMap);
        console.log("clubs : " + this._clubs.values);
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

    exists(clubName : string) : boolean {
        return this.clubMap.has(clubName);
    }
}

export default ClubStore;