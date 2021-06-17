package javastory.club.stage3.step4.logic;

import javastory.club.stage3.step4.da.map.io.MemoryMap;
import javastory.club.stage3.step4.service.BoardService;
import javastory.club.stage3.step4.service.ClubService;
import javastory.club.stage3.step4.service.MemberService;
import javastory.club.stage3.step4.service.PostingService;
import javastory.club.stage3.step4.service.ServiceLycler;

public class ServiceLogicLycler implements ServiceLycler{

    private static ServiceLycler lycler;

    private ClubService clubService;
    private MemberService memberService;
    private BoardService boardService;

    public ServiceLogicLycler() {
    }

    public static ServiceLycler getInstance(){
        if(lycler == null){
            lycler = new ServiceLogicLycler();
        }
        return lycler;
    }

    @Override
    public BoardService createBoardService() {

        if(boardService == null){
            boardService = new BoardServiceLogic();
        }
        return boardService;
    }

    @Override
    public ClubService createClubService() {

        if(clubService == null){
            clubService = new ClubServiceLogic();
        }
        return clubService;
    }

    @Override
    public MemberService createMemberService() {

        if(memberService == null){
            memberService = new MemberServiceLogic();
        }
        return memberService;
    }

    @Override
    public PostingService createPostingService() {
        return null;
    }
}