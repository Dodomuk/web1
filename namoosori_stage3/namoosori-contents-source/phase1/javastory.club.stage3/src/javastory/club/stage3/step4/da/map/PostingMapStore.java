package javastory.club.stage3.step4.da.map;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import javastory.club.stage3.step1.entity.board.Posting;
import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step4.da.map.io.MemoryMap;
import javastory.club.stage3.step4.store.PostingStore;
import javastory.club.stage3.step4.util.PostingDuplicationException;

public class PostingMapStore implements PostingStore{

    private Map<String,Posting> postingMap;
    private Map<String, SocialBoard> boardMap;

    public PostingMapStore() {
        this.postingMap = MemoryMap.getInstance().getPostingMap();
        this.boardMap = MemoryMap.getInstance().getBoardMap();
    }


    @Override
    public String create(Posting posting) {
        Optional.ofNullable(postingMap.get(posting.getId()))
                .ifPresent(posting1 -> {
                    throw new PostingDuplicationException("이미 포스팅이 존재합니다. " + posting1);
                });

        postingMap.put(posting.getId(),posting);

        return posting.getId();
    }

    @Override
    public Posting retrieve(String postingId) {

        return postingMap.get(postingId);

    }

    @Override
    public List<Posting> retrieveByBoardId(String boardId) {
        return postingMap.values().stream()
                .filter(posting -> posting.getBoardId().equals(boardId))
                .collect(Collectors.toList());
    }

    @Override
    public List<Posting> retrieveByTitle(String title) {
        return postingMap.values().stream()
                .filter(posting -> posting.getTitle().equals(title))
                .collect(Collectors.toList());
    }

    @Override
    public void update(Posting posting) {

        postingMap.put(posting.getId(),posting);

    }

    @Override
    public void delete(String posingId) {

        postingMap.remove(posingId);

    }

    @Override
    public boolean exists(String postingId) {

        return Optional.ofNullable(postingMap.get(postingId))
                .isPresent();
    }
}