import memoryMap from './MemoryMap';
import Posting from '../entity/board/Posting';

class PostingStore{

    private postingMap : any;
    
    constructor(){
        this.postingMap = memoryMap.postingMap;
    }

    create(posting : Posting) : string{
        if(this.exists(posting.getId)){
            throw new Error(`포스팅이 이미 존재합니다 : ${posting.getId}`)
        }
        this.postingMap.set(posting.getId,posting);
        return posting.getId;
    }

    retrieve(postingId : string) : Posting{
        return this.postingMap.get(postingId);
    }

    retrieveByBoardId(boardId : string) : Posting[]{
        
        let postings = [];

        for(let [key,value] of this.postingMap){
            if(key === boardId){
                postings.push(value);
            }
        }
        return postings;
    }

    retrieveByTitle(title : string) : Posting[] {
        let postings = [];

        for(let [,value] of this.postingMap){
            if(value.getTitle === title){
                postings.push(value);
            }
        }
        return postings;
    }

    update(posting : Posting) : void{
        this.postingMap.set(posting.getId, posting);
    }
    
    delete(postingId : string) : void{
        this.postingMap.delete(postingId);
    }

    exists(postingId : string) : boolean{
        return this.postingMap.has(postingId);
    }
}
export default PostingStore;