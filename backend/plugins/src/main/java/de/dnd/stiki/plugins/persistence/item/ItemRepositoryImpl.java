package de.dnd.stiki.plugins.persistence.item;

import de.dnd.stiki.domain.item.ItemEntity;
import de.dnd.stiki.domain.item.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ItemRepositoryImpl implements ItemRepository {

    @Autowired
    private ItemJpaRepository jpaRepository;

    @Autowired
    private ItemJpaToEntityMapper jpaToEntityMapper;

    @Autowired
    private ItemEntityToJpaMapper entityToJpaMapper;

    @Override
    public List<ItemEntity> getAllItems() {
        List<ItemJpa> jpaList = jpaRepository.findAll();

        return jpaToEntityMapper.mapJpasToEntities(jpaList);
    }

    @Override
    public List<ItemEntity> saveItems(List<ItemEntity> entities) {
        List<ItemJpa> jpaList = entityToJpaMapper.mapEntitiesToJpa(entities);

        return jpaToEntityMapper.mapJpasToEntities(jpaRepository.saveAll(jpaList));
    }

    @Override
    public void deleteAllItems() {
        jpaRepository.deleteAll();
    }
}
