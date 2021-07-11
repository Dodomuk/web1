import { question } from "readline-sync";
import TravelClub from "../entity/club/TravelClub";
import ClubCoordinator from "../service/ClubService";

class ClubWindow{

    private _clubCoordinator : any;

    constructor(){
        this._clubCoordinator = new ClubCoordinator();
    }

    register(){
        let newClub = null;
        while(true){
            let clubName = question('club name(0.Club menu) : ');
            
            if(clubName === '0' || clubName === null || clubName === ''){
                break;
            }

            if(this._clubCoordinator.hasClubs(clubName)){
                console.log(`Club Name already exists. ==> ${clubName}` );
                continue;
            }
            
            let intro = question('club intro(0.club Menu) : ');
            if(intro === '0'){
                break;
            }

            newClub = new TravelClub(clubName, intro);
            this._clubCoordinator.register(newClub);
            console.log(`Registered club : ${clubName}`);
        }
    }

    findAll(){
        if(this._clubCoordinator.hasClubs()){
            console.log('해당 클럽이 존재하지 않습니다.');
            return;
        }
        
        let allClubs = this._clubCoordinator.findAll();
        console.log('....찾은 클럽 ...');
        
        for( let value of allClubs){
            console.log(`Club Name : ${value.name}`);
            console.log(`Club Intro : ${value.intro}`);
            console.log(`Fouded Date : ${value.foundedDate}`);
        }

    }

    findOne() : TravelClub{
        let foundClub = null;
        // if(!this._clubCoordinator.hasClubs()){
        //     console.log('클럽이 존재하지 않습니다.');
        // }

        while(true){
            let clubName = question('Club name to find(0.Club menu):');

            if(clubName === '0'){
                break;
            }

            if(this._clubCoordinator.hasClubs(clubName)){
                foundClub = this._clubCoordinator.find(clubName);
                break;
            }else{
                console.log(`No such club in the storage => ${clubName}`);
            }
        }
        return foundClub;
    }

    find(){
        let foundClub = this.findOne();

        if(foundClub){
            console.log(`Club Name : ${foundClub.getName}`);
            console.log(`Club Intro : ${foundClub.getIntro}`);
            console.log(`Fouded Date : ${foundClub.getFoundedDate}`);
        }
    }

    modify(){
        let targetClub = this.findOne();

        let newIntro = question('New intro(0.Club menu) : ');

        if(newIntro === '0'){
            return;
        }

        this._clubCoordinator.modify(targetClub.getName,newIntro);

        console.log('클럽 소개글이 변경되었습니다.');
    }

    remove(){
        let targetClub = this.findOne();

        let confirmStr = question(' Remove this club ?? (Y : yes , N : no) : ');
        
        if(confirmStr.toLowerCase() === 'y' || confirmStr.toLowerCase() === 'yes'){
            this._clubCoordinator.remove(targetClub.getName);
            console.log(`Removing a club => ${targetClub.getName}`);
        }else{
            console.log(`Removing cancelled => ${targetClub.getName}`);
        }
    }
}
export default ClubWindow;