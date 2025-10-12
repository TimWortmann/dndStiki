package de.dnd.stiki.adapters.dndClass;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.adapters.dndClass.classLevel.ClassLevelEntityToDtoMapper;
import de.dnd.stiki.adapters.trait.TraitEntityToDtoMapper;
import de.dnd.stiki.domain.dndClass.DndClassEntity;
import de.dnd.stiki.domain.dndClass.classLevel.ClassLevelEntity;
import de.dnd.stiki.domain.dndClass.feature.FeatureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
        dto.setSubclasses(getDndSubclasses(entity.getClassLevels()));
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

    private List<String> getDndSubclasses(List<ClassLevelEntity> classLevels) {
        List<String> subclasses = classLevels.stream()
                .flatMap(level -> level.getFeatures() != null
                        ? level.getFeatures().stream()
                        : Stream.empty()) // handle null features
                .map(FeatureEntity::getName)
                .map(name -> {
                    String prefix = null;
                    if (name.contains("Subclass: ")) {
                        prefix = "Subclass: ";
                    } else if (name.contains("Archetype: ")) {
                        prefix = "Archetype: ";
                    }

                    if (prefix != null) {
                        return name.substring(name.indexOf(prefix) + prefix.length()).trim();
                    } else {
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .distinct()
                .collect(Collectors.toList());

        // Add "No Subclass" at the beginning
        subclasses.addFirst("No Subclass");

        return subclasses;
    }

}
