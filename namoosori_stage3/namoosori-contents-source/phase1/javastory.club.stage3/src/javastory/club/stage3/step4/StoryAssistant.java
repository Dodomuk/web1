package javastory.club.stage3.step4;

import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step4.da.map.ClubStoreMapLycler;
import javastory.club.stage3.step4.da.map.io.MemoryMap;
import javastory.club.stage3.step4.logic.ServiceLogicLycler;
import javastory.club.stage3.step4.service.ClubService;
import javastory.club.stage3.step4.service.MemberService;
import javastory.club.stage3.step4.service.ServiceLycler;
import javastory.club.stage3.step4.service.dto.ClubMembershipDto;
import javastory.club.stage3.step4.service.dto.MemberDto;
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.ui.menu.MainMenu;

public class StoryAssistant {
	//
	private void startStory() {
		//
		MainMenu mainMenu = new MainMenu();
		mainMenu.show();
	}

	public static void main(String[] args) {
		StoryAssistant storyAssistant = new StoryAssistant();
		storyAssistant.startStory();
	}
}
