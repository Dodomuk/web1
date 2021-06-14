package javastory.club.stage3.step3.logic;

import javastory.club.stage3.step1.entity.board.Posting;
import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step3.service.PostingService;
import javastory.club.stage3.step3.service.dto.PostingDto;
import javastory.club.stage3.step3.util.NoSuchBoardException;
import javastory.club.stage3.step3.util.NoSuchMemberException;
import javastory.club.stage3.step3.util.NoSuchPostingException;
import javastory.club.stage3.util.StringUtil;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostingServiceLogic implements PostingService {

    private Map<String, SocialBoard> boardMap;
    private Map<String, Posting> postingMap;
    private Map<String, TravelClub> clubMap;

    @Override
    public String register(String boardId, PostingDto postingDto) {

        Optional.ofNullable(clubMap.get(boardId))
                .map(club -> club.getMembershipBy(postingDto.getWriterEmail()))
                .orElseThrow(() -> new NoSuchMemberException("해당 이메일을 가진 멤버가 클럽에 존재하지 않습니다. ---> "+ postingDto.getWriterEmail()));
          Posting newPosting = Optional.ofNullable(boardMap.get(boardId))
                  .map(foundBoard -> postingDto.toPostingIn(foundBoard))
                  .orElseThrow(() -> new NoSuchBoardException("해당 게시판이 존재하지 않습니다 ---> " + boardId));

        postingMap.put(newPosting.getId(), newPosting);
        return newPosting.getId();
    }

    @Override
    public PostingDto find(String postingId) {
        return Optional.ofNullable(postingMap.get(postingId))
                .map(foundPosting -> new PostingDto(foundPosting))
                .orElseThrow(() -> new NoSuchPostingException("해당 포스트를 찾알 수 없습니다 : " + postingId));
    }

    @Override
    public List<PostingDto> findByBoardId(String boardId) {
        SocialBoard foundBoard = boardMap.get(boardId);
        if(foundBoard == null){
            throw new NoSuchBoardException("해당 이름의 게시판이 존재하지 않습니다 ---> " + boardId);
        }

        Optional.ofNullable(boardMap.get(boardId))
                .orElseThrow(() -> new NoSuchBoardException("해당 이름의 게시판이 존재하지 않습니다 ---> " + boardId));

        return postingMap.values().stream()
                .filter(posting -> posting.getBoardId().equals(boardId))
                .map(targetPosting -> new PostingDto(targetPosting))
                .collect(Collectors.toList());
    }

    @Override
    public void modify(PostingDto postingDto) {
        String postingId = postingDto.getUsid();

        Posting targetPosting = Optional.ofNullable(postingMap.get(postingId))
                .orElseThrow(() -> new NoSuchPostingException("해당 아이디의 포스팅이 존재하지 않습니다 ---> " + postingId));

        if(StringUtil.isEmpty(postingDto.getTitle())){
            postingDto.setTitle(targetPosting.getTitle());
        }
        if(StringUtil.isEmpty(postingDto.getContents())){
            postingDto.setContents(targetPosting.getContents());
        }

        Posting newPosting = postingDto.toPostingIn(postingDto.getUsid(), targetPosting.getBoardId());

        postingMap.put(postingId,newPosting);

    }

    @Override
    public void remove(String postingId) {

        Optional.ofNullable(postingMap.get(postingId))
                .orElseThrow(() -> new NoSuchMemberException("해당 아이디의 포스팅이 존재하지 않습니다. ---> " + postingId));

        postingMap.remove(postingId);

    }

    @Override
    public void removeAllIn(String boardId) {

        postingMap.values().stream()
                .forEach(posting -> postingMap.remove(posting.getId()));

    }
}
