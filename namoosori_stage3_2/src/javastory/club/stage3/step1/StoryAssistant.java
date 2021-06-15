package javastory.club.stage3.step1;

import javastory.club.stage3.step1.entity.board.Posting;
import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step1.entity.club.ClubMembership;
import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.util.Narrator;
import javastory.club.stage3.util.TalkingAt;

import java.util.List;

public class StoryAssistant {

    private Narrator narrator;

    public StoryAssistant() {
        this.narrator = new Narrator(this, TalkingAt.Left);
    }

    public static void main(String[] args) {

        StoryAssistant assistant = new StoryAssistant();
        TravelClub club = TravelClub.getSample(true);

        assistant.showBoardExample(club);
        assistant.showMemberExample(club);
    }

    public void showBoardExample(TravelClub club){

        SocialBoard board = SocialBoard.getSample(club);
        List<Posting> postings = Posting.getSamples(board);

        narrator.sayln("//// board : " + board.toString());
        narrator.sayln("//// posting : " + postings.toString());

    }

    public void showMemberExample(TravelClub club){

        CommunityMember member = CommunityMember.getSample();
        ClubMembership clubMembership = ClubMembership.getSample(club,member);

        narrator.sayln("//// communityMember : " + member.toString());
        narrator.sayln("//// clubMemberShip : " + clubMembership.toString());

    }
}
