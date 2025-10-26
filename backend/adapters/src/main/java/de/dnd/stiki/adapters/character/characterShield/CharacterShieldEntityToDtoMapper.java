package de.dnd.stiki.adapters.character.characterShield;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.character.CharacterShieldEntity;
import org.springframework.stereotype.Component;

@Component
public class CharacterShieldEntityToDtoMapper extends AbstractEntityToDtoMapper<CharacterShieldEntity, CharacterShieldDto> {

    @Override
    public CharacterShieldDto mapEntityToDto(CharacterShieldEntity entity) {
        if (entity == null) {
            return null;
        }

        CharacterShieldDto dto = new CharacterShieldDto();
        dto.setName(entity.getName());
        dto.setAc(entity.getAc());
        return dto;
    }
}
