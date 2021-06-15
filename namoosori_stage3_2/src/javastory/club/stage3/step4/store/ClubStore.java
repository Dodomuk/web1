package javastory.club.stage3.step4.store;

import javastory.club.stage3.step1.entity.club.TravelClub;

public interface ClubStore {
	//
	String create(TravelClub club);
	TravelClub retrieve(String clubId);
	TravelClub retrieveByName(String name);
	void update(TravelClub club);
	void delete(String clubId);
	
	boolean exists(String clubId);
}