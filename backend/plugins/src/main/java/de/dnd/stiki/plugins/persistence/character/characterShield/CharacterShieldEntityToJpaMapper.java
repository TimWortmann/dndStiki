package de.dnd.stiki.plugins.persistence.character.characterShield;

import de.dnd.stiki.domain.character.CharacterShieldEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterShieldEntityToJpaMapper extends AbstractEntityToJpaMapper<CharacterShieldEntity, CharacterShieldJpa> {

    @Override
    public CharacterShieldJpa mapEntityToJpa(CharacterShieldEntity entity) {
        if (entity == null) {
            return null;
        }

        CharacterShieldJpa jpa = new CharacterShieldJpa();
        jpa.setName(entity.getName());
        jpa.setAc(entity.getAc());
        return jpa;
    }
}
