package javastory.club.stage3.step3.logic;

import javastory.club.stage3.step1.entity.AutoIdEntity;
import javastory.club.stage3.step1.entity.club.ClubMembership;
import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.entity.club.RoleInClub;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step3.logic.storage.MapStorage;
import javastory.club.stage3.step3.service.ClubService;
import javastory.club.stage3.step3.service.dto.ClubMembershipDto;
import javastory.club.stage3.step3.service.dto.TravelClubDto;
import javastory.club.stage3.step3.util.ClubDuplicationException;
import javastory.club.stage3.step3.util.MemberDuplicationException;
import javastory.club.stage3.step3.util.NoSuchClubException;
import javastory.club.stage3.step3.util.NoSuchMemberException;
import javastory.club.stage3.util.StringUtil;

import javax.swing.text.html.Option;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class ClubServiceLogic implements ClubService {

    private Map<String, CommunityMember> memberMap;
    private Map<String, TravelClub> clubMap;
    private Map<String, Integer> autoIdMap;

    public ClubServiceLogic(){
        this.memberMap = MapStorage.getInstance().getMemberMap();
        this.clubMap = MapStorage.getInstance().getClubMap();
        this.autoIdMap = MapStorage.getInstance().getAutoIdMap();
    }

    @Override
    public void registerClub(TravelClubDto clubDto) {

        Optional.ofNullable(retrieveByName(clubDto.getName()))
                .ifPresent((club) -> {
                    throw new ClubDuplicationException("같은 이름의 클럽이 존재합니다." + club.getName());
                });

        TravelClub club = clubDto.toTravelClub();

        if (club instanceof AutoIdEntity) {
            String className = TravelClub.class.getSimpleName();

            if (autoIdMap.get(className) == null) {
                autoIdMap.put(className, 1);
            }

            int keySequence = autoIdMap.get(className);
            String autoId = String.format("%05d", keySequence);
            club.setAutoId(autoId);
            autoIdMap.put(className, ++keySequence);
        }
        clubMap.put(club.getId(), club);

        clubDto.setUsid(club.getId());
    }

    @Override
    public TravelClubDto findClub(String clubId) {

        return Optional.ofNullable(clubMap.get(clubId))
                .map(foundClub -> new TravelClubDto(foundClub))
                .orElseThrow(() -> new NoSuchClubException("클럽이 존재하지 않습니다." + clubId));

    }

    @Override
    public TravelClubDto findClubByName(String name) {

        return Optional.ofNullable(retrieveByName(name))
                .map(foundClub -> new TravelClubDto(foundClub))
                .orElseThrow(() -> new NoSuchClubException("클럽이 존재하지 않습니다 : " + name));
    }

    @Override
    public void modify(TravelClubDto clubDto) {

        String clubid = clubDto.getUsid();

        TravelClub targetClub = Optional.ofNullable(clubMap.get(clubid))
                .orElseThrow(() -> new NoSuchClubException("클럽이 존재하지 않습니다 : " + clubDto.getUsid()));

        if (StringUtil.isEmpty(clubDto.getName())) {
            clubDto.setName(targetClub.getName());
        }
        if (StringUtil.isEmpty(clubDto.getIntro())) {
            clubDto.setIntro(targetClub.getIntro());
        }

        clubMap.put(clubid, clubDto.toTravelClub());

    }

    @Override
    public void remove(String clubId) {
        Optional.ofNullable(clubMap.get(clubId))
                .orElseThrow(() -> new NoSuchClubException("해당 아이디 관련 클럽이 존재하지 않습니다 : " + clubId));
        clubMap.remove(clubId);
    }

    @Override
    public void addMembership(ClubMembershipDto membershipDto) {

        String memberId = membershipDto.getMemberEmail();

        CommunityMember foundMember = Optional.ofNullable(memberMap.get(memberId))
                .orElseThrow(() -> new NoSuchMemberException("해당 이메일 관련 멤버가 존재하지 않습니다 : " + memberId));

        String clubId = membershipDto.getClubId();

        TravelClub foundClub = clubMap.get(clubId);

        for (ClubMembership membership : foundClub.getMembershipList()) {
            if (memberId.equals(membership.getMemberEmail())) {
                throw new MemberDuplicationException("클럽에 멤버가 이미 존재합니다 . " + memberId);
            }
        }

        ClubMembership clubMembership = membershipDto.toMembership();
        foundClub.getMembershipList().add(clubMembership);
        clubMap.put(clubId, foundClub);
        foundMember.getMembershipList().add(clubMembership);
        memberMap.put(memberId, foundMember);

    }

    @Override
    public ClubMembershipDto findMembershipIn(String clubId, String memberId) {

        TravelClub foundClub = clubMap.get(clubId);

        ClubMembership membership = getMembershipOfClub(foundClub, memberId);

        return new ClubMembershipDto(membership);

    }

    @Override
    public List<ClubMembershipDto> findAllMembershipsIn(String clubId) {
        return clubMap.get(clubId).getMembershipList()
                .stream()
                .map(membership -> new ClubMembershipDto(membership))
                .collect(Collectors.toList());
    }

    @Override
    public void modifyMembership(String clubId, ClubMembershipDto membershipDto) {

        String targetEmail = membershipDto.getMemberEmail();
        RoleInClub newRole = membershipDto.getRole();
        TravelClub targetClub = clubMap.get(clubId);

        ClubMembership membershipOfClub = getMembershipOfClub(targetClub, targetEmail);
        membershipOfClub.setRole(newRole);

        memberMap.get(targetEmail).getMembershipList()
                .stream()
                .filter(membershipOfMember -> membershipOfMember.getClubId().equals(clubId))
                .forEach(membershipOfMember -> membershipOfMember.setRole(newRole));
    }

    @Override
    public void removeMembership(String clubId, String memberId) {

        TravelClub foundClub = clubMap.get(clubId);
        CommunityMember foundMember = memberMap.get(memberId);

        ClubMembership clubMembership = getMembershipOfClub(foundClub, memberId);

        foundClub.getMembershipList().remove(clubMembership);
        foundMember.getMembershipList().remove(clubMembership);

        clubMap.put(clubId, foundClub);
        memberMap.put(foundMember.getId(), foundMember);

    }

    private TravelClub retrieveByName(String name) {

        Collection<TravelClub> clubs = clubMap.values();
        if (clubs.isEmpty()) {
            return null;
        }
        TravelClub foundClub = null;
        for (TravelClub club : clubs) {
            if (club.getName().equals(name)) {
                foundClub = club;
                break;
            }
        }

        return foundClub;
    }

    private ClubMembership getMembershipOfClub(TravelClub club, String memberId) {

        for (ClubMembership clubMembership : club.getMembershipList()) {
            if (memberId.equals(clubMembership.getMemberEmail())) {
                return clubMembership;
            }
        }

        throw new NoSuchMemberException(String.format("이메일 [%s] 이(가) 클럽 [%s] 에 존재하지 않습니다.", memberId, club.getName()));

    }
}
