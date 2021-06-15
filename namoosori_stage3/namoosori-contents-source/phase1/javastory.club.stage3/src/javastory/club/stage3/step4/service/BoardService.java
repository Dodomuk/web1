package javastory.club.stage3.step4.service;

import javastory.club.stage3.step4.service.dto.BoardDto;

import java.util.List;

public interface BoardService {

	String register(BoardDto boardDto);
	BoardDto find(String boardId);
	List<BoardDto> findByName(String boardName);
	BoardDto findByClubName(String clubName);
	void modify(BoardDto boardDto);
	void remove(String boardId);
}
