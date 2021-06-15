package javastory.club.stage3.step4.logic;

import javastory.club.stage3.step1.entity.board.Posting;
import javastory.club.stage3.step4.da.map.ClubStoreMapLycler;
import javastory.club.stage3.step4.service.PostingService;
import javastory.club.stage3.step4.service.dto.PostingDto;
import javastory.club.stage3.step4.store.BoardStore;
import javastory.club.stage3.step4.store.ClubStore;
import javastory.club.stage3.step4.store.PostingStore;
import javastory.club.stage3.step4.util.NoSuchBoardException;
import javastory.club.stage3.step4.util.NoSuchMemberException;
import javastory.club.stage3.step4.util.NoSuchPostingException;
import javastory.club.stage3.util.StringUtil;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostingServiceLogic implements PostingService {

    private PostingStore postingStore;
    private ClubStore clubStore;
    private BoardStore boardStore;

    public PostingServiceLogic() {
        postingStore = ClubStoreMapLycler.getInstance().requestPostingStore();
        clubStore = ClubStoreMapLycler.getInstance().requestClubStore();
        boardStore = ClubStoreMapLycler.getInstance().requestBoardStore();
    }

    @Override
    public String register(String boardId, PostingDto postingDto) {

        Optional.ofNullable(clubStore.retrieve(boardId))
                .map(travelClub -> travelClub.getMembershipBy(postingDto.getWriterEmail()))
                .orElseThrow(() -> new NoSuchMemberException("클럽에 해당 이메일을 가진 멤버가 존재하지 않습니다 ----> " + postingDto.getWriterEmail()));

        return Optional.ofNullable(boardStore.retrieve(boardId))
                .map(socialBoard -> postingStore.create(postingDto.toPostingIn(socialBoard)))
                .orElseThrow(() -> new NoSuchBoardException("해당 아이디를 가진 게시판이 존재하지 않습니다."+ boardId) );
    }

    @Override
    public PostingDto find(String postingId) {
        return Optional.ofNullable(postingStore.retrieve(postingId))
                .map(posting -> new PostingDto(posting))
                .orElseThrow(() -> new NoSuchPostingException("해당 아이디의 포스팅이 존재하지 않습니다."));
    }

    @Override
    public List<PostingDto> findByBoardId(String boardId) {

        Optional.ofNullable(boardStore.retrieve(boardId))
                .orElseThrow(() -> new NoSuchBoardException("해당 아이디를 가진 게시판이 존재하지 않습니다."));

        return postingStore.retrieveByBoardId(boardId).stream()
                .map(posting -> new PostingDto(posting))
                .collect(Collectors.toList());
    }

    @Override
    public void modify(PostingDto postingDto) {

        String postingId = postingDto.getUsid();

        Posting targetPosting = Optional.ofNullable(postingStore.retrieve(postingId))
                .orElseThrow(() -> new NoSuchPostingException("해당 아이디를 가진 포스팅이 존재하지 않습니다 . "));

        if(StringUtil.isEmpty(postingDto.getTitle())){
            postingDto.setTitle(targetPosting.getTitle());
        }
        if(StringUtil.isEmpty(postingDto.getContents())){
            postingDto.setContents(targetPosting.getContents());
        }

        postingStore.update(postingDto.toPostingIn(postingId,targetPosting.getBoardId()));
    }

    @Override
    public void remove(String postingId) {

        if(!postingStore.exists(postingId)){
            throw new NoSuchPostingException("해당 아이디를 가진 포스팅이 존재하지 않습니다 . ");
        }
        postingStore.delete(postingId);

    }

    @Override
    public void removeAllIn(String boardId) {

        postingStore.retrieveByBoardId(boardId).stream()
                .forEach(posting -> postingStore.delete(boardId));

    }
}
