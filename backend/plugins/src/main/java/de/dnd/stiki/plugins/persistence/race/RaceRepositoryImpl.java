package de.dnd.stiki.plugins.persistence.race;

import de.dnd.stiki.domain.race.RaceEntity;
import de.dnd.stiki.domain.race.RaceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class RaceRepositoryImpl implements RaceRepository {

    @Autowired
    private RaceJpaRepository jpaRepository;

    @Autowired
    private RaceJpaToEntityMapper jpaToEntityMapper;

    @Override
    public List<RaceEntity> getAllRaces() {

        List<RaceJpa> jpaList = jpaRepository.findAll();

        return jpaToEntityMapper.mapJpasToEntities(jpaList);
    }
}
