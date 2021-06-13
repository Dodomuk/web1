package javastory.club.stage3.step3.ui.console;

import javastory.club.stage3.util.ConsoleUtil;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

public class ClubConsole {

    private ClubService clubService;
    private ConsoleUtil consoleUtil;
    private Narrator narrator;

    public ClubConsole() {
        this.clubService = ServiceLogicLycler.shareInstance().createClubService();
        this.narrator = new Narrator(this, TalkingAt.Left);
        this.consoleUtil = new ConsoleUtil(narrator);
    }

    public void register(){

        while(true){
            String clubName = consoleUtil.getValueOf("\n 클럽 이름(0.클럽 메뉴)");
            if(clubName.equals("0")){
                return;
            }
            String intro = consoleUtil.getValueOf("\n 소개 입력(0.클럽 메뉴)");
            if(intro.equals("0")){
                return ;
            }

            TravelClubDto clubDto = new TravelClubDto(clubName, intro);
            clubService.registerClub(clubDto);
            narrator.say("등록된 클럽 : " + clubDto.toString());

        }

    }
}
