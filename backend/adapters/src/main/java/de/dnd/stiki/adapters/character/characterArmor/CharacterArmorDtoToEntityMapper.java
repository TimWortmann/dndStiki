package de.dnd.stiki.adapters.character.characterArmor;

import de.dnd.stiki.adapters.AbstractDtoToEntityMapper;
import de.dnd.stiki.domain.character.CharacterArmorEntity;
import de.dnd.stiki.domain.enums.ItemType;
import org.springframework.stereotype.Component;

@Component
public class CharacterArmorDtoToEntityMapper extends AbstractDtoToEntityMapper<CharacterArmorDto, CharacterArmorEntity> {

    @Override
    public CharacterArmorEntity mapDtoToEntity(CharacterArmorDto dto) {
        if (dto == null) {
            return null;
        }

        CharacterArmorEntity entity = new CharacterArmorEntity();
        entity.setName(dto.getName());
        entity.setAc(dto.getAc());
        entity.setType(ItemType.fromName(dto.getType()));
        return entity;
    }
}
