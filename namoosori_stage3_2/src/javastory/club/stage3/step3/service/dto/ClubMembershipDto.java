package javastory.club.stage3.step3.service.dto;

import javastory.club.stage3.step1.entity.club.ClubMembership;
import javastory.club.stage3.step1.entity.club.RoleInClub;
import javastory.club.stage3.util.DateUtil;

public class ClubMembershipDto {

    private String clubId;
    private String memberEmail;
    private RoleInClub role;
    private String joinDate;

    private ClubMembershipDto(){
        this.role = RoleInClub.Member;
        this.joinDate = DateUtil.today();
    }

    public ClubMembershipDto(String clubId, String memberEmail){

        this();
        this.clubId = clubId;
        this.memberEmail = memberEmail;
    }

    public ClubMembershipDto(TravelClubDto clubDto, MemberDto memberDto){

    }

    public ClubMembershipDto(ClubMembership membership){

        this.clubId = membership.getClubId();
        this.memberEmail = membership.getMemberEmail();
        this.role = membership.getRole();
        this.joinDate = membership.getJoinDate();
    }

    public ClubMembership toMembership(){
        ClubMembership membership = new ClubMembership(clubId,memberEmail);
        membership.setRole(role);
        membership.setJoinDate(joinDate);
        return membership;
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();
        sb.append("club Id:").append(clubId);
        sb.append(", member email:").append(memberEmail);
        sb.append(", role:").append(role.name());
        sb.append(", join date:").append(joinDate);

        return sb.toString();
    }

    public String getClubId() {
        return clubId;
    }

    public void setClubId(String clubId) {
        this.clubId = clubId;
    }

    public String getMemberEmail() {
        return memberEmail;
    }

    public void setMemberEmail(String memberEmail) {
        this.memberEmail = memberEmail;
    }

    public RoleInClub getRole() {
        return role;
    }

    public void setRole(RoleInClub role) {
        this.role = role;
    }

    public String getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(String joinDate) {
        this.joinDate = joinDate;
    }
}
