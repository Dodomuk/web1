import { title } from "process";
import { writer } from "repl";
import Posting from "../entity/board/Posting";

class PostingDto {

    private _usid : string;
    private _readCount : number; 
    private _writtenDate : Date | string;

    constructor(
        private title : string,
        private writerEmail : string,
        private contents : string){
            this._readCount = 0;
            this.title = title;
            this.writerEmail = writerEmail;
            this.contents = contents;
            this._writtenDate = new Date();
    }

    postingDtoSet(posting : Posting){
        this._usid = posting.getId;
        this.title = posting.getTitle;
        this.writerEmail = posting.getWriterEmail;
        this.contents = posting.getContents;
        this._writtenDate = posting.getWrittenDate;
        this._readCount = posting.getReadCount;
    }

    get getUsid(){
        return this._usid;
    }

    set setUsid(usid : string){
        this._usid = usid;
    }

    get getTitle(){
        return this.title;
    }

    set setTitle(title : string){
        this.title = title;
    }

    get getWriterEmail(){
        return this.writerEmail;
    }

    get getContents(){
        return this.contents;
    }

    set setContents(contents : string){
        this.contents = contents;
    }
}
export default PostingDto;