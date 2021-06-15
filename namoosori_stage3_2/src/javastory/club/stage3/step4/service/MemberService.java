package javastory.club.stage3.step4.service;

import javastory.club.stage3.step1.util.InvalidEmailException;
import javastory.club.stage3.step4.service.dto.MemberDto;

import java.util.List;

public interface MemberService {

	void register(MemberDto memberDto) throws InvalidEmailException;
	MemberDto find(String memberId);
	List<MemberDto> findByName(String memberName);
	void modify(MemberDto memberDto) throws InvalidEmailException;
	void remove(String memberId);
}
