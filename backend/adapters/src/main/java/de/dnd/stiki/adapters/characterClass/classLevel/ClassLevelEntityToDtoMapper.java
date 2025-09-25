package de.dnd.stiki.adapters.characterClass.classLevel;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.adapters.characterClass.counter.CounterEntityToDtoMapper;
import de.dnd.stiki.adapters.characterClass.feature.FeatureEntityToDtoMapper;
import de.dnd.stiki.domain.characterClass.classLevel.ClassLevelEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ClassLevelEntityToDtoMapper extends AbstractEntityToDtoMapper<ClassLevelEntity, ClassLevelDto> {

    @Autowired
    private FeatureEntityToDtoMapper featureEntityToDtoMapper;

    @Autowired
    private CounterEntityToDtoMapper counterEntityToDtoMapper;

    @Override
    public ClassLevelDto mapEntityToDto(ClassLevelEntity entity) {

        ClassLevelDto dto = new ClassLevelDto();
        dto.setId(entity.getId());
        dto.setLevel(entity.getLevel());
        dto.setScoreImprovement(entity.hasScoreImprovement());
        dto.setSpellSlots(entity.getSpellSlots());
        dto.setFeatures(featureEntityToDtoMapper.mapEntitiesToDtos(entity.getFeatures()));
        dto.setCounters(counterEntityToDtoMapper.mapEntitiesToDtos(entity.getCounters()));

        return dto;
    }
}
