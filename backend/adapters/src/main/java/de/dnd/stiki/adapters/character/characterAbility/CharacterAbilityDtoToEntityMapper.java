package de.dnd.stiki.adapters.character.characterAbility;

import de.dnd.stiki.adapters.AbstractDtoToEntityMapper;
import de.dnd.stiki.domain.enums.AbilityType;
import de.dnd.stiki.domain.character.CharacterAbilityEntity;
import org.springframework.stereotype.Component;

@Component
public class CharacterAbilityDtoToEntityMapper extends AbstractDtoToEntityMapper<CharacterAbilityDto, CharacterAbilityEntity> {

    @Override
    public CharacterAbilityEntity mapDtoToEntity(CharacterAbilityDto dto) {

        CharacterAbilityEntity entity = new CharacterAbilityEntity();
        entity.setId(dto.getId());
        entity.setName(AbilityType.fromName(dto.getName()));
        entity.setBasicScore(dto.getBasicScore());
        entity.setBonus(dto.getBonus());
        entity.setSavingThrowProficiency(dto.getSavingThrowProficiency());
        return entity;
    }
}
