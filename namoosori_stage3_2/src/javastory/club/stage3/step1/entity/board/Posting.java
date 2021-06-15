package javastory.club.stage3.step1.entity.board;

import javastory.club.stage3.step1.entity.Entity;
import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.util.DateUtil;

import java.util.ArrayList;
import java.util.List;

public class Posting implements Entity {

    private String usid;
    private String title;
    private String writerEmail;
    private String contents;
    private String writtenDate;
    private int readCount;

    private String boardId;

    public Posting(){
        this.readCount = 0;
    }

    public Posting(SocialBoard board, String title, String writerEmail, String contents){

                this();
                this.usid = board.nextPostingId();
                this.boardId = board.getId();
                this.title = title;
                this.writerEmail = writerEmail;
                this.contents = contents;
                this.writtenDate = DateUtil.today();
    }

    public Posting(String postingId, String boardId, String title, String writerEmail, String contents){
        this.usid = postingId;
        this.boardId = boardId;
        this.title = title;
        this.writerEmail = writerEmail;
        this.contents = contents;
        this.writtenDate = DateUtil.today();

    }

    @Override
    public String toString() {
        //
        StringBuilder sb = new StringBuilder();
        sb.append("포스팅 id: " + usid);
        sb.append(", 타이틀: " + title);
        sb.append(", 작성자 이메일: " + writerEmail);
        sb.append(", read count: " + readCount);
        sb.append(", written date: " + writtenDate);
        sb.append(", contents: " + contents);
        sb.append(", 게시판 id: " + boardId);

        return sb.toString();
    }

    public static List<Posting> getSamples(SocialBoard board){

    List<Posting> postings = new ArrayList<>();

    String postingUsid = board.nextPostingId();
    CommunityMember leader = CommunityMember.getSample();
    Posting leaderPosting = new Posting(board, " 클럽 인트로", leader.getEmail(), "안녕하세요, 반가워요");
    leaderPosting.setUsid(postingUsid);
    postings.add(leaderPosting);

    return postings;

    }

    @Override
    public String getId() {
        return usid;
    }

    public String getUsid() {
        return usid;
    }

    public void setUsid(String usid) {
        this.usid = usid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getWriterEmail() {
        return writerEmail;
    }

    public void setWriterEmail(String writerEmail) {
        this.writerEmail = writerEmail;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getWrittenDate() {
        return writtenDate;
    }

    public void setWrittenDate(String writtenDate) {
        this.writtenDate = writtenDate;
    }

    public int getReadCount() {
        return readCount;
    }

    public void setReadCount(int readCount) {
        this.readCount = readCount;
    }

    public String getBoardId() {
        return boardId;
    }

    public void setBoardId(String boardId) {
        this.boardId = boardId;
    }
}
