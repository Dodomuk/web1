package javastory.club.stage3.step3.logic;

import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.util.InvalidEmailException;
import javastory.club.stage3.step3.logic.storage.MapStorage;
import javastory.club.stage3.step3.service.MemberService;
import javastory.club.stage3.step3.service.dto.MemberDto;
import javastory.club.stage3.step3.util.MemberDuplicationException;
import javastory.club.stage3.step3.util.NoSuchMemberException;
import javastory.club.stage3.util.StringUtil;

import javax.swing.text.html.Option;
import java.util.*;
import java.util.stream.Collectors;

public class MemberServiceLogic implements MemberService {

    private Map<String, CommunityMember> memberMap;

    public MemberServiceLogic() {
        this.memberMap = MapStorage.getInstance().getMemberMap();
    }


    @Override
    public void register(MemberDto memberDto) throws InvalidEmailException {

        String memberEmail = memberDto.getEmail();

        Optional.ofNullable(memberMap.get(memberEmail))
                .ifPresent(foundMember -> {
                    throw new MemberDuplicationException("해당 이메일의 멤버가 이미 존재합니다 ..." + foundMember.getEmail());
                });

        memberMap.put(memberEmail,memberDto.toMember());
    }

    @Override
    public MemberDto find(String memberEmail) {
        return Optional.ofNullable(memberMap.get(memberEmail))
                .map(foundMember -> new MemberDto(foundMember))
                .orElseThrow(() -> new NoSuchMemberException("해당 이메일을 가진 멤버가 존재하지 않습니다 ." + memberEmail));
    }

    @Override
    public List<MemberDto> findByName(String memberName) {

        Collection<CommunityMember> members = memberMap.values();
        if(members.isEmpty()){
            return new ArrayList<>();
        }
        return members.stream()
                .filter(member -> member.getName().equals(memberName))
                .map(targetMember -> new MemberDto(targetMember))
                .collect(Collectors.toList());
    }

    @Override
    public void modify(MemberDto memberDto) throws InvalidEmailException {

        String memberEmail = memberDto.getEmail();

        CommunityMember targetMember = Optional.ofNullable(memberMap.get(memberEmail))
                .orElseThrow(() -> new NoSuchMemberException("해당 이메일을 가진 멤버가 존재하지 않습니다." + memberDto.getEmail()));

        //value가 입력 되었을때만 수정된다.
        if(StringUtil.isEmpty(memberDto.getName())){
            memberDto.setName(targetMember.getName());
        }
        if(StringUtil.isEmpty(memberDto.getNickName())){
            memberDto.setNickName(targetMember.getNickName());
        }
        if(StringUtil.isEmpty(memberDto.getPhoneNumber())){
            memberDto.setPhoneNumber(targetMember.getPhoneNumber());
        }
        if(StringUtil.isEmpty(memberDto.getBirthDay())){
            memberDto.setBirthDay(targetMember.getBirthDay());
        }
        memberMap.put(memberEmail,memberDto.toMember());

    }

    @Override
    public void remove(String memberEmail) {

        Optional.ofNullable(memberMap.get(memberEmail))
                .orElseThrow(() -> new NoSuchMemberException("해당 이메일을 가진 멤버가 존재하지 않습니다"+ memberEmail));
        memberMap.remove(memberEmail);
    }
}
