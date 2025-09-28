package de.dnd.stiki.plugins.persistence.spell;

import de.dnd.stiki.domain.spell.SpellEntity;
import de.dnd.stiki.domain.spell.SpellRepository;
import de.dnd.stiki.plugins.persistence.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class SpellRepositoryImpl extends AbstractRepositoryImpl<
        SpellEntity,
        SpellJpa,
        SpellJpaRepository,
        SpellJpaToEntityMapper,
        SpellEntityToJpaMapper> implements SpellRepository {
}
