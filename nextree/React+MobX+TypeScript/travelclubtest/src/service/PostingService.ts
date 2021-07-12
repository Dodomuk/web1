import PostingDto from "../dto/PostingDto";
import BoardStore from "../model/BoardStore";
import PostingStore from "../model/PostingStore";
import ClubStore from "../model/TravelClubStore";

class PostingService {
    
    private _postingStore : any;
    private _boardStore : any;
    private _clubStore : any;

    constructor(){
        this._postingStore = PostingStore;
        this._boardStore = BoardStore;
        this._clubStore = ClubStore;
    }

    register(boardId : string, postingDto : PostingDto) : string{
        let club = this._clubStore.retrieve(boardId);
        
        if(club.getMembershipBy()){
            
        }
        //멤버쉽 관련 로직 작성
    }
}