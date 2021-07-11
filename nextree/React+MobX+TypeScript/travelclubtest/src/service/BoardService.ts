
// import { BoardDto } from "../dto/BoardDto";
// import { SocialBoard } from "../entity/SocialBoard";
// import BoardStore from "../model/BoardStore";

// interface Supervisor{
//     create(board : SocialBoard) : string,
//     retrieve(boardId : string) : SocialBoard,
// //    retrieveByName(name : string) : List<SocialBoard>,
//     update(board : SocialBoard) : void,
//     delete(boardId : string) : void,
//     exists(boardId : string) : boolean,  
// }

// class BoardService{

//     constructor(private boardStore : Supervisor){
//         this.boardStore = new BoardStore();
//     }

//     register(board:BoardDto) : string{
        
//         let boardId = board.getBoardId;

//         if(this.boardStore.exists(boardId)){
//             throw new Error("게시판이 클럽에 이미 존재합니다.");
//         }
        
//         //클럽이 존재하는지 확인하는 로직 추후 작성

//         return board.getBoardEmail//추후  클럽의 멤버십 가입된 이메일을 확인하는 로직 작성

//     }
//     find(boardId : string) : BoardDto{
        
//         let boardDto = undefined;
        
//         const boardFound = this.boardStore.retrieve(boardId);

//         if(boardFound !== undefined){
//             boardDto = new BoardDto(boardFound.getClubId,boardFound.getName,boardFound.getEmail);
//         }else{
//             throw new Error("해당 아이디의 게시판이 존재하지 않습니다.");
//         }

//         return boardDto;
        
//     }
//     // findByName(boardName:string) : BoardDto;
//     // findByClubName(clubName : string) : BoardDto;
//     modify(boardDto:BoardDto) : void{

//         if(!this.boardStore.exists(boardDto.getBoardId)){
//             throw new Error("해당 아이디를 가진 게시판이 존재하지 않습니다.");
//         }

//         //공백 부분 처리 로직 미작성(원문 StringUtil)

//         this.boardStore.update(boardDto.toBoard());
//     }
//     remove(boardId:string) : void{
//         if(!this.boardStore.exists(boardId)){
//             throw new Error("해당 아이디의 게시판이 존재하지 않습니다.");
//         }

//         this.boardStore.delete(boardId);
//     }
    

// }
// export default BoardService;