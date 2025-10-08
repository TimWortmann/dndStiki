package de.dnd.stiki.adapters.character;

import de.dnd.stiki.adapters.AbstractDtoToEntityMapper;
import de.dnd.stiki.domain.character.CharacterEntity;
import org.springframework.stereotype.Component;

@Component
public class CharacterDtoToEntityMapper extends AbstractDtoToEntityMapper<CharacterDto, CharacterEntity> {

    @Override
    public CharacterEntity mapDtoToEntity(CharacterDto dto) {
        CharacterEntity entity = new CharacterEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setDndClass(dto.getDndClass());
        entity.setBackground(dto.getBackground());
        entity.setRace(dto.getRace());
        entity.setCurrentHealth(dto.getCurrentHealth());
        entity.setCurrentHitDice(dto.getCurrentHitDice());

        return entity;
    }
}
