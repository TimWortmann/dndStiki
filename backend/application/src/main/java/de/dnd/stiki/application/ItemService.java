package de.dnd.stiki.application;

import de.dnd.stiki.adapters.item.ItemDto;
import de.dnd.stiki.adapters.item.ItemEntityToDtoMapper;
import de.dnd.stiki.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ItemService {

    @Autowired
    private ItemRepository repository;

    @Autowired
    private ItemEntityToDtoMapper entityToDtoMapper;

    public List<ItemDto> getAllItems() {
        return entityToDtoMapper.mapEntitiesToDtos(repository.getAllItems());
    }
}
