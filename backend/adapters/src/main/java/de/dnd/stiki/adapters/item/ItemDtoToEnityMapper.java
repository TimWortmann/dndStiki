package de.dnd.stiki.adapters.item;

import de.dnd.stiki.adapters.AbstractDtoToEntityMapper;
import de.dnd.stiki.domain.enums.ItemType;
import de.dnd.stiki.domain.item.ItemEntity;
import org.springframework.stereotype.Component;

@Component
public class ItemDtoToEnityMapper extends AbstractDtoToEntityMapper<ItemDto, ItemEntity> {

    @Override
    public ItemEntity mapDtoToEntity(ItemDto dto) {
        ItemEntity entity = new ItemEntity();
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

        return entity;
    }
}
