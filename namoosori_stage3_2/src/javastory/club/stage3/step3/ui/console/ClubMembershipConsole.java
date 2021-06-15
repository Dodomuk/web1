package javastory.club.stage3.step3.ui.console;

import javastory.club.stage3.step1.entity.club.RoleInClub;
import javastory.club.stage3.step3.logic.ServiceLogicLycler;
import javastory.club.stage3.step3.service.ClubService;
import javastory.club.stage3.step3.service.ServiceLycler;
import javastory.club.stage3.step3.service.dto.ClubMembershipDto;
import javastory.club.stage3.step3.service.dto.TravelClubDto;
import javastory.club.stage3.step3.util.MemberDuplicationException;
import javastory.club.stage3.step3.util.NoSuchClubException;
import javastory.club.stage3.step3.util.NoSuchMemberException;
import javastory.club.stage3.util.ConsoleUtil;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

import java.util.Locale;

public class ClubMembershipConsole {

    private TravelClubDto currentClub;
    private ClubService clubService;
    private ConsoleUtil consoleUtil;
    private Narrator narrator;

    public ClubMembershipConsole() {

        ServiceLycler serviceFactory = ServiceLogicLycler.shareInstance();
        this.clubService = serviceFactory.createClubService();

        this.narrator = new Narrator(this, TalkingAt.Left);
        this.consoleUtil = new ConsoleUtil(narrator);

    }

    public boolean hasCurrentClub() {
        return currentClub != null;
    }

    public String requestCurrentClubName() {

        String clubName = null;

        if (hasCurrentClub()) {
            clubName = currentClub.getName();
        }
        return clubName;
    }

    public void findClub() {

        TravelClubDto clubFound = null;
        while (true) {
            String clubName = consoleUtil.getValueOf("\n 찾을 클럽 이름(0.멤버쉽 메뉴)");
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

            clubFound = null;
        }
        this.currentClub = clubFound;
    }

    public void add() {

        if (!hasCurrentClub()) {
            narrator.sayln("지정된 클럽이 아직 없습니다. 클럽을 먼저 지정해주세요 . ");
            return;
        }

        while (true) {
            String email = consoleUtil.getValueOf("\n 추가할 멤버의 이메일");

            if (email.equals("0")) {
                return;
            }

            String memberRole = consoleUtil.getValueOf("President | Member");

            try {

                ClubMembershipDto clubMembershipDto = new ClubMembershipDto(currentClub.getUsid(), email);
                clubMembershipDto.setRole(RoleInClub.valueOf(memberRole));

                clubService.addMembership(clubMembershipDto);
                narrator.sayln(String.format("클럽에 멤버 [ %s ] 를 추가합니다 . [ %s ]", email, currentClub.getName()));

            } catch (MemberDuplicationException | NoSuchMemberException e) {
                narrator.sayln(e.getMessage());
            } catch (IllegalArgumentException e) {
                narrator.sayln("클럽에서의 롤 은 President 또는 Member 입니다.");
            }
        }
    }

    public void find() {
        if (!hasCurrentClub()) {
            narrator.sayln("지정된 클럽이 아직 없습니다. 클럽을 먼저 지정해주세요 . ");
            return;
        }

        ClubMembershipDto membershipDto = null;

        while (true) {
            String memberEmail = consoleUtil.getValueOf("\n 찾을 멤버의 이메일 ");
            if (memberEmail.equals("0")) {
                break;
            }
            try {
                membershipDto = clubService.findMembershipIn(currentClub.getUsid(), memberEmail);
                narrator.sayln("\t > 찾은 멤버십 정보 : " + membershipDto);
            } catch (NoSuchMemberException e) {
                narrator.sayln(e.getMessage());
            }
        }
    }

    public ClubMembershipDto findOne(){

        ClubMembershipDto membershipDto = null;

        while(true){

            String memberEmail = consoleUtil.getValueOf("\n 찾고자 하는 멤버의 이메일 : ");
            if(memberEmail.equals("0")){
                break;
            }
            try{

                membershipDto = clubService.findMembershipIn(currentClub.getUsid(),memberEmail );
                narrator.sayln("\t >> 멤버십 정보를 찾았습니다 : " + membershipDto);
                break;

            }catch(NoSuchMemberException e){

                 narrator.sayln(e.getMessage());

            }
        }
        return membershipDto;
    }

    public void modify(){

        if(!hasCurrentClub()){
            narrator.sayln("지정된 클럽이 아직 없습니다. 클럽을 먼저 지정해주세요 . ");
            return;
        }

        ClubMembershipDto targetMembership = findOne();
        if(targetMembership == null){
            return;
        }

        String newRole = consoleUtil.getValueOf("새로운 롤 President | Member(0.멤버십 메뉴, 엔터시 변화없음) ");

        if(newRole.equals("0")){
            return;
        }
        if(!newRole.equals("")){
            targetMembership.setRole(RoleInClub.valueOf(newRole));
        }

        String clubId = targetMembership.getClubId();
        clubService.modifyMembership(clubId, targetMembership);

        ClubMembershipDto modifiedMembership = clubService.findMembershipIn(clubId, targetMembership.getMemberEmail());
        narrator.sayln("\t > 멤버십 관련 정보를 찾았습니다 : " + modifiedMembership);

    }

    public void remove(){

        if(!hasCurrentClub()){
            narrator.sayln("지정된 클럽이 아직 없습니다. 클럽을 먼저 지정해주세요 . ");
            return;
        }

        ClubMembershipDto targetMembership = findOne();
        if(targetMembership == null){
            return;
        }

        String confirmStr = consoleUtil.getValueOf("이 멤버를 클럽에서 제거하시겠습니까??? (Y: yes N : no)");

        if(confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")){
            narrator.sayln("멤버십을 제거하는 중입니다 ..... " + targetMembership.getMemberEmail());
            clubService.removeMembership(currentClub.getUsid(), targetMembership.getMemberEmail());
        }else{
            narrator.sayln("제거가 취소되었습니다. ------>" + targetMembership.getMemberEmail());
        }

    }
}
