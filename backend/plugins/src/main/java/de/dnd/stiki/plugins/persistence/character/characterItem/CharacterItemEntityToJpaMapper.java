package de.dnd.stiki.plugins.persistence.character.characterItem;

import de.dnd.stiki.domain.character.CharacterItemEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterItemEntityToJpaMapper extends AbstractEntityToJpaMapper<CharacterItemEntity, CharacterItemJpa> {

    @Override
    public CharacterItemJpa mapEntityToJpa(CharacterItemEntity entity) {
        CharacterItemJpa jpa = new CharacterItemJpa();
        jpa.setName(entity.getName());
        jpa.setDetail(entity.getDetail());
        jpa.setType(entity.getType().getName());
        jpa.setWeight(entity.getWeight());
        jpa.setValue(entity.getValue());
        jpa.setProperties(getStringFromList(entity.getProperties()));
        jpa.setDmg1(entity.getDmg1());
        jpa.setDmg2(entity.getDmg2());
        jpa.setDmgType(entity.getDmgType());
        jpa.setRange(entity.getRange());
        jpa.setAc(entity.getAc());
        jpa.setStealth(entity.getStealth());
        jpa.setMagic(entity.getMagic());
        jpa.setStrength(entity.getStrength());
        jpa.setText(entity.getText());
        jpa.setQuantity(entity.getQuantity());
        return jpa;
    }
}
