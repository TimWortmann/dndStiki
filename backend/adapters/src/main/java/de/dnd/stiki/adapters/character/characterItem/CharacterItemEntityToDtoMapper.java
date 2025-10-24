package de.dnd.stiki.adapters.character.characterItem;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.character.CharacterItemEntity;
import org.springframework.stereotype.Component;

@Component
public class CharacterItemEntityToDtoMapper extends AbstractEntityToDtoMapper<CharacterItemEntity, CharacterItemDto> {

    @Override
    public CharacterItemDto mapEntityToDto(CharacterItemEntity entity) {
        CharacterItemDto dto = new CharacterItemDto();
        dto.setName(entity.getName());
        dto.setDetail(entity.getDetail());
        dto.setType(entity.getType().getName());
        dto.setWeight(entity.getWeight());
        dto.setValue(entity.getValue());
        dto.setProperties(entity.getProperties());
        dto.setDmg1(entity.getDmg1());
        dto.setDmg2(entity.getDmg2());
        dto.setDmgType(entity.getDmgType());
        dto.setRange(entity.getRange());
        dto.setAc(entity.getAc());
        dto.setStealth(entity.getStealth());
        dto.setMagic(entity.getMagic());
        dto.setStrength(entity.getStrength());
        dto.setText(entity.getText());
        dto.setQuantity(entity.getQuantity());
        return dto;
    }
}
