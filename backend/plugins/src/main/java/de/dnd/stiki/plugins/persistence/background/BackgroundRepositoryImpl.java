package de.dnd.stiki.plugins.persistence.background;

import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.domain.background.BackgroundRepository;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitJpa;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BackgroundRepositoryImpl implements BackgroundRepository {

    @Autowired
    private BackgroundJpaRepository jpaRepository;

    @Autowired
    private BackgroundJpaToEntityMapper jpaToEntityMapper;

    @Autowired
    private BackgroundEntityToJpaMapper entityToJpaMapper;

    @Autowired
    private TraitJpaRepository traitRepository;

    @Override
    public List<BackgroundEntity> getAllBackgrounds() {

        List<BackgroundJpa> jpaList = jpaRepository.findAll();

        return jpaToEntityMapper.mapJpasToEntities(jpaList);
    }

    @Override
    public List<BackgroundEntity> createBackgrounds(List<BackgroundEntity> entities) {
        List<BackgroundJpa> jpaList = entityToJpaMapper.mapEntitiesToJpa(entities);

        for(BackgroundJpa jpa : jpaList){
            for (TraitJpa traitJpa :jpa.getTraits()){
                traitRepository.save(traitJpa);
            }
        }

        return jpaToEntityMapper.mapJpasToEntities(jpaRepository.saveAll(jpaList));
    }

    @Override
    public void deleteAllBackgrounds() {
        jpaRepository.deleteAll();
    }
}
