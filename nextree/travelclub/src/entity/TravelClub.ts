import { computed, makeObservable, observable } from "mobx";

class TravelClub{

    @observable
    private foundedDate : Date;

    constructor(
        private name : string,
        private intro : string){
            makeObservable(this);
            this.name = name;
            this.intro = intro;
            this.foundedDate = new Date();
        }

        @computed
        get getName(){
            return this.name;
        }

        set setName(name:string){
            this.name = name;
        }
        
        @computed
        get getIntro(){
            return this.intro;
        }

        set setIntro(intro:string){
            this.intro = intro;
        } 

        @computed
        get getFoundedDate(){
            return this.foundedDate;
        }

}

export default TravelClub;
