package de.dnd.stiki.plugins.persistence.race;

import de.dnd.stiki.domain.race.RaceEntity;
import de.dnd.stiki.domain.race.RaceRepository;
import de.dnd.stiki.plugins.persistence.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class RaceRepositoryImpl extends AbstractRepositoryImpl<
        RaceEntity,
        RaceJpa,
        RaceJpaRepository,
        RaceJpaToEntityMapper,
        RaceEntityToJpaMapper> implements RaceRepository {
}
