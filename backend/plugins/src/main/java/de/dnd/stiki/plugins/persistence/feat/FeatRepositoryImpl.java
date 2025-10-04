package de.dnd.stiki.plugins.persistence.feat;

import de.dnd.stiki.domain.feat.FeatEntity;
import de.dnd.stiki.domain.feat.FeatRepository;
import de.dnd.stiki.plugins.persistence.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class FeatRepositoryImpl extends AbstractRepositoryImpl<
        FeatEntity,
        FeatJpa,
        FeatJpaRepository,
        FeatJpaToEntityMapper,
        FeatEntityToJpaMapper> implements FeatRepository {
}
