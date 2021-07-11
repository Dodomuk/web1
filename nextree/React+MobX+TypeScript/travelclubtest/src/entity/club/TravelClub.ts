class TravelClub{

    private foundedDate : Date;

    constructor(
        private name : string,
        private intro : string){
            this.name = name;
            this.intro = intro;
            this.foundedDate = new Date();
        }

        get getName(){
            return this.name;
        }

        set setName(name:string){
            this.name = name;
        }
        
        get getIntro(){
            return this.intro;
        }

        set setIntro(intro:string){
            this.intro = intro;
        } 

        get getFoundedDate(){
            return this.foundedDate;
        }

}

export default TravelClub;
