package de.dnd.stiki.plugins.persistence.background;

import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.domain.background.BackgroundRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BackgroundRepositoryImpl implements BackgroundRepository {

    @Autowired
    private BackgroundJpaRepository jpaRepository;

    @Autowired
    private BackgroundJpaToEntityMapper jpaToEntityMapper;

    @Override
    public List<BackgroundEntity> getAllBackgrounds() {

        List<BackgroundJpa> jpaList = jpaRepository.findAll();

        return jpaToEntityMapper.mapJpasToEntities(jpaList);
    }
}
