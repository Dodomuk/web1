package javastory.club.stage3.step4.logic;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javastory.club.stage3.step1.entity.board.Posting;
import javastory.club.stage3.step4.service.PostingService;
import javastory.club.stage3.step4.service.dto.PostingDto;
import javastory.club.stage3.step4.store.BoardStore;
import javastory.club.stage3.step4.store.ClubStore;
import javastory.club.stage3.step4.store.PostingStore;
import javastory.club.stage3.step4.util.NoSuchBoardException;
import javastory.club.stage3.step4.util.NoSuchMemberException;
import javastory.club.stage3.step4.da.map.ClubStoreMapLycler;
import javastory.club.stage3.step4.util.NoSuchPostingException;


public class PostingServiceLogic implements PostingService{

    private PostingStore postingStore;
    private BoardStore boardStore;
    private ClubStore clubStore;

    public PostingServiceLogic() {
        this.postingStore = ClubStoreMapLycler.getInstance().requestPostingStore();
        this.boardStore = ClubStoreMapLycler.getInstance().requestBoardStore();
        this.clubStore = ClubStoreMapLycler.getInstance().requestClubStore();
    }

    @Override
    public String register(String boardId, PostingDto postingDto) {

        Optional.ofNullable(clubStore.retrieve(boardId))
                .map(club -> club.getMembershipBy(postingDto.getWriterEmail()))
                .orElseThrow(() -> new NoSuchMemberException("해당 이메일의 멤버가 클럽에 존재하지 않습니다." + postingDto.getWriterEmail()));

        return Optional.ofNullable(boardStore.retrieve(boardId))
                .map(board -> postingStore.create(postingDto.toPostingIn(board)))
                .orElseThrow(() -> new NoSuchBoardException("게시판이 존재하지 않습니다. " + boardId));
    }

    @Override
    public PostingDto find(String postingId) {
        return Optional.ofNullable(postingStore.retrieve(postingId))
                .map(posting -> new PostingDto(posting))
                .orElseThrow(() -> new NoSuchPostingException("해당 아이디 관련 포스팅이 존재하지 않습니다 . "));
    }

    @Override
    public List<PostingDto> findByBoardId(String boardId) {

        Optional.ofNullable(boardStore.retrieve(boardId))
                .orElseThrow(() -> new NoSuchBoardException("해당 아이디 관련 게시판이 존재하지 않습니다. --->" + boardId));

        return postingStore.retrieveByBoardId(boardId).stream()
                .map(posting -> new PostingDto(posting))
                .collect(Collectors.toList());
    }

    @Override
    public void modify(PostingDto postingDto) {

        Posting target = Optional.ofNullable(postingStore.retrieve(postingDto.getUsid()))
                .orElseThrow(() -> new )

    }

    @Override
    public void remove(String postingId) {

    }

    @Override
    public void removeAllIn(String boardId) {

    }
}