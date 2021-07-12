import { SocialBoard } from './SocialBoard';
class Posting{
    
    private _usid : string;
    private _boardId : string;
    private _readCount : number;
    private _writtenDate : string | Date;
    
    constructor(
        private board : SocialBoard, 
        private title : string,
        private writerEmail : string,
        private contents : string){
            this._readCount = 0;
            this._usid = board.nextPostingId;
            this._boardId = board.getClubId;
            this.title = title;
            this.writerEmail = writerEmail;
            this.contents = contents;
            this._writtenDate = new Date();
    }

        
    postingSet(
         postingId : string, 
         boardId : string,
         title : string,
         writerEmail : string,
         contents : string){
            this._usid = postingId;
            this._boardId = boardId;
            this.title = title;
            this.writerEmail = writerEmail;
            this.contents = contents;
            this._writtenDate = new Date();
    }

    get getId(){
      return this._usid;   
    }

    set setId(usid : string){
        this._usid = usid;
    }

    get getTitle(){
        return this.title;
    }

    get getWriterEmail(){
        return this.writerEmail;
    }

    get getReadCount(){
        return this._readCount;
    }

    set setReadCount(readCount : number){
        this._readCount = readCount;
    }
    
    get getContents(){
        return this.contents;
    }

    get getWrittenDate(){
        return this._writtenDate;
    }

    set setWrittenDate(writtenDate : string){
        this._writtenDate = writtenDate;
    }

    get getBoardId(){
        return this._boardId;
    }

    set setBoardId(boardId : string){
        this._boardId = boardId;
    }
}
export default Posting;