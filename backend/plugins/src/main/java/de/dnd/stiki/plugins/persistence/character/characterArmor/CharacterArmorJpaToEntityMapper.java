package de.dnd.stiki.plugins.persistence.character.characterArmor;

import de.dnd.stiki.domain.character.CharacterArmorEntity;
import de.dnd.stiki.domain.enums.ItemType;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterArmorJpaToEntityMapper extends AbstractJpaToEntityMapper<CharacterArmorJpa, CharacterArmorEntity> {

    @Override
    public CharacterArmorEntity mapJpaToEntity(CharacterArmorJpa jpa) {
        if (jpa == null) {
            return null;
        }

        CharacterArmorEntity entity = new CharacterArmorEntity();
        entity.setName(jpa.getName());
        entity.setAc(jpa.getAc());
        entity.setType(ItemType.fromName(jpa.getType()));
        return entity;
    }
}
