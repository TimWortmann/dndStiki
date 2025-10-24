package de.dnd.stiki.adapters.character.characterItem;

import de.dnd.stiki.adapters.AbstractDtoToEntityMapper;
import de.dnd.stiki.domain.character.CharacterItemEntity;
import de.dnd.stiki.domain.enums.ItemType;
import org.springframework.stereotype.Component;

@Component
public class CharacterItemDtoToEntityMapper extends AbstractDtoToEntityMapper<CharacterItemDto, CharacterItemEntity> {

    @Override
    public CharacterItemEntity mapDtoToEntity(CharacterItemDto dto) {
        CharacterItemEntity entity = new CharacterItemEntity();
        entity.setName(dto.getName());
        entity.setDetail(dto.getDetail());
        entity.setType(ItemType.fromName(dto.getType()));
        entity.setWeight(dto.getWeight());
        entity.setValue(dto.getValue());
        entity.setProperties(dto.getProperties());
        entity.setDmg1(dto.getDmg1());
        entity.setDmg2(dto.getDmg2());
        entity.setDmgType(dto.getDmgType());
        entity.setRange(dto.getRange());
        entity.setAc(dto.getAc());
        entity.setStealth(dto.getStealth());
        entity.setMagic(dto.getMagic());
        entity.setStrength(dto.getStrength());
        entity.setText(dto.getText());
        entity.setQuantity(dto.getQuantity());
        return entity;
    }
}
