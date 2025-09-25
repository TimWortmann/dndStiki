package de.dnd.stiki.adapters.characterClass;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.adapters.characterClass.classLevel.ClassLevelEntityToDtoMapper;
import de.dnd.stiki.domain.characterClass.CharacterClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterClassEntityToDtoMapper extends AbstractEntityToDtoMapper<CharacterClassEntity, CharacterClassDto> {

    @Autowired
    private ClassLevelEntityToDtoMapper classLevelEntityToDtoMapper;

    @Override
    public CharacterClassDto mapEntityToDto(CharacterClassEntity entity) {

        CharacterClassDto dto = new CharacterClassDto();
        dto.setName(entity.getName());
        dto.setHitDice(entity.getHitDice());
        dto.setSavingThrowProficiencies(entity.getSavingThrowProficiencies());
        dto.setSkillProficiencies(entity.getSkillProficiencies());
        dto.setNumberOfSkillProficiencies(entity.getNumberOfSkillProficiencies());
        dto.setArmorProficiencies(entity.getArmorProficiencies());
        dto.setWeaponProficiencies(entity.getWeaponProficiencies());
        dto.setToolProficiencies(entity.getToolProficiencies());
        dto.setWealth(entity.getWealth());
        dto.setSpellAbility(entity.getSpellAbility());
        dto.setClassLevels(classLevelEntityToDtoMapper.mapEntitiesToDtos(entity.getClassLevels()));

        return dto;
    }
}
