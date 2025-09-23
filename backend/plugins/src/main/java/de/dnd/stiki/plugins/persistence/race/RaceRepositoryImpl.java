package de.dnd.stiki.plugins.persistence.race;

import de.dnd.stiki.domain.race.RaceEntity;
import de.dnd.stiki.domain.race.RaceRepository;
import de.dnd.stiki.plugins.persistence.trait.TraitJpa;
import de.dnd.stiki.plugins.persistence.trait.TraitJpaRepository;
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

    @Autowired
    private TraitJpaRepository traitRepository;

    @Override
    public List<RaceEntity> getAllRaces() {

        List<RaceJpa> jpaList = jpaRepository.findAll();

        return jpaToEntityMapper.mapJpasToEntities(jpaList);
    }

    @Override
    public List<RaceEntity> createRaces(List<RaceEntity> entities) {
        List<RaceJpa> jpaList = entityToJpaMapper.mapEntitiesToJpa(entities);

        for(RaceJpa jpa : jpaList){
            for (TraitJpa traitJpa :jpa.getTraits()){
                traitRepository.save(traitJpa);
            }
        }

        return jpaToEntityMapper.mapJpasToEntities(jpaRepository.saveAll(jpaList));
    }

    @Override
    public void deleteAllRaces() {
        jpaRepository.deleteAll();
    }
}
