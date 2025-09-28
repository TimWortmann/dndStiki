package de.dnd.stiki.adapters.item;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.item.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemEntityToDtoMapper extends AbstractEntityToDtoMapper<ItemEntity, ItemDto> {

    @Override
    public ItemDto mapEntityToDto(ItemEntity entity) {
        ItemDto dto = new ItemDto();
        dto.setName(entity.getName());
        dto.setDetail(entity.getDetail());
        dto.setType(entity.getType());
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

        return dto;
    }
}
