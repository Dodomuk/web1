package javastory.club.stage3.step3.ui.console;

import javastory.club.stage3.step1.util.InvalidEmailException;
import javastory.club.stage3.step3.logic.ServiceLogicLycler;
import javastory.club.stage3.step3.service.MemberService;
import javastory.club.stage3.step3.service.dto.MemberDto;
import javastory.club.stage3.step3.service.dto.TravelClubDto;
import javastory.club.stage3.step3.util.ClubDuplicationException;
import javastory.club.stage3.step3.util.MemberDuplicationException;
import javastory.club.stage3.step3.util.NoSuchClubException;
import javastory.club.stage3.step3.util.NoSuchMemberException;
import javastory.club.stage3.util.ConsoleUtil;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

public class MemberConsole {

    private MemberService memberService;
    private ConsoleUtil consoleUtil;
    private Narrator narrator;

    public MemberConsole() {
        this.memberService = ServiceLogicLycler.shareInstance().createMemberService();
        this.narrator = new Narrator(this, TalkingAt.Left);
        this.consoleUtil = new ConsoleUtil(narrator);
    }

    public void register() {

        while (true) {
            String email = consoleUtil.getValueOf("\n 이메일을 입력해주세요(0.클럽 메뉴)");
            if (email.equals("0")) {
                return;
            }
            String name = consoleUtil.getValueOf("name");
            String phoneNumber = consoleUtil.getValueOf("phone number");
            String nickName = consoleUtil.getValueOf("nickname");
            String birthDay = consoleUtil.getValueOf("birthday(yyyy.mm.dd)");

            try {
                MemberDto newMember = new MemberDto(email, name, phoneNumber);
                newMember.setNickName(nickName);
                newMember.setBirthDay(birthDay);

                memberService.register(newMember);
                narrator.sayln("등록된 멤버 : " + newMember.toString());
            } catch (MemberDuplicationException | InvalidEmailException e) {
                narrator.sayln(e.getMessage());
            }
        }
    }

    public void find() {

        while (true) {
            String email = consoleUtil.getValueOf("\n 찾고자하는 멤버의 이메일(0.클럽 메뉴)");
            if (email.equals("0")) {
                return;
            }

            try {
                MemberDto memberFound = memberService.find(email);
                narrator.sayln("\t >>> 찾은 멤버 : " + memberFound.toString());
            } catch (NoSuchClubException e) {
                narrator.sayln(e.getMessage());
            }
        }
    }

    public MemberDto findOne() {
        MemberDto memberFound = null;
        while (true) {
            String email = consoleUtil.getValueOf("\n 찾고자 하는 멤버의 이메일(0.클럽 메뉴)");
            if (email.equals("0")) {
                return null;
            }
            try {
                memberFound = memberService.find(email);
                narrator.sayln("\t > 찾은 클럽 : " + memberFound.toString());
                break;
            } catch (NoSuchClubException e) {
                narrator.sayln(e.getMessage());
            }
            memberFound = null;
        }
        return memberFound;
    }


    public void modify() {

        MemberDto targetMember = findOne();
        if (targetMember == null) {
            return;
        }

        String newName = consoleUtil.getValueOf("\n 새 이름 (0.클럽 메뉴, Enter. no change)");
        String newPhoneNumber = consoleUtil.getValueOf("\n 새 번호 (0.클럽 메뉴, Enter. no change)");
        String newNickName = consoleUtil.getValueOf("\n 새 닉네임 (0.클럽 메뉴, Enter. no change)");
        String newBirthday = consoleUtil.getValueOf("\n 새 생일날짜 (0.클럽 메뉴, Enter. no change)");

        try {
            MemberDto newMember = new MemberDto(targetMember.getEmail(),newName,newPhoneNumber);
            newMember.setNickName(newNickName);
            newMember.setBirthDay(newBirthday);

            memberService.modify(newMember);
            narrator.sayln("수정된 멤버 : " + newMember.toString());
        } catch (NoSuchMemberException | InvalidEmailException e) {
            narrator.sayln(e.getMessage());
        }
    }

    public void remove() {

        MemberDto targetMember = findOne();

        if (targetMember == null) {
            return;
        }

        String confirmStr = consoleUtil.getValueOf("멤버를 제거하시겠습니까? (Y:yes, N:no)");
        if (confirmStr.toLowerCase().equals("y") || confirmStr.toLowerCase().equals("yes")) {
            narrator.sayln("멤버를 제거 중입니다 --> " + targetMember.getName());
            memberService.remove(targetMember.getEmail());
        } else {
            narrator.sayln("제거가 취소되었습니다. --> " + targetMember.getName());
        }
    }
}
