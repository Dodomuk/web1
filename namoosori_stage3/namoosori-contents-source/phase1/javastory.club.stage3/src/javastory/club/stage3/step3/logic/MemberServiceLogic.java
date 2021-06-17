package javastory.club.stage3.step3.logic;

import java.util.*;
import java.util.stream.Collectors;

import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.util.InvalidEmailException;
import javastory.club.stage3.step3.logic.storage.MapStorage;
import javastory.club.stage3.step3.service.MemberService;
import javastory.club.stage3.step3.service.dto.MemberDto;
import javastory.club.stage3.step3.util.MemberDuplicationException;
import javastory.club.stage3.step3.util.NoSuchMemberException;
import javastory.club.stage3.util.StringUtil;

public class MemberServiceLogic implements MemberService {
	//
	private Map<String,CommunityMember> memberMap;

	public MemberServiceLogic() {
		//
		this.memberMap = MapStorage.getInstance().getMemberMap();
	}

	@Override
	public void register(MemberDto memberDto) throws InvalidEmailException {
		//
		String memberEmail = memberDto.getEmail();

		Optional.ofNullable(memberMap.get(memberEmail))
				.ifPresent(foundMember -> {
					throw new MemberDuplicationException("It is already exist the member email: " + foundMember.getEmail());
				});

		memberMap.put(memberEmail, memberDto.toMember());
	}

	@Override
	public MemberDto find(String memberEmail) {
		//
		return Optional.ofNullable(memberMap.get(memberEmail))
				.map(foundMember -> new MemberDto(foundMember))
				.orElseThrow(() -> new NoSuchMemberException("No such member with email: " + memberEmail));
	}

	@Override
	public List<MemberDto> findByName(String memberName) {
		//
		Collection<CommunityMember> members = memberMap.values();
		if (members.isEmpty()) {
			return new ArrayList<>();
		}
		return members.stream()
				.filter(member -> member.getName().equals(memberName))
				.map(targetMember -> new MemberDto(targetMember))
				.collect(Collectors.toList());
	}

	@Override
	public void modify(MemberDto memberDto) throws InvalidEmailException {
		//
		String memberEmail = memberDto.getEmail();

		CommunityMember targetMember = Optional.ofNullable(memberMap.get(memberEmail))
				.orElseThrow(() -> new NoSuchMemberException("No such member with email: " + memberDto.getEmail()));

		//  modify only if user inputs some value.
		if (StringUtil.isEmpty(memberDto.getName())) {
			memberDto.setName(targetMember.getName());
		}
		if (StringUtil.isEmpty(memberDto.getNickName())) {
			memberDto.setNickName(targetMember.getNickName());
		}
		if (StringUtil.isEmpty(memberDto.getPhoneNumber())) {
			memberDto.setPhoneNumber(targetMember.getPhoneNumber());
		}
		if (StringUtil.isEmpty(memberDto.getBirthDay())) {
			memberDto.setBirthDay(targetMember.getBirthDay());
		}

		memberMap.put(memberEmail, memberDto.toMember());
	}

	@Override
	public void remove(String memberEmail) {
		//
		Optional.ofNullable(memberMap.get(memberEmail))
				.orElseThrow(() -> new NoSuchMemberException("No such member with email: " + memberEmail));
		memberMap.remove(memberEmail);
	}

}
