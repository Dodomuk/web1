package javastory.club.stage3.step3.service;

public interface ClubService {

    void registerClub(TravelClubDto clubDto);
    TravelClubDto findClub(String clubId);
    TravelClubDto findClubByName(String name);
    void modify(TravelClubDto clubDto);
    void remove(String clubId);

    void addMembership(ClubMembershipDto membershipDto);
    ClubMembershipDto findMembershipIn(String clubId, String memberId);
    List<ClubMembershipDto> findAllMembershipsIn(String clubId);
    void modifyMembership(String clubId, ClubMembershipDto membershipDto);
    void removeMembership(String clubId, String memberId);


}
