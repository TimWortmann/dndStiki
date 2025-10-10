package de.dnd.stiki.plugins.persistence.background;

import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.domain.background.BackgroundRepository;
import de.dnd.stiki.plugins.persistence.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class BackgroundRepositoryImpl extends AbstractRepositoryImpl<
        BackgroundEntity,
        BackgroundJpa,
        BackgroundJpaRepository,
        BackgroundJpaToEntityMapper,
        BackgroundEntityToJpaMapper> implements BackgroundRepository {

    @Override
    public BackgroundEntity getByName(String name) {
        Optional<BackgroundJpa> jpaOptional = jpaRepository.findById(name);

        return jpaOptional.map(backgroundJpa -> jpaToEntityMapper.mapJpaToEntity(backgroundJpa)).orElse(null);
    }

}
