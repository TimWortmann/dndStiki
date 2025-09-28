package de.dnd.stiki.plugins.persistence.background;

import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.domain.background.BackgroundRepository;
import de.dnd.stiki.plugins.persistence.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class BackgroundRepositoryImpl extends AbstractRepositoryImpl<
        BackgroundEntity,
        BackgroundJpa,
        BackgroundJpaRepository,
        BackgroundJpaToEntityMapper,
        BackgroundEntityToJpaMapper> implements BackgroundRepository {

}
