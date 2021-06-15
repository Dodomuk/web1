package javastory.club.stage3.step4.da.map;

import javastory.club.stage3.step1.entity.board.Posting;
import javastory.club.stage3.step3.util.MemberDuplicationException;
import javastory.club.stage3.step4.da.map.io.MemoryMap;
import javastory.club.stage3.step4.store.PostingStore;
import javastory.club.stage3.step4.util.PostingDuplicationException;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PostingMapStore implements PostingStore {

    Map<String, Posting> postingMap;

    public PostingMapStore() {
        this.postingMap = MemoryMap.getInstance().getPostingMap();
    }

    @Override
    public String create(Posting posting) {
        Optional.ofNullable(postingMap.get(posting.getId()))
                .ifPresent(targetPosting -> {
                    throw new PostingDuplicationException("이미 존재합니다 : " + targetPosting);
                });
        postingMap.put(posting.getId(), posting);

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
    public void delete(String postingId) {
        postingMap.remove(postingId);
    }

    @Override
    public boolean exists(String postingId) {
        return Optional.ofNullable(postingMap.get(postingId))
                .isPresent();
    }
}
