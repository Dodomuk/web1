package javastory.club.stage3.step3.logic;

import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step3.logic.storage.MapStorage;
import javastory.club.stage3.step3.service.BoardService;
import javastory.club.stage3.step3.service.dto.BoardDto;
import javastory.club.stage3.step3.util.BoardDuplicationException;
import javastory.club.stage3.step3.util.NoSuchBoardException;
import javastory.club.stage3.step3.util.NoSuchClubException;
import javastory.club.stage3.step3.util.NoSuchMemberException;
import javastory.club.stage3.util.StringUtil;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BoardServiceLogic implements BoardService {

    private Map<String, SocialBoard> boardMap;
    private Map<String, TravelClub> travelClubMap;

    public BoardServiceLogic() {
        this.boardMap = MapStorage.getInstance().getBoardMap();
        this.travelClubMap = MapStorage.getInstance().getClubMap();
    }


    @Override
    public String register(BoardDto boardDto) {

        String boardId = boardDto.getId();

        Optional.ofNullable(boardMap.get(boardId))
                .ifPresent((targetBoard) -> {
                    throw new BoardDuplicationException("게시판이 이미 존재합니다. -->" + targetBoard.getName());
                });

        TravelClub clubFound = Optional.ofNullable(travelClubMap.get(boardId))
                .orElseThrow(() -> new NoSuchClubException("해당 아이디의 클럽이 존재하지 않습니다." + boardId));

        Optional.ofNullable(clubFound.getMembershipBy(boardDto.getAdminEmail()))
                .orElseThrow(() -> new NoSuchMemberException("해당 이메일을 가진 멤버가 클럽에 존재하지 않습니다." + boardDto.getAdminEmail()));

        SocialBoard board = boardDto.toBoard();
        boardMap.put(boardId, board);
        return boardId;
    }

    @Override
    public BoardDto find(String boardId) {
        return Optional.ofNullable(boardMap.get(boardId))
                .map(board -> new BoardDto(board))
                .orElseThrow(() -> new NoSuchBoardException("해당 아이디의 게시판이 존재하지 않습니다. --> " + boardId));
    }

    @Override
    public List<BoardDto> findByname(String boardName) {

        Collection<SocialBoard> boards = boardMap.values();
        if (boards.isEmpty()) {
            throw new NoSuchBoardException("게시판이 존재하지 않습니다.");
        }

        List<BoardDto> boardDtos = boards.stream()
                .filter(board -> board.getName().equals(boardName))
                .map(board -> new BoardDto(board))
                .collect(Collectors.toList());

        if (boardDtos.isEmpty()) {
            throw new NoSuchBoardException("해당 이름의 게시판이 존재하지 않습니다 --> " + boardName);
        }
        return boardDtos;
    }

    @Override
    public BoardDto findByClubName(String clubName) {
        TravelClub foundClub = null;
        for (TravelClub club : travelClubMap.values()) {
            if (club.getName().equals(clubName)) {
                foundClub = club;
                break;
            }
        }
        return Optional.ofNullable(foundClub)
                .map(club -> boardMap.get(club.getId()))
                .map(board -> new BoardDto(board))
                .orElseThrow(() -> new NoSuchClubException("해당 이름의 클럽이 존재하지 않습니다 . " + clubName));
    }

    @Override
    public void modify(BoardDto board) {
        String boardId = board.getId();

        SocialBoard targetBoard = Optional.ofNullable(boardMap.get(boardId))
                .orElseThrow(() -> new NoSuchBoardException("해당 아이디의 보드가 존재하지 않습니다 ---> " + board.getId()));

        if (StringUtil.isEmpty(board.getName())) {
            board.setName(targetBoard.getName());
        }
        if (StringUtil.isEmpty(board.getAdminEmail())) {
            board.setAdminEmail(targetBoard.getAdminEmail());
        } else {
            Optional.ofNullable(travelClubMap.get(board.getClubId()))
                    .map(club -> club.getMembershipBy(board.getAdminEmail()))
                    .orElseThrow(() -> new NoSuchMemberException("클럽에 해당 이메일을 가진 멤버가 존재하지 않습니다. --->"
                            + board.getAdminEmail()));
            }
        boardMap.put(boardId, board.toBoard());
    }

    @Override
    public void remove(String boardId) {

        Optional.ofNullable(boardMap.get(boardId))
                .orElseThrow(() -> new NoSuchBoardException("해당 아이디의 게시판이 존재하지 않습니다. ---> " + boardId));
        boardMap.remove(boardId);

    }
}
