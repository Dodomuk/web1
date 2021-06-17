package javastory.club.stage3.step4.ui.console;

import java.util.List;

import javastory.club.stage3.step4.logic.ServiceLogicLycler;
import javastory.club.stage3.step4.service.BoardService;
import javastory.club.stage3.step4.service.ClubService;
import javastory.club.stage3.step4.service.dto.BoardDto;
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.util.BoardDuplicationException;
import javastory.club.stage3.step4.util.NoSuchBoardException;
import javastory.club.stage3.step4.util.NoSuchClubException;
import javastory.club.stage3.step4.util.NoSuchMemberException;
import javastory.club.stage3.util.ConsoleUtil;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

public class BoardConsole {

    private ClubService clubService;
    private BoardService boardService;
    private ConsoleUtil consoleUtil;
    private Narrator narrator;

    public BoardConsole() {
        this.clubService = ServiceLogicLycler.getInstance().createClubService();
        this.boardService = ServiceLogicLycler.getInstance().createBoardService();
        this.narrator = new Narrator(this, TalkingAt.Left);
        this.consoleUtil = new ConsoleUtil(narrator);
    }

    public void register() {
        TravelClubDto targetClub = null;
        while (true) {

            String clubName = consoleUtil.getValueOf("\n club name to find(0.Member menu) ");
            if (clubName.equals("0")) {
                break;
            }

            try {
                targetClub = clubService.findClubByName(clubName);
                narrator.sayln("\t 찾은 클럽 : " + targetClub);
                break;
            } catch (NoSuchClubException e) {
                narrator.sayln(e.getMessage());
            }

            if (targetClub == null) {
                return;
            }

            while (true) {
                String boardName = consoleUtil.getValueOf("\n board name to register(0.Board menu)");
                if (boardName.equals("0")) {
                    return;
                }
                String adminEmail = consoleUtil.getValueOf("\n admin member's email.");

                try {
                    BoardDto newBoardDto = new BoardDto(targetClub.getUsid(), boardName, adminEmail);
                    boardService.register(newBoardDto);
                } catch (BoardDuplicationException | NoSuchClubException | NoSuchMemberException e) {
                    narrator.sayln(e.getMessage());
                }
            }

        }
    }

    public void findByName(){
        String boardName = consoleUtil.getValueOf("\n board name to find(0.Board menu)");
        if (boardName.equals("0")) {
            return;
        }

        try{
            List<BoardDto> boardDtos = boardService.findByName(boardName);

            for (BoardDto boardDto : boardDtos) {
                narrator.sayln(boardDto.toString());
            }
        }catch (NoSuchBoardException e){
            narrator.sayln(e.getMessage());
        }
    }

    public BoardDto findOne() {
        BoardDto boardFound = null;
        while (true) {
            String clubName = consoleUtil.getValueOf("\n Club name to find a board (0.Board menu) ");
            if (clubName.equals("0")) {
                break;
            }

            try {
                boardFound = boardService.findByClubName(clubName);
                narrator.sayln("\t > 찾은 클럽 : " + boardFound);
                break;
            } catch (NoSuchClubException e) {
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

        String boardName = consoleUtil.getValueOf("\n new board name to modify(0.Board menu, Enter. no change)");
        if (boardName.equals("0")) {
            return;
        }
        targetBoard.setName(boardName);

        String adminEmail = consoleUtil.getValueOf("\n new admin member's email.(Enter. no change)");
        targetBoard.setAdminEmail(adminEmail);

        try{
            boardService.modify(targetBoard);

        }catch (NoSuchClubException | NoSuchBoardException | NoSuchMemberException e){
            narrator.sayln(e.getMessage());
        }
    }

    public void remove(){
        BoardDto target =findOne();
        if(target == null){
            return;
        }

        String confirmStr = consoleUtil.getValueOf("Remove this board? (Y:yes, N:no)");

        if(confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")){
            narrator.sayln("Removing a board --> " + target.getName());
            boardService.remove(target.getId());
        }else{
            narrator.sayln("제거가 취소되었습니다.");
        }
    }
}
