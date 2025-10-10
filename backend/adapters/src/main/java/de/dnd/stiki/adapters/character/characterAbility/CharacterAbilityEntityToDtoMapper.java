package de.dnd.stiki.adapters.character.characterAbility;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.character.CharacterAbilityEntity;
import org.springframework.stereotype.Component;

@Component
public class CharacterAbilityEntityToDtoMapper extends AbstractEntityToDtoMapper<CharacterAbilityEntity, CharacterAbilityDto> {

    @Override
    public CharacterAbilityDto mapEntityToDto(CharacterAbilityEntity entity) {
        CharacterAbilityDto dto = new CharacterAbilityDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName().toString());
        dto.setBasicScore(entity.getBasicScore());
        dto.setBonus(entity.getBonus());
        dto.setSavingThrowProficiency(entity.getSavingThrowProficiency());
        return dto;
    }
}
