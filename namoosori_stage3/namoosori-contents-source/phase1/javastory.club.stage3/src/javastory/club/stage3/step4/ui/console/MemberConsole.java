package javastory.club.stage3.step4.ui.console;


import javastory.club.stage3.step1.util.InvalidEmailException;
import javastory.club.stage3.step4.logic.ServiceLogicLycler;
import javastory.club.stage3.step4.service.MemberService;
import javastory.club.stage3.step4.service.dto.MemberDto;
import javastory.club.stage3.step4.util.MemberDuplicationException;
import javastory.club.stage3.step4.util.NoSuchMemberException;
import javastory.club.stage3.util.ConsoleUtil;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

public class MemberConsole {

    private MemberService memberService;
    private ConsoleUtil consoleUtil;
    private Narrator narrator;

    public MemberConsole() {

        this.memberService = ServiceLogicLycler.getInstance().createMemberService();
        this.narrator = new Narrator(this, TalkingAt.Left);
        this.consoleUtil = new ConsoleUtil(narrator);

    }

    public void register() {
        while (true) {

            String email = consoleUtil.getValueOf("\n new member's email(0.Member menu)");
            if (checker(email)) {
                return;
            }
            String name = consoleUtil.getValueOf(" name");
            if (checker(name)) {
                return;
            }
            String phoneNumber = consoleUtil.getValueOf(" phone number");
            if (checker(phoneNumber)) {
                return;
            }
            String nickName = consoleUtil.getValueOf(" nickname");
            if (checker(nickName)) {
                return;
            }
            String birthDay = consoleUtil.getValueOf(" birthday(yyyy.mm.dd)");
            if (checker(birthDay)) {
                return;
            }

            try {
                MemberDto newMember = new MemberDto(email, name, phoneNumber);
                newMember.setNickName(nickName);
                newMember.setBirthDay(birthDay);

                memberService.register(newMember);
                narrator.sayln("새로운 멤버가 등록되었습니다 : " + newMember.toString());
            } catch (MemberDuplicationException | InvalidEmailException e) {
                narrator.sayln(e.getMessage());
            }
        }
    }

    public void find() {
        while (true) {
            String email = consoleUtil.getValueOf("\n Member Email to find(0.Member menu)");

            if (checker(email)) {
                return;
            }
            try {
                MemberDto memberFound = memberService.find(email);
                narrator.sayln("찾은 멤버 : " + memberFound.toString());
            } catch (NoSuchMemberException e) {
                narrator.sayln(e.getMessage());
            }
        }
    }

    public MemberDto findOne() {
        MemberDto memberFound = null;

        while (true) {
            String email = consoleUtil.getValueOf("\n member's email to find(0.Member menu)");
            if (checker(email)) {
                return null;
            }
            try {
                memberFound = memberService.find(email);
                narrator.sayln("찾은 멤버 : " + memberFound.toString());
                break;
            } catch (NoSuchMemberException | InvalidEmailException e) {
                narrator.sayln(e.getMessage());
            }
        }
        return memberFound;
    }

    public void findByName() {
        while (true) {
            String name = consoleUtil.getValueOf("\n member's name to find(0.Member menu)");
            if (checker(name)) {
                return;
            }
            try {
                narrator.sayln("멤버의 리스트 :");
                memberService.findByName(name).stream().forEach(memberDto -> narrator.sayln(memberDto.toString()));
            } catch (NoSuchMemberException e) {
                narrator.sayln(e.getMessage());
            }
        }
    }

    public void modify() {
        MemberDto targetMember = findOne();
        if (targetMember == null) {
            return;
        }

        String newName = consoleUtil.getValueOf(" new name(Enter. no change)");
        targetMember.setName(newName);
        String newPhoneNumber = consoleUtil.getValueOf(" new phone number(Enter. no change)");
        targetMember.setPhoneNumber(newPhoneNumber);
        String newNickName = consoleUtil.getValueOf(" new nickname(Enter. no change)");
        targetMember.setNickName(newNickName);
        String newBirthDay = consoleUtil.getValueOf(" new birthday(yyyy.mm.dd)(Enter. no change)");
        targetMember.setBirthDay(newBirthDay);

        try {
            memberService.modify(targetMember);
            narrator.sayln("수정된 멤버 : " + targetMember.toString());
        } catch (NoSuchMemberException | InvalidEmailException e) {
            narrator.sayln(e.getMessage());
        }
    }

    public void remove() {
        MemberDto target = findOne();

        String confirm = consoleUtil.getValueOf("지우시겠습니까? (y:yes / n:no)");

        if (confirm.toLowerCase().equals("y") || confirm.toLowerCase().equals("yes")) {
            memberService.remove(target.getEmail());
        } else {
            narrator.sayln("삭제가 취소되었습니다... ");
        }

    }

    private boolean checker(String input) {
        if (input.equals("0")) {
            return true;
        }
        return false;
    }
}