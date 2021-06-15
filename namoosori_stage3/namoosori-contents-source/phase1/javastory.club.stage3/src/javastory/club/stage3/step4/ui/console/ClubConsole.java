package javastory.club.stage3.step4.ui.console;

import javastory.club.stage3.step4.logic.ServiceLogicLycler;
import javastory.club.stage3.step4.service.ClubService;
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.util.ClubDuplicationException;
import javastory.club.stage3.step4.util.NoSuchClubException;
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

    public void register() {

        while (true) {
            String clubName = consoleUtil.getValueOf("\n 클럽 이름(0.클럽 메뉴)");
            if (clubName.equals("0")) {
                return;
            }
            String intro = consoleUtil.getValueOf("\n 소개 입력(0.클럽 메뉴)");
            if (intro.equals("0")) {
                return;
            }
            try {
                TravelClubDto clubDto = new TravelClubDto(clubName, intro);
                clubService.register(clubDto);
                narrator.say("등록된 클럽 : " + clubDto.toString());
            } catch (IllegalArgumentException | ClubDuplicationException e) {
                narrator.sayln(e.getMessage());
            }
        }
    }

    public TravelClubDto find() {

        TravelClubDto clubFound = null;

        while (true) {
            String clubName = consoleUtil.getValueOf("\n 찾고자하는 클럽의 이름(0.클럽 메뉴)");
            if (clubName.equals("0")) {
                break;
            }

            try {
                clubFound = clubService.findClubByName(clubName);
                narrator.sayln("\t >>> 찾은 클럽 : " + clubFound);
            } catch (NoSuchClubException e) {
                narrator.sayln(e.getMessage());
            }
        }
        return clubFound;
    }

    public TravelClubDto findOne() {
        TravelClubDto clubFound = null;
        while (true) {
            String clubName = consoleUtil.getValueOf("\n 찾고자 하는 클럽의 이름(0.클럽 메뉴)");
            if (clubName.equals("0")) {
                break;
            }
            try {
                clubFound = clubService.findClubByName(clubName);
                narrator.sayln("\t > 찾은 클럽 : " + clubFound);
                break;
            } catch (NoSuchClubException e) {
                narrator.sayln(e.getMessage());
            }
        }
        return clubFound;
    }

    public void modify() {
        //
        TravelClubDto targetClub = findOne();
        if (targetClub == null) {
            return;
        }

        String newName = consoleUtil.getValueOf("\n 새 클럽 이름 (0.클럽 메뉴, Enter. no change)");
        if (newName.equals("0")) {
            return;
        }
        if (!newName.isEmpty()) {
            targetClub.setName(newName);
        }

        String newIntro = consoleUtil.getValueOf(" 새 클럽 소개(Enter. no change)");
        if (!newIntro.isEmpty()) {
            targetClub.setIntro(newIntro);
        }

        try {
            clubService.modify(targetClub);
            narrator.sayln("\t > 찾은 클럽 : " + targetClub);
        } catch (IllegalArgumentException | NoSuchClubException e) {
            narrator.sayln(e.getMessage());
        }
    }

    public void remove() {
        //
        TravelClubDto targetClub = findOne();
        if (targetClub == null) {
            return;
        }

        String confirmStr = consoleUtil.getValueOf("클럽을 제거하시겠습니까? (Y:yes, N:no)");
        if (confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")) {
            narrator.sayln("클럽을 제거 중입니다 --> " + targetClub.getName());
            clubService.remove(targetClub.getUsid());
        } else {
            narrator.sayln("제거가 취소되었습니다. --> " + targetClub.getName());
        }
    }

}
