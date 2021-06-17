package javastory.club.stage3.step4.da.map;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step4.da.map.io.MemoryMap;
import javastory.club.stage3.step4.store.MemberStore;
import javastory.club.stage3.step4.util.MemberDuplicationException;

public class MemberMapStore implements MemberStore{

    private Map<String, CommunityMember> memberMap;

    public MemberMapStore() {
        this.memberMap = MemoryMap.getInstance().getMemberMap();
    }


    @Override
    public String create(CommunityMember member) {

        Optional.ofNullable(memberMap.get(member.getId()))
                .ifPresent(communityMember -> {throw new MemberDuplicationException("이미 같은 이메일을 가진 멤버가 존재합니다.");});
        System.out.println(member.getId() + " /////" + member.getEmail());
        memberMap.put(member.getId(),member);

        return member.getId();
    }

    @Override
    public CommunityMember retrieve(String email) {

        return memberMap.get(email);
    }

    @Override
    public List<CommunityMember> retrieveByName(String name) {
        return memberMap.values().stream()
                .filter(communityMember -> communityMember.getName().equals(name))
                .collect(Collectors.toList());
    }

    @Override
    public void update(CommunityMember member) {
        memberMap.put(member.getId(),member);
    }

    @Override
    public void delete(String email) {
        memberMap.remove(email);
    }

    @Override
    public boolean exists(String email) {
        return false;
    }
}

