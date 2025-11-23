package de.dnd.stiki.plugins.persistence.character.characterSpellSlots;

import de.dnd.stiki.domain.character.CharacterSpellSlotsEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterSpellSlotsJpaToEntityMapper extends AbstractJpaToEntityMapper<CharacterSpellSlotsJpa, CharacterSpellSlotsEntity> {

    @Override
    public CharacterSpellSlotsEntity mapJpaToEntity(CharacterSpellSlotsJpa jpa) {
        CharacterSpellSlotsEntity entity = new CharacterSpellSlotsEntity();
        entity.setSpellSlots(jpa.getSpellSlots());
        entity.setLevel(jpa.getLevel());
        return entity;
    }
}
