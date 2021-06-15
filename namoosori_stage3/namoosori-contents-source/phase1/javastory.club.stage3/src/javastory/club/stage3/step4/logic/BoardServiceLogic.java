package javastory.club.stage3.step4.logic;

import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step4.da.map.ClubStoreMapLycler;
import javastory.club.stage3.step4.service.BoardService;
import javastory.club.stage3.step4.service.dto.BoardDto;
import javastory.club.stage3.step4.store.BoardStore;
import javastory.club.stage3.step4.store.ClubStore;
import javastory.club.stage3.step4.util.BoardDuplicationException;
import javastory.club.stage3.step4.util.NoSuchBoardException;
import javastory.club.stage3.step4.util.NoSuchClubException;
import javastory.club.stage3.step4.util.NoSuchMemberException;
import javastory.club.stage3.util.StringUtil;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class BoardServiceLogic implements BoardService {

    private BoardStore boardStore;
    private ClubStore clubStore;

    public BoardServiceLogic() {
        this.boardStore = ClubStoreMapLycler.getInstance().requestBoardStore();
        this.clubStore = ClubStoreMapLycler.getInstance().requestClubStore();
    }

    @Override
    public String register(BoardDto boardDto) {

        String boardId = boardDto.getClubId();

        Optional.ofNullable(boardStore.retrieve(boardId))
                .ifPresent(boardFound -> {
                    throw new BoardDuplicationException("클럽에 게시판이 이미 존재합니다 . " + boardFound.getName());
                });

        TravelClub clubFound = Optional.ofNullable(clubStore.retrieve(boardId)).orElseThrow(() -> {
            throw new NoSuchClubException("해당 클럽이 존재하지 않습니다 ." + boardId);
        });

        return Optional.ofNullable(clubFound.getMembershipBy(boardDto.getAdminEmail()))
                .map(adminEmail -> boardStore.create(boardDto.toBoard()))
                .orElseThrow(() -> {
                    throw new NoSuchMemberException("클럽에 해당 이메일을 가진 멤버가 존재하지 않습니다 ---> " + boardDto.getAdminEmail());
                });
    }

    @Override
    public BoardDto find(String boardId) {
        return Optional.ofNullable(boardStore.retrieve(boardId))
                .map(socialBoard -> new BoardDto(socialBoard))
                .orElseThrow(() -> {
                    throw new NoSuchBoardException("해당 이름을 가진 게시판이 존재하지 않습니다 ... --> " + boardId);
                });
    }

    @Override
    public List<BoardDto> findByName(String boardName) {

        List<SocialBoard> boards = boardStore.retrieveByName(boardName);

        if (boards == null || boards.isEmpty()) {
            throw new NoSuchBoardException("해당 이름의 게시판이 존재하지 않습니다.");
        }
        return boards.stream().map(socialBoard -> new BoardDto(socialBoard))
                .collect(Collectors.toList());
    }

    @Override
    public BoardDto findByClubName(String clubName) {
        return Optional.ofNullable(clubStore.retrieveByName(clubName))
                .map(travelClub -> boardStore.retrieve(travelClub.getId()))
                .map(socialBoard -> new BoardDto(socialBoard))
                .orElseThrow(() -> {
                    throw new NoSuchClubException("해당 이름의 클럽이 존재하지 않습니다. ");
                });
    }

    @Override
    public void modify(BoardDto boardDto) {

        SocialBoard targetBoard = Optional.ofNullable(boardStore.retrieve(boardDto.getClubId()))
                .orElseThrow(() -> {
                    throw new NoSuchBoardException("해당 아이디의 게시판이 존재하지 않습니다.");
                });

        
        //다시 확인해볼것... StringUtil 안씀
        if(boardDto.getName().isEmpty()){
            boardDto.setName(targetBoard.getName());
        }
        if(boardDto.getAdminEmail().isEmpty()){
            boardDto.setAdminEmail(targetBoard.getAdminEmail());
        }else{
            Optional.ofNullable(clubStore.retrieve(boardDto.getClubId()))
                    .map(travelClub -> travelClub.getMembershipBy(boardDto.getAdminEmail()))
                    .orElseThrow(() -> {throw new NoSuchMemberException("해당 이메일의 멤버가 존재하지 않습니다. ");});
        }

        boardStore.update(boardDto.toBoard());
        
    }

    @Override
    public void remove(String boardId) {

        Optional.ofNullable(!boardStore.exists(boardId))
                .orElseThrow(() -> {throw new NoSuchBoardException("게시판이 존재하지 않습니다 . ");});

               boardStore.delete(boardId);
    }
}
