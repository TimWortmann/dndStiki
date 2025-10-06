package de.dnd.stiki.adapters.dndClass;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.adapters.dndClass.classLevel.ClassLevelEntityToDtoMapper;
import de.dnd.stiki.adapters.trait.TraitEntityToDtoMapper;
import de.dnd.stiki.domain.dndClass.DndClassEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DndClassEntityToDtoMapper extends AbstractEntityToDtoMapper<DndClassEntity, DndClassDto> {

    @Autowired
    private ClassLevelEntityToDtoMapper classLevelEntityToDtoMapper;

    @Autowired
    private TraitEntityToDtoMapper traitEntityToDtoMapper;

    @Override
    public DndClassDto mapEntityToDto(DndClassEntity entity) {

        DndClassDto dto = new DndClassDto();
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
        dto.setTraits(traitEntityToDtoMapper.mapEntitiesToDtos(entity.getTraits()));

        return dto;
    }
}
