package de.dnd.stiki.adapters.character.characterAbility;

import de.dnd.stiki.adapters.AbstractDtoToEntityMapper;
import de.dnd.stiki.domain.character.AbilityType;
import de.dnd.stiki.domain.character.CharacterAbilityEntity;
import org.springframework.stereotype.Component;

@Component
public class CharacterAbilityDtoToEntityMapper extends AbstractDtoToEntityMapper<CharacterAbilityDto, CharacterAbilityEntity> {

    @Override
    public CharacterAbilityEntity mapDtoToEntity(CharacterAbilityDto dto) {

        CharacterAbilityEntity entity = new CharacterAbilityEntity();
        entity.setId(dto.getId());
        entity.setAbility(AbilityType.fromName(dto.getAbility()));
        entity.setBasicScore(dto.getBasicScore());
        entity.setBonus(dto.getBonus());
        entity.setSavingThrowProficiency(dto.getSavingThrowProficiency());
        return entity;
    }
}
