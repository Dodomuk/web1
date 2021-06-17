package javastory.club.stage3.step4.logic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javastory.club.stage3.step1.entity.club.ClubMembership;
import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.entity.club.RoleInClub;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step4.service.ClubService;
import javastory.club.stage3.step4.service.dto.ClubMembershipDto;
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.store.ClubStore;
import javastory.club.stage3.step4.store.MemberStore;
import javastory.club.stage3.step4.util.ClubDuplicationException;
import javastory.club.stage3.step4.util.MemberDuplicationException;
import javastory.club.stage3.step4.util.NoSuchClubException;
import javastory.club.stage3.step4.util.NoSuchMemberException;
import javastory.club.stage3.util.StringUtil;
import javastory.club.stage3.step4.da.map.ClubStoreMapLycler;

public class ClubServiceLogic implements ClubService{

    private ClubStore clubStore;
    private MemberStore memberStore;

    public ClubServiceLogic() {
        this.clubStore = ClubStoreMapLycler.getInstance().requestClubStore();
        this.memberStore = ClubStoreMapLycler.getInstance().requestMemberStore();
    }

    @Override
    public void register(TravelClubDto clubDto) {

        Optional.ofNullable(clubStore.retrieveByName(clubDto.getName()))
                .ifPresent(travelClub -> { throw new ClubDuplicationException("이미 존재하는 이름입니당");});

        TravelClub club = clubDto.toTravelClub();
        String clubId = clubStore.create(club);

        clubDto.setUsid(clubId);
    }

    @Override
    public TravelClubDto findClub(String clubId) {

       return Optional.ofNullable(clubStore.retrieve(clubId))
                .map(travelClub -> new TravelClubDto(travelClub))
                .orElseThrow(() -> new NoSuchClubException("해당 아이디를 가진 클럽이 존재하지 않습니다. "));

    }

    @Override
    public TravelClubDto findClubByName(String name) {
        return Optional.ofNullable(clubStore.retrieveByName(name))
                .map(travelClub -> new TravelClubDto(travelClub))
                .orElseThrow(() -> new NoSuchClubException("해당 이름을 가진 클럽이 존재하지 않습니다."));
    }

    @Override
    public void modify(TravelClubDto clubDto) {
        Optional.ofNullable(clubStore.retrieveByName(clubDto.getName()))
                .ifPresent(travelClub -> {throw new ClubDuplicationException("해당 이름의 클럽이 이미 존재합니다.");});

        TravelClub club = Optional.ofNullable(clubStore.retrieve(clubDto.getUsid()))
                .orElseThrow(() -> new NoSuchClubException("해당 아이디를 가진 클럽이 존재하지 않습니다 . "));

        if(clubDto.getName().isEmpty() || clubDto.getName() == null){
            clubDto.setName(club.getName());
        }
        if(clubDto.getIntro().isEmpty() || clubDto.getIntro() == null){
            clubDto.setIntro(club.getIntro());
        }

        clubStore.update(clubDto.toTravelClub());

    }

    @Override
    public void remove(String clubId) {

        if(!clubStore.exists(clubId)){
            throw new NoSuchClubException("해당 아이디를 가진 클럽이 존재하지 않습니다.");
        }

        clubStore.delete(clubId);

    }

    @Override
    public void addMembership(ClubMembershipDto membershipDto) {

    }

    @Override
    public ClubMembershipDto findMembershipIn(String clubId, String memberId) {
        return null;
    }

    @Override
    public List<ClubMembershipDto> findAllMembershipsIn(String clubId) {
        return null;
    }

    @Override
    public void modifyMembership(String clubId, ClubMembershipDto member) {

    }

    @Override
    public void removeMembership(String clubId, String memberId) {

    }
}