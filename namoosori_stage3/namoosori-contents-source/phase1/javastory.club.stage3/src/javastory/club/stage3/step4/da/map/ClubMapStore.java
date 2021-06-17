package javastory.club.stage3.step4.da.map;

import java.util.*;

import javastory.club.stage3.step1.entity.AutoIdEntity;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step4.da.map.io.MemoryMap;
import javastory.club.stage3.step4.store.ClubStore;
import javastory.club.stage3.step4.util.ClubDuplicationException;

public class ClubMapStore implements ClubStore{

    private Map<String, TravelClub> clubMap;
    private List<Integer> idList;

    public ClubMapStore() {
        this.clubMap = MemoryMap.getInstance().getClubMap();
        this.idList = MemoryMap.getInstance().getAutoIdList();
    }

    @Override
    public String create(TravelClub club) {
        Optional.ofNullable(clubMap.get(club.getId()))
                .ifPresent(travelClub -> {throw new ClubDuplicationException("이미 존재하는 클럽입니다.");});
        System.out.println(clubMap.values().toString());

        int sequence = 1;
        if(idList.size() == 0){
            System.out.println("/////////////////확인1");
            idList.add(sequence);
        }else{
            sequence = idList.get(0) + 1;
            idList.add(sequence);
        }
        System.out.println("////////////////확인2");
        String id = String.format("%05d", sequence); //가장 앞에 있는 값으로 설정

        club.setAutoId(id); 

        Collections.reverse(idList); // 가장 큰 값이 앞에 오도록 설정
        System.out.println(idList.toString());


        clubMap.put(club.getId(),club);

        return club.getId();
    }

    @Override
    public TravelClub retrieve(String clubId) {
        return clubMap.get(clubId);
    }

    @Override
    public TravelClub retrieveByName(String name) {

        TravelClub foundClub = null;

        for (TravelClub club : clubMap.values()) {
           if(club.getName().equals(name)){ //동묵아.... 주의하자..
               foundClub = club;
               break;
           }
        }

        return foundClub;
    }

    @Override
    public void update(TravelClub club) {

        clubMap.put(club.getId(),club);
    }

    @Override
    public void delete(String clubId) {
        clubMap.remove(clubId);
    }

    @Override
    public boolean exists(String clubId) {
        return Optional.ofNullable(clubMap.get(clubId))
                .isPresent();
    }
}
