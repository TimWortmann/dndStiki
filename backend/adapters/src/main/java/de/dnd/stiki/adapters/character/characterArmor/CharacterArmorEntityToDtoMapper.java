package de.dnd.stiki.adapters.character.characterArmor;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.character.CharacterArmorEntity;
import org.springframework.stereotype.Component;

@Component
public class CharacterArmorEntityToDtoMapper extends AbstractEntityToDtoMapper<CharacterArmorEntity, CharacterArmorDto> {

    @Override
    public CharacterArmorDto mapEntityToDto(CharacterArmorEntity entity) {
        if (entity == null) {
            return null;
        }

        CharacterArmorDto dto = new CharacterArmorDto();
        dto.setName(entity.getName());
        dto.setAc(entity.getAc());
        dto.setType(entity.getType().getName());
        return dto;
    }
}
