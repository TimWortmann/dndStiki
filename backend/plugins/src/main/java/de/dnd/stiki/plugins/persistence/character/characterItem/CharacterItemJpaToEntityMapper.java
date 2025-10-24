package de.dnd.stiki.plugins.persistence.character.characterItem;

import de.dnd.stiki.domain.enums.ItemType;
import de.dnd.stiki.domain.item.ItemEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterItemJpaToEntityMapper extends AbstractJpaToEntityMapper<CharacterItemJpa, ItemEntity> {

    @Override
    public ItemEntity mapJpaToEntity(CharacterItemJpa jpa) {
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
