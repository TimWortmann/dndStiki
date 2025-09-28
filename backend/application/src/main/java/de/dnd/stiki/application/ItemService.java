package de.dnd.stiki.application;

import de.dnd.stiki.adapters.item.ItemDto;
import de.dnd.stiki.adapters.item.ItemEntityToDtoMapper;
import de.dnd.stiki.domain.item.ItemEntity;
import de.dnd.stiki.domain.item.ItemRepository;
import org.springframework.stereotype.Service;

@Service
public class ItemService extends AbstractService<
        ItemEntity,
        ItemDto,
        ItemRepository,
        ItemEntityToDtoMapper> {
}
