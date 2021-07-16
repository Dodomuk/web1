import {action,computed,makeObservable,observable,toJS} from "mobx";

interface TravelClub{
    name : string;
    intro : string;
    date : Date;
}
let club = {} as TravelClub;
class ClubStore implements TravelClub{
    
    @observable
    name = '12123123';
    @observable
    intro = 'asdasada';
    @observable
    date = new Date();

    @observable
    private clubMap : Map<string,TravelClub>;

    constructor(){
        makeObservable(this);
        this.clubMap = new Map<string,TravelClub>();
    }

    @computed
    get getName(){
        return this.name;
    }

    @action
    setName(name:string){
        this.name = 'name입니다';
    }

    @computed
    get getIntro(){
        return this.intro;
    }

    @action
    setIntro(intro:string){
        this.intro = 'intro입니다';
    }

    @computed
    get getFoundedDate(){
        return this.date;
    }

}
export default ClubStore;