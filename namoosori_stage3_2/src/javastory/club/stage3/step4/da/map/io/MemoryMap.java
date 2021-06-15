package javastory.club.stage3.step4.da.map.io;

import javastory.club.stage3.step1.entity.board.Posting;
import javastory.club.stage3.step1.entity.board.SocialBoard;
import javastory.club.stage3.step1.entity.club.CommunityMember;
import javastory.club.stage3.step1.entity.club.TravelClub;

import java.util.LinkedHashMap;
import java.util.Map;

public class MemoryMap {

    private static MemoryMap memoryMap;

    private Map<String, CommunityMember> memberMap;
    private Map<String, TravelClub> clubMap;
    private Map<String, SocialBoard> boardMap;
    private Map<String, Posting> postingMap;
    private Map<String, Integer> autoIdMap;

    public MemoryMap() {
        memberMap = new LinkedHashMap<>();
        clubMap = new LinkedHashMap<>();
        boardMap = new LinkedHashMap<>();
        postingMap = new LinkedHashMap<>();
        autoIdMap = new LinkedHashMap<>();
    }

    public static MemoryMap getInstance(){

        if(memoryMap == null){
            memoryMap = new MemoryMap();
        }
        return memoryMap;
    }

    public Map<String , Integer> getAutoIdMap(){
        return this.autoIdMap;
    }

    public Map<String, CommunityMember> getMemberMap() {
        return memberMap;
    }

    public Map<String, TravelClub> getClubMap() {
        return clubMap;
    }

    public Map<String, SocialBoard> getBoardMap() {
        return boardMap;
    }

    public Map<String, Posting> getPostingMap() {
        return postingMap;
    }
}
