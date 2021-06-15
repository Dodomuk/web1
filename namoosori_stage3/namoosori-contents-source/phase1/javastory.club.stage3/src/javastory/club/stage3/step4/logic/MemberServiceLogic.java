package javastory.club.stage3.step4.logic;

import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.util.InvalidEmailException;
import javastory.club.stage3.step4.da.map.ClubStoreMapLycler;
import javastory.club.stage3.step4.service.MemberService;
import javastory.club.stage3.step4.service.dto.MemberDto;
import javastory.club.stage3.step4.store.MemberStore;
import javastory.club.stage3.step4.util.MemberDuplicationException;
import javastory.club.stage3.step4.util.NoSuchMemberException;
import javastory.club.stage3.util.StringUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class MemberServiceLogic implements MemberService {

    private MemberStore memberStore;

    public MemberServiceLogic() {
        memberStore = ClubStoreMapLycler.getInstance().requestMemberStore();
    }

    @Override
    public void register(MemberDto memberDto) throws InvalidEmailException {
        String email = memberDto.getEmail();
        Optional.ofNullable(memberStore.retrieve(email))
                .ifPresent(communityMember -> {throw new MemberDuplicationException("해당 이메일을 가진 멤버가 이미 존재합니다 . ");
                });
        memberStore.create(memberDto.toMember());
    }

    @Override
    public MemberDto find(String memberId) {

        return Optional.ofNullable(memberStore.retrieve(memberId))
                .map(communityMember -> new MemberDto(communityMember))
                .orElseThrow(() -> new NoSuchMemberException("해당 이메일을 가진 멤버는 존재하지 않습니다 . "));
    }

    @Override
    public List<MemberDto> findByName(String memberName) {

        List<CommunityMember> communityMembers = memberStore.retrieveByName(memberName);

        if(communityMembers.isEmpty()){
            throw new NoSuchMemberException("해당 이름을 가진 멤버가 존재하지 않습니다 . ");
        }

        return communityMembers.stream()
                .map(communityMember -> new MemberDto(communityMember))
                .collect(Collectors.toList());

    }

    @Override
    public void modify(MemberDto memberDto) throws InvalidEmailException {

        CommunityMember targetMember = Optional.ofNullable(memberStore.retrieve(memberDto.getEmail()))
                .orElseThrow(() -> new NoSuchMemberException("해당 이메일을 가진 멤버가 존재하지 않습니다 : " + memberDto.getEmail()));

        if(StringUtil.isEmpty(memberDto.getName())){
             memberDto.setName(targetMember.getName());
        }
        if(StringUtil.isEmpty(memberDto.getNickName())){
            memberDto.setName(targetMember.getNickName());
        }
        if(StringUtil.isEmpty(memberDto.getBirthDay())){
            memberDto.setBirthDay(targetMember.getBirthDay());
        }
        if(StringUtil.isEmpty(memberDto.getPhoneNumber())){
            memberDto.setPhoneNumber(targetMember.getPhoneNumber());
        }

        memberStore.update(memberDto.toMember());

    }

    @Override
    public void remove(String memberId) {

        Optional.ofNullable(memberStore.exists(memberId))
                .orElseThrow(() -> new NoSuchMemberException("해당 아이디를 가진 멤버가 존재하지 않습니다."));

        memberStore.delete(memberId);

    }
}
