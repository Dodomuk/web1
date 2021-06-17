package javastory.club.stage3.step3.logic;

import java.util.*;
import java.util.stream.Collectors;

import javastory.club.stage3.step1.entity.AutoIdEntity;
import javastory.club.stage3.step1.entity.club.ClubMembership;
import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.entity.club.RoleInClub;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step3.logic.storage.MapStorage;
import javastory.club.stage3.step3.service.ClubService;
import javastory.club.stage3.step3.service.dto.ClubMembershipDto;
import javastory.club.stage3.step3.service.dto.TravelClubDto;
import javastory.club.stage3.step3.util.ClubDuplicationException;
import javastory.club.stage3.step3.util.MemberDuplicationException;
import javastory.club.stage3.step3.util.NoSuchClubException;
import javastory.club.stage3.step3.util.NoSuchMemberException;
import javastory.club.stage3.util.StringUtil;

public class ClubServiceLogic implements ClubService {
	//
	private Map<String,CommunityMember> memberMap;
	private Map<String,TravelClub> clubMap;
	private Map<String,Integer> autoIdMap;

	public ClubServiceLogic() {
		//
		this.memberMap = MapStorage.getInstance().getMemberMap();
		this.clubMap = MapStorage.getInstance().getClubMap();
		this.autoIdMap = MapStorage.getInstance().getAutoIdMap();
	}

	@Override
	public void registerClub(TravelClubDto clubDto) {
		//
		Optional.ofNullable(retrieveByName(clubDto.getName()))
				.ifPresent((club) -> {
					throw new ClubDuplicationException("Club already exists with name:" + club.getName());
				});

		TravelClub club = clubDto.toTravelClub();

		if (club instanceof AutoIdEntity) {
			String className = TravelClub.class.getSimpleName();
			if (autoIdMap.get(className) == null) {
				autoIdMap.put(className, 1);
			}

			int keySequence = autoIdMap.get(className);
			String autoId = String.format("%05d", keySequence);
			club.setAutoId(autoId);
			autoIdMap.put(className, ++keySequence);
		}

		clubMap.put(club.getId(),  club);

		clubDto.setUsid(club.getId());
	}

	@Override
	public TravelClubDto findClub(String clubId) {
		//

		return Optional.ofNullable(clubMap.get(clubId))
				.map(foundClub -> new TravelClubDto(foundClub))
				.orElseThrow(() -> new NoSuchClubException("No such club with id: " + clubId));
	}

	@Override
	public TravelClubDto findClubByName(String name) {
		//
		return Optional.ofNullable(retrieveByName(name))
				.map(foundClub -> new TravelClubDto(foundClub))
				.orElseThrow(() -> new NoSuchClubException("No such club with name: " + name));
	}

	@Override
	public void modify(TravelClubDto clubDto) {
		//
		String clubId = clubDto.getUsid();

		TravelClub targetClub = Optional.ofNullable(clubMap.get(clubId))
				.orElseThrow(() -> new NoSuchClubException("No such club with id: " + clubDto.getUsid()));

		if (StringUtil.isEmpty(clubDto.getName())){
			clubDto.setName(targetClub.getName());
		}
		if (StringUtil.isEmpty(clubDto.getIntro())){
			clubDto.setIntro(targetClub.getIntro());
		}


		clubMap.put(clubId, clubDto.toTravelClub());
	}

	@Override
	public void remove(String clubId) {
		//
		Optional.ofNullable(clubMap.get(clubId))
				.orElseThrow(() -> new NoSuchClubException("No such club with id: " + clubId));
		clubMap.remove(clubId);
	}

	// Membership
	@Override
	public void addMembership(ClubMembershipDto membershipDto) {
		//
		// check if member exists
		String memberId = membershipDto.getMemberEmail();

		CommunityMember foundMember = Optional.ofNullable(memberMap.get(memberId))
				.orElseThrow(() -> new NoSuchClubException("No such member with email: " + memberId));

		// check if membership exists in the club
		String clubId = membershipDto.getClubId();
		TravelClub foundClub = clubMap.get(clubId);
		for (ClubMembership membership : foundClub.getMembershipList()) {
			if (memberId.equals(membership.getMemberEmail())) {
				throw new MemberDuplicationException("Member already exists in the club -->" + memberId);
			}
		}

		// add membership
		ClubMembership clubMembership = membershipDto.toMembership();
		foundClub.getMembershipList().add(clubMembership);
		clubMap.put(clubId, foundClub);
		foundMember.getMembershipList().add(clubMembership);
		memberMap.put(memberId, foundMember);
	}

	@Override
	public ClubMembershipDto findMembershipIn(String clubId, String memberId) {
		//
		TravelClub foundClub = clubMap.get(clubId);

		ClubMembership membership = getMembershipOfClub(foundClub, memberId);

		return new ClubMembershipDto(membership);
	}

	@Override
	public List<ClubMembershipDto> findAllMembershipsIn(String clubId) {
		//

		return clubMap.get(clubId).getMembershipList()
				.stream()
				.map(membership -> new ClubMembershipDto(membership))
				.collect(Collectors.toList());
	}

	@Override
	public void modifyMembership(String clubId, ClubMembershipDto membershipDto) {
		//
		String targetEmail = membershipDto.getMemberEmail();
		RoleInClub newRole = membershipDto.getRole();

		// modify membership of the club.
		TravelClub targetClub = clubMap.get(clubId);
		ClubMembership membershipOfClub = getMembershipOfClub(targetClub, targetEmail);
		membershipOfClub.setRole(newRole);

		// modify membership of the member.
		memberMap.get(targetEmail).getMembershipList()
				.stream()
				.filter(membershipOfMember -> membershipOfMember.getClubId().equals(clubId))
				.forEach(membershipOfMember->membershipOfMember.setRole(newRole));
	}

	@Override
	public void removeMembership(String clubId, String memberId) {
		//
		TravelClub foundClub = clubMap.get(clubId);
		CommunityMember foundMember = memberMap.get(memberId);

		ClubMembership clubMembership = getMembershipOfClub(foundClub, memberId);

		foundClub.getMembershipList().remove(clubMembership);
		foundMember.getMembershipList().remove(clubMembership);

		clubMap.put(clubId, foundClub);
		memberMap.put(foundMember.getId(),foundMember);
	}

	// Private
	private TravelClub retrieveByName(String name) {
		//
		Collection<TravelClub> clubs = clubMap.values();
		if (clubs.isEmpty()) {
			return null;
		}

		TravelClub foundClub = null;
		for (TravelClub club : clubs) {
			if (club.getName().equals(name)) {
				foundClub = club;
				break;
			}
		}

		return foundClub;
	}

	private ClubMembership getMembershipOfClub(TravelClub club, String memberId) {
		//
		for (ClubMembership membership : club.getMembershipList()) {
			if (memberId.equals(membership.getMemberEmail())) {
				return membership;
			}
		}

		throw new NoSuchMemberException(String.format("No such member[email:%s] in club[name:%s]", memberId, club.getName()));
	}

}
