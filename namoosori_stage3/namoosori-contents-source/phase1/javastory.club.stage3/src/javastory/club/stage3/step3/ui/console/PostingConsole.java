package javastory.club.stage3.step3.ui.console;

import javastory.club.stage3.step3.logic.ServiceLogicLycler;
import javastory.club.stage3.step3.service.BoardService;
import javastory.club.stage3.step3.service.PostingService;
import javastory.club.stage3.step3.service.ServiceLycler;
import javastory.club.stage3.step3.service.dto.BoardDto;
import javastory.club.stage3.step3.service.dto.PostingDto;
import javastory.club.stage3.step3.util.NoSuchBoardException;
import javastory.club.stage3.step3.util.NoSuchClubException;
import javastory.club.stage3.step3.util.NoSuchMemberException;
import javastory.club.stage3.step3.util.NoSuchPostingException;
import javastory.club.stage3.util.ConsoleUtil;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

import java.util.List;

public class PostingConsole {

    private BoardDto currentBoard;

    private BoardService boardService;
    private PostingService postingService;

    private ConsoleUtil consoleUtil;
    private Narrator narrator;

    public PostingConsole() {
        ServiceLycler serviceFactory = ServiceLogicLycler.shareInstance();
        this.boardService = serviceFactory.createBoardService();
        this.postingService = serviceFactory.createPostingService();

        this.narrator = new Narrator(this, TalkingAt.Left);
        this.consoleUtil = new ConsoleUtil(narrator);
    }

    public boolean hasCurrentBoard() {
        return currentBoard != null;
    }

    public String requestCurrentBoardName() {

        String clubName = null;

        if (hasCurrentBoard()) {
            clubName = currentBoard.getName();
        }

        return clubName;

    }

    public void findBoard() {
        BoardDto boardFound = null;
        while (true) {
            String clubName = consoleUtil.getValueOf("\n 찾을 게시판의 클럽 이름(0.포스팅 메뉴)");
            if (clubName.equals("0")) {
                break;
            }
            try {
                boardFound = boardService.findByClubName(clubName);
                narrator.sayln("\t > 찾은 게시판 : " + boardFound);
                break;
            } catch (NoSuchClubException e) {
                narrator.sayln(e.getMessage());
            }
            boardFound = null;
        }
        this.currentBoard = boardFound;
    }

    public void register() {

        if (!hasCurrentBoard()) {
            narrator.sayln("아직 지정된 게시판이 없습니다. 게시판을 먼저 지정해주세요 . ");
            return;
        }

        while (true) {
            String title = consoleUtil.getValueOf("\n 포스팅 제목(0.포스팅 메뉴) ");
            if (title.equals("0")) {
                return;
            }

            String writerEmail = consoleUtil.getValueOf("\n 포스팅할 작성자의 이메일");
            String contents = consoleUtil.getValueOf("\n 포스팅 컨텐츠들.");

            try {
                PostingDto postingDto = new PostingDto(title, writerEmail, contents);
                postingDto.setUsid(postingService.register(currentBoard.getId(), postingDto));
                narrator.sayln("포스팅 등록 ---> " + postingDto);

            } catch (NoSuchBoardException | NoSuchMemberException e) {
                narrator.sayln(e.getMessage());
            }
        }
    }

    public void findByBoardId() {

        boardChecker();

        try {
            List<PostingDto> postings = postingService.findByBoardId(currentBoard.getId());
            int idx = 0;
            for (PostingDto postingDto : postings) {
                narrator.sayln(String.format("[%d}", idx) + postingDto);
                idx++;
            }
        } catch (NoSuchBoardException e) {
            narrator.sayln(e.getMessage());
        }
    }

    public void find(){
       boardChecker();

       PostingDto postingDto = null;
       while(true){
           String postingId = consoleUtil.getValueOf("\n 찾을 포스팅 아이디(0.포스팅 메뉴)");
           if(postingId.equals("0")){
               break;
           }

           try{
               postingDto = postingService.find(postingId);
               narrator.sayln("\t >> 찾은 포스팅 : " + postingDto);
           }catch (NoSuchPostingException e){
               narrator.sayln(e.getMessage());
           }
       }
    }

    public PostingDto findOne(){
        boardChecker();

        PostingDto postingDto = null;

        while(true){
            String postingId = consoleUtil.getValueOf("\n 찾을 포스팅 아이디(0.포스팅 메뉴)");
            if(postingId.equals("0")){
                break;
            }

            try{
                postingDto = postingService.find(postingId);
                narrator.sayln("\t >>> 찾을 포스팅 : " + postingDto);
                break;
            }catch(NoSuchPostingException e){
                narrator.sayln(e.getMessage());
            }
            postingDto = null;
        }
        return postingDto;
    }

    public void modify(){
        PostingDto targetPosting = findOne();
        if(targetPosting == null){
            return;
        }

        String newTitle = consoleUtil.getValueOf("\n 새로운 포스팅 제목(0.포스팅 메뉴, Enter . no change");
        if(newTitle.equals("0")){
            return;
        }
        if(!newTitle.isEmpty()){
            targetPosting.setTitle(newTitle);
        }

        String contents = consoleUtil.getValueOf("\n 새로운 포스팅 컨텐츠(Enter, no change)");
        if(!contents.isEmpty()){
            targetPosting.setContents(contents);
        }

        try{
            postingService.modify(targetPosting);
            narrator.sayln("\n 수정된 포스팅 : " + targetPosting);
        }catch (NoSuchPostingException e){
            narrator.sayln(e.getMessage());
        }
    }

    public void remove(){

        PostingDto targetPosting = findOne();
        if(targetPosting == null){
            return;
        }

        String confirmStr = consoleUtil.getValueOf("이 포스팅을 제거하시겠습니까??? (Y: yes , N: no)");
        if(confirmStr.toLowerCase().equals("yes") || confirmStr.toLowerCase().equals("y")){
            postingService.remove(targetPosting.getUsid());
        }else{
            narrator.sayln("제거가 취소되었습니다 ....  =====> " + targetPosting.getTitle());
        }
    }

    public void boardChecker(){
        if (!hasCurrentBoard()) {
            narrator.sayln("지정된 게시판이 없습니다. 게시판을 먼저 지정해주세요.");
            return;
        }
    }
}
