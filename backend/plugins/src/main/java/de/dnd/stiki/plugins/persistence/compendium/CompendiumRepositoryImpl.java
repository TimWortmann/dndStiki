package de.dnd.stiki.plugins.persistence.compendium;

import de.dnd.stiki.domain.compendium.CompendiumEntity;
import de.dnd.stiki.domain.compendium.CompendiumRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CompendiumRepositoryImpl implements CompendiumRepository {

    @Autowired
    CompendiumJpaRepository jpaRepository;

    @Autowired
    CompendiumEntityToJpaMapper entityToJpaMapper;

    @Autowired
    CompendiumJpaToEntityMapper jpaToEntityMapper;

    @Override
    public CompendiumEntity getCompendium() {

        List<CompendiumJpa> jpaList = jpaRepository.findAll();

        if (jpaList.isEmpty()) {
            return new CompendiumEntity();
        }

        return jpaToEntityMapper.mapJpaToEntity(jpaList.getFirst());
    }

    @Override
    public CompendiumEntity saveCompendium(CompendiumEntity entity) {
        return jpaToEntityMapper.mapJpaToEntity(jpaRepository.save(entityToJpaMapper.mapEntityToJpa(entity)));
    }

    @Override
    public void deleteCompendium() {
        jpaRepository.deleteAll();
    }
}
