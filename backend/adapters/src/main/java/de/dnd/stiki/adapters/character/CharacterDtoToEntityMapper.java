package de.dnd.stiki.adapters.character;

import de.dnd.stiki.adapters.AbstractDtoToEntityMapper;
import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityDtoToEntityMapper;
import de.dnd.stiki.domain.character.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterDtoToEntityMapper extends AbstractDtoToEntityMapper<CharacterDto, CharacterEntity> {

    @Autowired
    private CharacterAbilityDtoToEntityMapper abilityDtoToEntityMapper;

    @Override
    public CharacterEntity mapDtoToEntity(CharacterDto dto) {
        CharacterEntity entity = new CharacterEntity();

        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setLevel(dto.getLevel());
        entity.setDndClass(dto.getDndClass());
        entity.setBackground(dto.getBackground());
        entity.setRace(dto.getRace());
        entity.setCurrentHealth(dto.getCurrentHealth());
        entity.setCurrentHitDice(dto.getCurrentHitDice());
        entity.setAbilities(abilityDtoToEntityMapper.mapDtosToEntities(dto.getAbilities()));

        return entity;
    }
}
