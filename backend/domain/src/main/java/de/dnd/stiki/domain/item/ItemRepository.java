package de.dnd.stiki.domain.item;

import java.util.List;

public interface ItemRepository {

    public List<ItemEntity> getAllItems();

    public List<ItemEntity> saveItems(List<ItemEntity> entity);

    public void deleteAllItems();
}
