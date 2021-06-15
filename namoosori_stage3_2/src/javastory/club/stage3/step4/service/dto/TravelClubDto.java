package javastory.club.stage3.step4.service.dto;

import javastory.club.stage3.step1.entity.club.ClubMembership;
import javastory.club.stage3.step1.entity.club.TravelClub;

import java.util.ArrayList;
import java.util.List;

public class TravelClubDto {

    private String usid;
    private String name;
    private String intro;
    private String foundationDay;

    private List<ClubMembershipDto> membershipList;

    public TravelClubDto() {
        this.membershipList = new ArrayList<>();
    }

    public TravelClubDto(String name, String intro) {
        this.name = name;
        this.intro = intro;
    }

    public TravelClubDto(TravelClub club){
        this();
        usid = club.getUsid();
        name = club.getName();
        intro = club.getIntro();
        foundationDay = club.getFoundationDay();

        for (ClubMembership clubMembership : club.getMembershipList()) {
            membershipList.add(new ClubMembershipDto(clubMembership));
        }
        
    }

    public TravelClub toTravelClub(){
        TravelClub travelClub = new TravelClub(name, intro);
        travelClub.setUsid(usid);
        travelClub.setFoundationDay(foundationDay);

        for (ClubMembershipDto clubMembershipDto : membershipList) {
            travelClub.getMembershipList().add(clubMembershipDto.toMembership());
        }
        return travelClub;
    }

    @Override
    public String toString() {
        return "TravelClubDto{" +
                "usid='" + usid + '\'' +
                ", name='" + name + '\'' +
                ", intro='" + intro + '\'' +
                ", foundationDay='" + foundationDay + '\'' +
                ", membershipList=" + membershipList +
                '}';
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getFoundationDay() {
        return foundationDay;
    }

    public void setFoundationDay(String foundationDay) {
        this.foundationDay = foundationDay;
    }

    public List<ClubMembershipDto> getMembershipList() {
        return membershipList;
    }
}
