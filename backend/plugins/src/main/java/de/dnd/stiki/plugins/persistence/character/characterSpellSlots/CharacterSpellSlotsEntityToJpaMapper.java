package de.dnd.stiki.plugins.persistence.character.characterSpellSlots;

import de.dnd.stiki.domain.character.CharacterSpellSlotsEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterSpellSlotsEntityToJpaMapper extends AbstractEntityToJpaMapper<CharacterSpellSlotsEntity, CharacterSpellSlotsJpa> {

    @Override
    public CharacterSpellSlotsJpa mapEntityToJpa(CharacterSpellSlotsEntity entity) {
        CharacterSpellSlotsJpa jpa = new CharacterSpellSlotsJpa();
        jpa.setSpellSlots(entity.getSpellSlots());
        jpa.setLevel(entity.getLevel());
        return jpa;
    }
}
