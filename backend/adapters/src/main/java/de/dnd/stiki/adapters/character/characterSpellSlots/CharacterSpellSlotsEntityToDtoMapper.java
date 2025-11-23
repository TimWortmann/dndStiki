package de.dnd.stiki.adapters.character.characterSpellSlots;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.character.CharacterSpellSlotsEntity;
import org.springframework.stereotype.Component;

@Component
public class CharacterSpellSlotsEntityToDtoMapper extends AbstractEntityToDtoMapper<CharacterSpellSlotsEntity, CharacterSpellSlotsDto> {

    @Override
    public CharacterSpellSlotsDto mapEntityToDto(CharacterSpellSlotsEntity entity) {
        CharacterSpellSlotsDto dto = new CharacterSpellSlotsDto();
        dto.setSpellSlots(entity.getSpellSlots());
        dto.setLevel(entity.getLevel());
        return dto;
    }
}
