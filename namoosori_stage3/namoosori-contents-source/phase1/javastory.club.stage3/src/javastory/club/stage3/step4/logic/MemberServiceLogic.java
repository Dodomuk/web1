package javastory.club.stage3.step4.logic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.util.InvalidEmailException;
import javastory.club.stage3.step4.service.MemberService;
import javastory.club.stage3.step4.service.dto.MemberDto;
import javastory.club.stage3.step4.store.MemberStore;
import javastory.club.stage3.step4.util.MemberDuplicationException;
import javastory.club.stage3.step4.util.NoSuchMemberException;
import javastory.club.stage3.util.StringUtil;
import javastory.club.stage3.step4.da.map.ClubStoreMapLycler;

public class MemberServiceLogic implements MemberService{

    private MemberStore memberStore;

    public MemberServiceLogic() {
        memberStore = ClubStoreMapLycler.getInstance().requestMemberStore();
    }


    @Override
    public void register(MemberDto memberDto) throws InvalidEmailException {
        Optional.ofNullable(memberStore.retrieve(memberDto.getEmail()))
                .ifPresent(communityMember -> {throw new MemberDuplicationException("이미 같은 이메일로 가입된 멤버가 존재합니다.");});
        memberStore.create(memberDto.toMember());
    }

    @Override
    public MemberDto find(String memberId) {
        return Optional.ofNullable(memberStore.retrieve(memberId))
                .map(communityMember -> new MemberDto(communityMember))
                .orElseThrow(() -> new NoSuchMemberException("멤버가 존재하지 않습니다."));
    }

    @Override
    public List<MemberDto> findByName(String memberName) {

       List<CommunityMember> memberList = memberStore.retrieveByName(memberName);

        Optional.ofNullable(memberList)
                .orElseThrow(() -> new NoSuchMemberException("해당 이름의 멤버가 존재하지 않습니다."));

        return memberList.stream()
                .map(member -> new MemberDto(member))
                .collect(Collectors.toList());
    }

    @Override
    public void modify(MemberDto memberDto) throws InvalidEmailException {

        CommunityMember targetMember = memberStore.retrieve(memberDto.getEmail());

        //입력 값이 없을 경우 기존 값으로 대체 ** 주의
        if (StringUtil.isEmpty(memberDto.getName())) {
            memberDto.setName(targetMember.getName());
        }
        if (StringUtil.isEmpty(memberDto.getNickName())) {
            memberDto.setNickName(targetMember.getNickName());
        }
        if (StringUtil.isEmpty(memberDto.getPhoneNumber())) {
            memberDto.setPhoneNumber(targetMember.getPhoneNumber());
        }
        if (StringUtil.isEmpty(memberDto.getBirthDay())) {
            memberDto.setBirthDay(targetMember.getBirthDay());
        }

        memberStore.update(memberDto.toMember());

    }

    @Override
    public void remove(String memberId) {

        if(!memberStore.exists(memberId)){
            throw new NoSuchMemberException("해당 이메일을 가진 멤버가 존재하지 않습니다 . ");
        }

        memberStore.delete(memberId);
    }
}