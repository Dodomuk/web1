import { SocialBoard } from "../entity/board/SocialBoard";

export class BoardDto {

    constructor(
        private clubId : string,
        private name : string,
        private adminEmail : string
        ){

            this.clubId = clubId;
            this.name = name;
            this.adminEmail = adminEmail;

    }

    get getBoardId(){
        return this.clubId;
    }

    get getBoardName(){
        return this.name;
    }

    get getBoardEmail(){
        return this.adminEmail;
    }

    toBoard() : SocialBoard{
        let socialBoard = new SocialBoard(this.clubId,this.name,this.adminEmail);
        return socialBoard;
    }

    printDtoDetails() : void {
        console.log(`${this.clubId}\t${this.name}\t
        ${this.adminEmail}`);
    }
}