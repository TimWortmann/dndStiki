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

    @Autowired
    private RaceEntityToJpaMapper entityToJpaMapper;

    @Override
    public List<RaceEntity> getAll() {

        List<RaceJpa> jpaList = jpaRepository.findAll();

        return jpaToEntityMapper.mapJpasToEntities(jpaList);
    }

    @Override
    public List<RaceEntity> save(List<RaceEntity> entities) {
        List<RaceJpa> jpaList = entityToJpaMapper.mapEntitiesToJpa(entities);

        return jpaToEntityMapper.mapJpasToEntities(jpaRepository.saveAll(jpaList));
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }
}
