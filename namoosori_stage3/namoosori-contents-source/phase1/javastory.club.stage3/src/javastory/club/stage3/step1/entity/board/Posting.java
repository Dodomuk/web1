package javastory.club.stage3.step1.entity.board;

import javastory.club.stage3.util.DateUtil;

public class Posting {

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
        this.usid = board.a;
                this.boardId = board.getId();
                this.title = title;
                this.writerEmail = writerEmail;
                this.contents = contents;
                this.writtenDate = DateUtil.today();
    }


}
