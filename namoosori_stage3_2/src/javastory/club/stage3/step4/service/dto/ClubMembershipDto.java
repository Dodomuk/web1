package javastory.club.stage3.step4.service.dto;

import javastory.club.stage3.step1.entity.club.ClubMembership;
import javastory.club.stage3.step1.entity.club.RoleInClub;
import javastory.club.stage3.util.DateUtil;

public class ClubMembershipDto {

    private String clubId;
    private String memberEmail;
    private String memberName;
    private RoleInClub role;
    private String joinDate;

    public ClubMembershipDto(String clubId, String memberEmail, String memberName) {
        this.clubId = clubId;
        this.memberEmail = memberEmail;
        this.memberName = memberName;
        this.role = RoleInClub.Member;
        this.joinDate = DateUtil.today();
    }

    public ClubMembershipDto(ClubMembership clubMembership){
        this.clubId = clubMembership.getClubId();
        this.memberEmail = clubMembership.getMemberEmail();
        this.role = clubMembership.getRole();
        this.joinDate = clubMembership.getJoinDate();
    }

    public ClubMembership toMembership(){
        ClubMembership clubMembership = new ClubMembership(clubId, memberEmail);
        clubMembership.setRole(role);
        clubMembership.setJoinDate(joinDate);
        return clubMembership;
    }

    @Override
    public String toString() {
        return "ClubMembershipDto{" +
                "clubId='" + clubId + '\'' +
                ", memberEmail='" + memberEmail + '\'' +
                ", memberName='" + memberName + '\'' +
                ", role=" + role +
                ", joinDate='" + joinDate + '\'' +
                '}';
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

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
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
