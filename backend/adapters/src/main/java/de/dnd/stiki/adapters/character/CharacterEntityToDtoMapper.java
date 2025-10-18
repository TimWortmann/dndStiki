package de.dnd.stiki.adapters.character;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterSkill.CharacterSkillEntityToDtoMapper;
import de.dnd.stiki.adapters.reader.SubclassReader;
import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CharacterEntityToDtoMapper extends AbstractEntityToDtoMapper<CharacterEntity, CharacterDto> {

    @Autowired
    private CharacterAbilityEntityToDtoMapper abilityEntityToDtoMapper;

    @Autowired
    private CharacterSkillEntityToDtoMapper skillEntityToDtoMapper;

    @Autowired
    private SubclassReader subclassReader;

    @Override
    public CharacterDto mapEntityToDto(CharacterEntity entity) {

        CharacterDto dto = new CharacterDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setLevel(entity.getLevel());
        dto.setDndClass(entity.getDndClass());
        dto.setDndSubclass(entity.getDndSubclass());
        dto.setDndSubclasses(getDndSubclasses(entity.getClassFeatures()));
        if (entity.getSpellcastingAbility() != null) {
            dto.setSpellcastingAbility(entity.getSpellcastingAbility().getName());
        }
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
        dto.setSkills(skillEntityToDtoMapper.mapEntitiesToDtos(entity.getSkills()));

        return dto;
    }

    private List<String> getDndSubclasses(List<TraitEntity> classFeatures) {
        // Extract all feature names
        List<String> featureNames = classFeatures.stream()
                .map(TraitEntity::getName)
                .toList();

        // Delegate parsing to the shared method
        return subclassReader.getDndSubclasses(featureNames);
    }

}
