package de.dnd.stiki.plugins.persistence.character.characterArmor;

import de.dnd.stiki.domain.character.CharacterArmorEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterArmorEntityToJpaMapper extends AbstractEntityToJpaMapper<CharacterArmorEntity, CharacterArmorJpa> {

    @Override
    public CharacterArmorJpa mapEntityToJpa(CharacterArmorEntity entity) {
        if (entity == null) {
            return null;
        }

        CharacterArmorJpa jpa = new CharacterArmorJpa();
        jpa.setName(entity.getName());
        jpa.setAc(entity.getAc());
        jpa.setType(entity.getType().getName());
        return jpa;
    }
}
