
export class SocialBoard{

    private seq : number;
    private createDate : Date;

    constructor(
        private clubId : string, 
        private name : string,
        private email : string
        ){
        this.clubId = clubId;
        this.name = name;
        this.email = email;
        this.seq = 0;
        this.createDate = new Date(); 
    }

    get getClubId(): string{
        return this.clubId;
    }

    get getName(): string{
        return this.name;
    }

    get getEmail():string{
        return this.email;
    }
    
    get nextPostingId(): string{
        return (this.seq++).toString();
    }

    printDetails() : void {
        console.log(`${this.clubId}\t${this.seq}\t${this.email}
        \t${this.name}\t,${this.createDate}`);
    }
}
