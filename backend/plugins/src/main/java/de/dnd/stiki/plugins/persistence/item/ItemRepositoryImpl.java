package de.dnd.stiki.plugins.persistence.item;

import de.dnd.stiki.domain.item.ItemEntity;
import de.dnd.stiki.domain.item.ItemRepository;
import de.dnd.stiki.plugins.persistence.AbstractRepositoryImpl;
import org.springframework.stereotype.Repository;

@Repository
public class ItemRepositoryImpl extends AbstractRepositoryImpl<
        ItemEntity,
        ItemJpa,
        ItemJpaRepository,
        ItemJpaToEntityMapper,
        ItemEntityToJpaMapper> implements ItemRepository {


}
