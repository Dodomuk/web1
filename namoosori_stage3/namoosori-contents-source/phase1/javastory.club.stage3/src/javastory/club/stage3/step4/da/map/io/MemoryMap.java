package javastory.club.stage3.step4.da.map.io;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javastory.club.stage3.step1.entity.board.Posting;
import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.entity.club.TravelClub;

public class MemoryMap {

    private static MemoryMap memoryMap;

    private Map<String, TravelClub> clubMap;
    private Map<String, CommunityMember> memberMap;
    private Map<String, SocialBoard> boardMap;
    private Map<String, Posting> postingMap;
    private List<Integer> autoIdList;

    private MemoryMap(){
        this.clubMap = new LinkedHashMap<>();
        this.memberMap = new LinkedHashMap<>();
        this.boardMap = new LinkedHashMap<>();
        this.postingMap = new LinkedHashMap<>();
        this.autoIdList = new ArrayList<>();
    }

    public static MemoryMap getInstance(){
        if(memoryMap == null){
            memoryMap = new MemoryMap();
        }
        return memoryMap;
    }

    public Map<String, TravelClub> getClubMap() {
        return clubMap;
    }

    public Map<String, CommunityMember> getMemberMap() {
        return memberMap;
    }

    public Map<String, SocialBoard> getBoardMap(){
        return boardMap;
    }

    public Map<String, Posting> getPostingMap() {
        return postingMap;
    }

    public List<Integer> getAutoIdList() {
        return autoIdList;
    }
}