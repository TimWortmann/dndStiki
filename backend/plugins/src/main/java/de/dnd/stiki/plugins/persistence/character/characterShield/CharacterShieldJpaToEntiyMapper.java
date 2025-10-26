package de.dnd.stiki.plugins.persistence.character.characterShield;

import de.dnd.stiki.domain.character.CharacterShieldEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterShieldJpaToEntiyMapper extends AbstractJpaToEntityMapper<CharacterShieldJpa, CharacterShieldEntity> {

    @Override
    public CharacterShieldEntity mapJpaToEntity(CharacterShieldJpa jpa) {
        if (jpa == null) {
            return null;
        }

        CharacterShieldEntity entity = new CharacterShieldEntity();
        entity.setName(jpa.getName());
        entity.setAc(jpa.getAc());
        return entity;
    }
}
