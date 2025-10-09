package de.dnd.stiki.adapters.character;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityEntityToDtoMapper;
import de.dnd.stiki.domain.character.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterEntityToDtoMapper extends AbstractEntityToDtoMapper<CharacterEntity, CharacterDto> {

    @Autowired
    private CharacterAbilityEntityToDtoMapper abilityEntityToDtoMapper;

    @Override
    public CharacterDto mapEntityToDto(CharacterEntity entity) {
        CharacterDto dto = new CharacterDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLevel(entity.getLevel());
        dto.setDndClass(entity.getDndClass());
        dto.setBackground(entity.getBackground());
        dto.setRace(entity.getRace());
        dto.setMaxHealth(entity.getMaxHealth());
        dto.setCurrentHealth(entity.getCurrentHealth());
        dto.setHitDice(entity.getHitDice());
        dto.setMaxHitDice(entity.getMaxHitDice());
        dto.setCurrentHitDice(entity.getCurrentHitDice());
        dto.setArmorClass(entity.getArmorClass());
        dto.setSpeed(entity.getSpeed());
        dto.setPassivePerception(entity.getPassivePerception());
        dto.setProficiencyBonus(entity.getProficiencyBonus());
        dto.setClassFeatures(entity.getClassFeatures());
        dto.setBackgroundTraits(entity.getBackgroundTraits());
        dto.setRaceTraits(entity.getRaceTraits());
        dto.setFeats(entity.getFeats());
        dto.setAbilities(abilityEntityToDtoMapper.mapEntitiesToDtos(entity.getAbilities()));

        return dto;
    }
}
