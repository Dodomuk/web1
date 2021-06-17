package javastory.club.stage3.step4.logic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step4.service.BoardService;
import javastory.club.stage3.step4.service.dto.BoardDto;
import javastory.club.stage3.step4.service.dto.TravelClubDto;
import javastory.club.stage3.step4.store.BoardStore;
import javastory.club.stage3.step4.store.ClubStore;
import javastory.club.stage3.step4.util.BoardDuplicationException;
import javastory.club.stage3.step4.util.NoSuchBoardException;
import javastory.club.stage3.step4.util.NoSuchClubException;
import javastory.club.stage3.step4.util.NoSuchMemberException;
import javastory.club.stage3.util.StringUtil;
import javastory.club.stage3.step4.da.map.ClubStoreMapLycler;

public class BoardServiceLogic implements BoardService {

    private BoardStore boardStore;
    private ClubStore clubStore;

    public BoardServiceLogic() {
        this.boardStore = ClubStoreMapLycler.getInstance().requestBoardStore();
        this.clubStore = ClubStoreMapLycler.getInstance().requestClubStore();
    }

    //여기도 좀 헷갈림
    @Override
    public String register(BoardDto boardDto) {

        //해당 클럽 아이디 관련 게시판 존재 유무
        Optional.ofNullable(boardStore.retrieve(boardDto.getClubId()))
                .ifPresent(socialBoard -> {
                    throw new BoardDuplicationException("이미 게시판이 존재합니다 " + socialBoard.getName());
                });

        //현재 게시판의 클럽 아이디 관련 클럽 찾기
        TravelClub club = Optional.ofNullable(clubStore.retrieve(boardDto.getClubId()))
                .orElseThrow(() -> new NoSuchClubException("해당 아이디 관련 클럽이 존재하지 않습니다" + boardDto.getClubId()));

        //멤버십에 해당 이메일이 들어가 있다면 새로운 게시판을 만든다.
        return Optional.ofNullable(club.getMembershipBy(boardDto.getAdminEmail()))
                .map(adminEmail -> boardStore.create(boardDto.toBoard()))
                .orElseThrow(() -> new NoSuchMemberException("해당 이메일의 멤버가 존재하지 않습니다." + boardDto.getAdminEmail()));
    }

    @Override
    public BoardDto find(String boardId) {
        return Optional.ofNullable(boardStore.retrieve(boardId))
                .map(board -> new BoardDto(board))
                .orElseThrow(() -> new NoSuchBoardException("해당 아이디 관련 게시판이 존재하지 않습니다."));
    }

    @Override
    public List<BoardDto> findByName(String boardName) {
        List<SocialBoard> boardList = boardStore.retrieveByName(boardName);

        if (boardList == null || boardList.isEmpty()) {
            throw new NoSuchBoardException("해당 이름 관련 게시판이 존재하지 않습니다.");
        }

        return boardList.stream()
                .map(board -> new BoardDto(board))
                .collect(Collectors.toList());
    }

    //여기부분 좀 헷갈림
    @Override
    public BoardDto findByClubName(String clubName) {
        return Optional.ofNullable(clubStore.retrieveByName(clubName))
                .map(club -> boardStore.retrieve(club.getId()))
                .map(board -> new BoardDto(board))
                .orElseThrow(() -> {
                    throw new NoSuchClubException("해당 아이디 관련 클럽이 존재하지 않습니다.");
                });
    }

    @Override
    public void modify(BoardDto boardDto) {

        SocialBoard board = boardStore.retrieve(boardDto.getClubId());

        if (StringUtil.isEmpty(boardDto.getName())) {
            boardDto.setName(board.getName());
        }
        if (StringUtil.isEmpty(boardDto.getAdminEmail())) {
            boardDto.setAdminEmail(board.getAdminEmail());
        } else {
            //여기 부분 재확인 요망
            Optional.ofNullable(clubStore.retrieve(boardDto.getClubId()))
                    .map(club -> club.getMembershipBy(board.getAdminEmail()))
                    .orElseThrow(() -> new NoSuchMemberException("In the club, No such member with admin's email -->" + boardDto.getAdminEmail()));
            //
        }
        boardStore.update(boardDto.toBoard());
    }

    @Override
    public void remove(String boardId) {
        if(!boardStore.exists(boardId)){
            throw new NoSuchBoardException("해당 아이디 관련 게시판이 존재하지 않습니다.");
        }
        boardStore.delete(boardId);
    }
}