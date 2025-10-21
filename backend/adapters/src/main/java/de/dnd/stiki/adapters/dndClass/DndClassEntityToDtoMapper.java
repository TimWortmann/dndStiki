package de.dnd.stiki.adapters.dndClass;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.adapters.dndClass.classLevel.ClassLevelEntityToDtoMapper;
import de.dnd.stiki.domain.reader.SubclassReader;
import de.dnd.stiki.adapters.trait.TraitEntityToDtoMapper;
import de.dnd.stiki.domain.dndClass.DndClassEntity;
import de.dnd.stiki.domain.dndClass.classLevel.ClassLevelEntity;
import de.dnd.stiki.domain.dndClass.feature.FeatureEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Stream;

@Component
public class DndClassEntityToDtoMapper extends AbstractEntityToDtoMapper<DndClassEntity, DndClassDto> {

    @Autowired
    private ClassLevelEntityToDtoMapper classLevelEntityToDtoMapper;

    @Autowired
    private TraitEntityToDtoMapper traitEntityToDtoMapper;

    @Autowired
    private SubclassReader subclassReader;

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
        // Extract all feature names from the levels
        List<String> featureNames = classLevels.stream()
                .flatMap(level -> level.getFeatures() != null
                        ? level.getFeatures().stream()
                        : Stream.empty()) // handle null features
                .map(FeatureEntity::getName)
                .toList();

        // Delegate parsing to the other method
        return subclassReader.getDndSubclasses(featureNames);
    }

}
