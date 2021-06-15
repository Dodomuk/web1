package javastory.club.stage3.step3.service;

import javastory.club.stage3.step3.service.dto.BoardDto;

import java.util.List;

public interface BoardService {

    String register(BoardDto boardDto);
    BoardDto find(String boardId);
    List<BoardDto> findByname(String boardName);
    BoardDto findByClubName(String clubName);
    void modify(BoardDto board);
    void remove(String boardId);
}
