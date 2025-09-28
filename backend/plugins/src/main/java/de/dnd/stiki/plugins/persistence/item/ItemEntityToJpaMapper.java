package de.dnd.stiki.plugins.persistence.item;

import de.dnd.stiki.domain.item.ItemEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class ItemEntityToJpaMapper extends AbstractEntityToJpaMapper<ItemEntity, ItemJpa> {

    @Override
    public ItemJpa mapEntityToJpa(ItemEntity entity) {

        ItemJpa jpa = new ItemJpa();
        jpa.setName(entity.getName());
        jpa.setDetail(entity.getDetail());
        jpa.setType(entity.getType());
        jpa.setWeight(entity.getWeight());
        jpa.setValue(entity.getValue());
        jpa.setProperty(entity.getProperty());
        jpa.setDmg1(entity.getDmg1());
        jpa.setDmg2(entity.getDmg2());
        jpa.setDmgType(entity.getDmgType());
        jpa.setRange(entity.getRange());
        jpa.setAc(entity.getAc());
        jpa.setStealth(entity.getStealth());
        jpa.setMagic(entity.getMagic());
        jpa.setStrength(entity.getStrength());
        jpa.setText(entity.getText());

        return jpa;
    }
}
