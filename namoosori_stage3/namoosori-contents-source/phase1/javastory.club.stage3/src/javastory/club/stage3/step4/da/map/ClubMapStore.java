package javastory.club.stage3.step4.da.map;

import javastory.club.stage3.step1.entity.AutoIdEntity;
import javastory.club.stage3.step1.entity.club.TravelClub;
import javastory.club.stage3.step4.da.map.io.MemoryMap;
import javastory.club.stage3.step4.store.ClubStore;
import javastory.club.stage3.step4.util.ClubDuplicationException;

import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class ClubMapStore implements ClubStore {

    private Map<String, TravelClub> clubMap;
    private Map<String, Integer> autoIdMap;

    public ClubMapStore() {
        this.clubMap = MemoryMap.getInstance().getClubMap();
        this.autoIdMap = MemoryMap.getInstance().getAutoIdMap();
    }


    @Override
    public String create(TravelClub club) {
        Optional.ofNullable(clubMap.get(club.getId()))
                .ifPresent(targetClub -> {
                    throw new ClubDuplicationException("같은 이름의 클럽이 존재합니다." + targetClub.getName());
                });

        if(club instanceof AutoIdEntity){
            String className = TravelClub.class.getSimpleName();
            if(autoIdMap.get(className) == null){
                autoIdMap.put(className, 1);
            }
            int keySequence = autoIdMap.get(className);
            String autoId = String.format("%05d", keySequence);
            club.setAutoId(autoId);
            autoIdMap.put(className, ++keySequence);
        }

        clubMap.put(club.getId(), club);

        return club.getUsid();
    }

    @Override
    public TravelClub retrieve(String clubId) {
        return clubMap.get(clubId);
    }

    @Override
    public TravelClub retrieveByName(String name) {

        Iterator<TravelClub> clubIter = clubMap.values().iterator();

        TravelClub clubFound = null;

        while(clubIter.hasNext()){
            TravelClub club = clubIter.next();
            if(club.getName().equals(name)){
                clubFound = club;
                break;
            }
        }

        return clubFound;

    }

    @Override
    public void update(TravelClub club) {
        clubMap.put(club.getId(), club);
    }

    @Override
    public void delete(String clubId) {
        clubMap.remove(clubId);
    }

    @Override
    public boolean exists(String clubId) {
        return Optional.ofNullable(clubMap.get(clubId)).isPresent();
    }
}
