package javastory.club.stage3.step4.da.map;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step4.da.map.io.MemoryMap;
import javastory.club.stage3.step4.store.BoardStore;
import javastory.club.stage3.step4.util.MemberDuplicationException;
import javastory.club.stage3.step4.util.NoSuchBoardException;

import javax.swing.text.html.Option;

public class BoardMapStore implements BoardStore{

    private Map<String, SocialBoard> boardMap;

    public BoardMapStore() {
        this.boardMap = MemoryMap.getInstance().getBoardMap();
    }

    @Override
    public String create(SocialBoard board) {

        Optional.ofNullable(boardMap.get(board.getId()))
                .ifPresent(socialBoard -> {throw new NoSuchBoardException("해당 클럽 아이디의 게시판이 이미 존재합니다.");
                });

        boardMap.put(board.getId(),board);

        return board.getId();
    }

    @Override
    public SocialBoard retrieve(String boardId) {
        return boardMap.get(boardId);
    }

    @Override
    public List<SocialBoard> retrieveByName(String boardId) {
        return boardMap.values().stream()
                .filter(socialBoard -> socialBoard.getId().equals(boardId))
                .collect(Collectors.toList());
    }

    @Override
    public void update(SocialBoard board) {

        boardMap.put(board.getId() ,board);

    }

    @Override
    public void delete(String boardId) {

        boardMap.remove(boardId);
    }

    @Override
    public boolean exists(String boardId) {
        return Optional.ofNullable(boardMap.get(boardId))
                .isPresent();
    }
}
