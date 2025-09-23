package de.dnd.stiki.domain.race;

import java.util.List;

public interface RaceRepository {

    public List<RaceEntity> getAllRaces();

    public List<RaceEntity> createRaces(List<RaceEntity> entities);

    public void deleteAllRaces();
}
