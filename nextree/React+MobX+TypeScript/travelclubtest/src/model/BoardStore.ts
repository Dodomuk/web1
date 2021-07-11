import { SocialBoard } from "../entity/board/SocialBoard";

class BoardStore{
    
    constructor(private boardMap : Map<string,SocialBoard>){
      if(boardMap == null){
        this.boardMap = new Map<string,SocialBoard>(); 
    }else{
        this.boardMap = boardMap;
    }
    }

        create(board : SocialBoard) : string{

            if(this.boardMap.get(board.getClubId) !== undefined){
                this.boardMap.set(board.getClubId,board);
            }else {
                throw new Error('클럽 아이디가 게시판에 존재하지 않습니다.');
            }
            return board.getClubId;
        }

        retrieve(boardId : string) : SocialBoard | undefined{
            return this.boardMap.get(boardId);
        }
    //    retrieveByName(name : string) : List<SocialBoard>,
        update(board : SocialBoard) : void{
            this.boardMap.set(board.getClubId,board); //어쩌피 중복될 경우 덮어씀
        } 
        delete(boardId : string) : void{
            this.boardMap.delete(boardId);
        } 
        exists(boardId : string) : boolean{
           return this.boardMap.has(boardId);
        }  
}

export default BoardStore;