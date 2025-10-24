package de.dnd.stiki.plugins.persistence.character.characterItem;

import de.dnd.stiki.domain.character.CharacterItemEntity;
import de.dnd.stiki.domain.enums.ItemType;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterItemJpaToEntityMapper extends AbstractJpaToEntityMapper<CharacterItemJpa, CharacterItemEntity> {

    @Override
    public CharacterItemEntity mapJpaToEntity(CharacterItemJpa jpa) {
        CharacterItemEntity entity = new CharacterItemEntity();
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
        entity.setQuantity(jpa.getQuantity());
        return entity;
    }
}
