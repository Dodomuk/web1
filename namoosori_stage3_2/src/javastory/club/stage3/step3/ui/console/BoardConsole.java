package javastory.club.stage3.step3.ui.console;

import javastory.club.stage3.step3.logic.ServiceLogicLycler;
import javastory.club.stage3.step3.service.BoardService;
import javastory.club.stage3.step3.service.ClubService;
import javastory.club.stage3.step3.service.dto.BoardDto;
import javastory.club.stage3.step3.service.dto.TravelClubDto;
import javastory.club.stage3.step3.util.BoardDuplicationException;
import javastory.club.stage3.step3.util.NoSuchBoardException;
import javastory.club.stage3.step3.util.NoSuchClubException;
import javastory.club.stage3.step3.util.NoSuchMemberException;
import javastory.club.stage3.util.ConsoleUtil;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

import java.util.List;

public class BoardConsole {

    private ClubService clubService;
    private BoardService boardService;

    private ConsoleUtil consoleUtil;
    private Narrator narrator;

    public BoardConsole() {

        this.clubService = ServiceLogicLycler.shareInstance().createClubService();
        this.boardService = ServiceLogicLycler.shareInstance().createBoardService();
        this.narrator = new Narrator(this, TalkingAt.Left);
        this.consoleUtil = new ConsoleUtil(narrator);
    }

    private TravelClubDto findClub(){

        TravelClubDto clubFound = null;

        while(true){
            String clubName = consoleUtil.getValueOf("\n 찾고자 하는 클럽 이름 (0.클럽 메뉴)");

            if(clubName.equals("0")){
                break;
            }

            try{
                clubFound = clubService.findClubByName(clubName);
                narrator.sayln("\t > 찾은 클럽 : " + clubFound);
                break;
            }catch(NoSuchMemberException e){
                narrator.sayln(e.getMessage());
            }
            clubFound = null;
        }
        return clubFound;
    }

    public void register(){

        while(true){
            TravelClubDto targetClub = findClub();
            if(targetClub == null){
                return;
            }
            
            String boardName = consoleUtil.getValueOf("\n 등록하고 싶은 게시판 이름(0.게시판 메뉴)");
            if(boardName.equals("0")){
                return;
            }
            String admilEmail = consoleUtil.getValueOf("\n 멤버의 이메일 admin");

            try{
                BoardDto newBoardDto = new BoardDto(targetClub.getUsid(), boardName, admilEmail);
                boardService.register(newBoardDto);
            }catch(BoardDuplicationException | NoSuchClubException | NoSuchMemberException e){
                narrator.sayln(e.getMessage());
            }
        }
    }

    public void findByName(){
        String boardName = consoleUtil.getValueOf("\n 찾고 싶은 게시판 이름(0.게시판 메뉴)");

        if(boardName.equals("0")){
            return;
        }

        try{
            List<BoardDto> boardDtos = boardService.findByname(boardName);

            int index = 0;

            for(BoardDto boardDto : boardDtos){
                narrator.sayln(String.format("[%d]",index) + boardDto.toString());
                index++;
            }
        }catch(NoSuchBoardException e){
              narrator.sayln(e.getMessage());
        }
    }

    public BoardDto findOne(){
        BoardDto boardFound = null;
        while(true){

            String clubName = consoleUtil.getValueOf("\n 게시판을 찾을 클럽 이름(0.게시판 메뉴)");
            if(clubName.equals("0")){
                break;
            }

            try{
                boardFound = boardService.findByClubName(clubName);
                narrator.sayln("\t >> 찾은 클럽 : " + boardFound);
                break;
            }catch(NoSuchBoardException | NoSuchClubException e){
                narrator.sayln(e.getMessage());
            }
        }
        return boardFound;
    }

    public void modify(){

        BoardDto targetBoard = findOne();
        if(targetBoard == null){
            return;
        }

        String boardName = consoleUtil.getValueOf("\n 수정할 새로운 게시판 이름(0.게시판 메뉴, Enter . no change)");

        if(boardName.equals("0")){
            return;
        }
        if(!boardName.equals("")){
            targetBoard.setName(boardName);
        }
        
        String adminEmail = consoleUtil.getValueOf("\n admin할 멤버의 이메일.(Enter. no change)");
        if(!adminEmail.equals("")){
            targetBoard.setAdminEmail(adminEmail);
        }
        try{
            boardService.modify(targetBoard);
        }catch(BoardDuplicationException | NoSuchClubException | NoSuchMemberException e){
            narrator.sayln(e.getMessage());
        }
    }

    public void remove(){
        BoardDto targetBoard = findOne();
        if(targetBoard == null){
            return;
        }

        String confirmStr = consoleUtil.getValueOf("게시판을 제거하시겠습니까??? ( Y : yes , N : no)");
        if(confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")){
            narrator.sayln("게시판 제거 ----> " + targetBoard.getName());
            boardService.remove(targetBoard.getId());
        }else{
            narrator.sayln("제거가 취소되었습니다..." + targetBoard.getName());
        }
    }
}
