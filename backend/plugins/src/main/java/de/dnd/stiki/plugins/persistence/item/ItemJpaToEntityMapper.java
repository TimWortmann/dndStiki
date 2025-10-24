package de.dnd.stiki.plugins.persistence.item;

import de.dnd.stiki.domain.enums.ItemType;
import de.dnd.stiki.domain.item.ItemEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemJpaToEntityMapper extends AbstractJpaToEntityMapper<ItemJpa, ItemEntity> {

    @Override
    public ItemEntity mapJpaToEntity(ItemJpa jpa) {
        ItemEntity entity = new ItemEntity();
        entity.setName(jpa.getName());
        entity.setDetail(jpa.getDetail());
        entity.setType(ItemType.fromName(jpa.getType()));
        entity.setWeight(jpa.getWeight());
        entity.setValue(jpa.getValue());
        entity.setProperties(getListFromString(jpa.getProperties()));
        entity.setDmg1(jpa.getDmg1());
        entity.setDmg2(jpa.getDmg2());
        entity.setDmgType(jpa.getDmgType());
        entity.setRange(jpa.getRange());
        entity.setAc(jpa.getAc());
        entity.setStealth(jpa.getStealth());
        entity.setMagic(jpa.getMagic());
        entity.setStrength(jpa.getStrength());
        entity.setText(jpa.getText());

        return entity;
    }
}
