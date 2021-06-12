package javastory.club.stage3.step1.entity.club;

import javastory.club.stage3.step1.entity.AutoIdEntity;
import javastory.club.stage3.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class TravelClub implements AutoIdEntity {

    private static final int MINIMUM_NAME_LENGTH = 3;
    private static final int MINIMUM_INTRO_LENGTH = 10;
    public static final String ID_FORMAT = "%05d";

    private String usid;
    private String name;
    private String intro;
    private String foundationDay;

    private String boardId;
    private List<ClubMembership> membershipList;

    private TravelClub() {
        this.membershipList = new ArrayList<ClubMembership>();
    }

    public TravelClub(String name, String intro) {
        this();
        this.setName(name);
        this.setIntro(intro);
        this.foundationDay = DateUtil.today();
    }

    @Override
    public String toString() {

        StringBuilder builder = new StringBuilder();

        builder.append("Travel Club Id:").append(usid);
        builder.append(", name:").append(name);
        builder.append(", intro:").append(intro);
        builder.append(", foundation day:").append(foundationDay);

        return builder.toString();

    }

    public static TravelClub getSample(boolean keyIncluded) {

        String name = "자바여행클럽";
        String intro = "자바 클럽 아일랜드 ";
        TravelClub club = new TravelClub(name, intro);

        if (keyIncluded) {
            int sequence = 21;
            club.setAutoId(String.format(ID_FORMAT, sequence));
        }

        return club;

    }

    @Override
    public String getId() {
        return usid;
    }

    @Override
    public String getIdFormat() {
        return ID_FORMAT;
    }

    @Override
    public void setAutoId(String autoId) {
        this.usid = autoId;
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

        if (name.length() < MINIMUM_NAME_LENGTH) {
            throw new IllegalArgumentException("이름은 최소 " + MINIMUM_NAME_LENGTH + " 이상이여야 합니다.");
        }

        this.name = name;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        //
        if (intro.length() < MINIMUM_INTRO_LENGTH) {
            //
            throw new IllegalArgumentException("소개는 최소 " + MINIMUM_INTRO_LENGTH + "이상이여야 합니다.");
        }
        this.intro = intro;
    }

    public String getFoundationDay() {
        return foundationDay;
    }

    public void setFoundationDay(String foundationDay) {
        this.foundationDay = foundationDay;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }

    public List<ClubMembership> getMembershipList() {
        return membershipList;
    }

    public void setMembershipList(List<ClubMembership> membershipList) {
        this.membershipList = membershipList;
    }
}
