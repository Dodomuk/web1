package javastory.club.stage3.step3.service.dto;

import javastory.club.stage3.step1.entity.club.ClubMembership;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.util.DateUtil;

import java.util.Date;
import java.util.List;

public class TravelClubDto {

    private String usid;
    private String name;
    private String intro;
    private String foundationDay;

    private List<ClubMembershipDto> membershipList;

    public TravelClubDto() {
    }

    public TravelClubDto(String name, String intro) {
        this();
        this.name = name;
        this.intro = intro;
        this.foundationDay = DateUtil.today();
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
}
