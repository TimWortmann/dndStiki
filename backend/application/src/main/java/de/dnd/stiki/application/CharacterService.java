package de.dnd.stiki.application;

import de.dnd.stiki.adapters.character.CharacterDto;
import de.dnd.stiki.adapters.character.CharacterDtoToEntityMapper;
import de.dnd.stiki.adapters.character.CharacterEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterCreation.CharacterCreationDto;
import de.dnd.stiki.adapters.character.characterCreation.CharacterCreationDtoToEntityMapper;
import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.domain.background.BackgroundRepository;
import de.dnd.stiki.domain.character.CharacterAbilityEntity;
import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.character.CharacterRepository;
import de.dnd.stiki.domain.character.CharacterSkillEntity;
import de.dnd.stiki.domain.dndClass.DndClassEntity;
import de.dnd.stiki.domain.dndClass.DndClassRepository;
import de.dnd.stiki.domain.dndClass.classLevel.ClassLevelEntity;
import de.dnd.stiki.domain.dndClass.feature.FeatureEntity;
import de.dnd.stiki.domain.enums.AbilityType;
import de.dnd.stiki.domain.enums.SkillType;
import de.dnd.stiki.domain.race.RaceEntity;
import de.dnd.stiki.domain.race.RaceRepository;
import de.dnd.stiki.domain.trait.TraitEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

import static de.dnd.stiki.domain.enums.AbilityType.*;

@Service
public class CharacterService {

    @Autowired
    private CharacterRepository repository;

    @Autowired
    private CharacterEntityToDtoMapper entityToDtoMapper;

    @Autowired
    private CharacterDtoToEntityMapper dtoToEntityMapper;

    @Autowired
    private CharacterCreationDtoToEntityMapper creationDtoToEntityMapper;

    @Autowired
    private DndClassRepository dndClassRepository;

    @Autowired
    private BackgroundRepository backgroundRepository;

    @Autowired
    private RaceRepository raceRepository;

    public List<CharacterDto> getAll() {
        return entityToDtoMapper.mapEntitiesToDtos(repository.getAll());
    }

    public CharacterDto get(Long id) {
        return entityToDtoMapper.mapEntityToDto(repository.get(id));
    }

    public CharacterDto create(CharacterCreationDto creationDto) {

        CharacterEntity characterEntity = creationDtoToEntityMapper.mapDtoToEntity(creationDto);
        characterEntity.setLevel(1);
        characterEntity.setMaxHitDice(1);
        characterEntity.setCurrentHitDice(1);
        characterEntity.setArmorClass(10 + characterEntity.getAbility(DEXTERITY).getModifier());
        characterEntity.setProficiencyBonus(2);

        List<CharacterSkillEntity> skills = new ArrayList<>();
        for (CharacterAbilityEntity ability : characterEntity.getAbilities()) {
            List<SkillType> skillTypes = SkillType.fromAbility(ability.getName().toString());
            for (SkillType skillType : skillTypes) {
                CharacterSkillEntity skill = new CharacterSkillEntity();
                skill.setName(skillType);
                skill.setAbility(ability.getName());
                skill.setBasicModifier(ability.getModifier());

                if (creationDto.getSkillProficiencies() != null && creationDto.getSkillProficiencies().contains(skillType.getName())) {
                    skill.setProficiency(1);
                }

                skills.add(skill);
            }
        }
        characterEntity.setSkills(skills);
        characterEntity.setPassivePerception(10 + characterEntity.getSkill(SkillType.PERCEPTION).getModifierWithProficiency(characterEntity.getProficiencyBonus()));

        DndClassEntity dndClass = dndClassRepository.getByName(characterEntity.getDndClass());
        if (dndClass != null) {
            characterEntity.setHitDice(dndClass.getHitDice());

            int health = characterEntity.getHitDice()+ characterEntity.getAbility(CONSTITUTION).getModifier();
            characterEntity.setMaxHealth(health);
            characterEntity.setCurrentHealth(health);
            characterEntity.setClassFeatures(getClassFeatures(dndClass));

            for (CharacterAbilityEntity ability : characterEntity.getAbilities()) {
                for (String classSavingThrowProficiency : dndClass.getSavingThrowProficiencies()) {
                    if (ability.getName() == AbilityType.fromName(classSavingThrowProficiency)) {
                        ability.setSavingThrowProficiency(1);
                    }
                }
            }
        }


        BackgroundEntity background = backgroundRepository.getByName(characterEntity.getBackground());
        if (background != null) {
            characterEntity.setBackgroundTraits(background.getTraits());
        }

        RaceEntity race = raceRepository.getByName(characterEntity.getRace());
        if (race != null) {
            characterEntity.setSpeed(race.getSpeed());
            characterEntity.setRaceTraits(race.getTraits());
        }

        CharacterEntity entity = repository.save(characterEntity);
        return entityToDtoMapper.mapEntityToDto(entity);
    }

    public CharacterDto save(CharacterDto characterDto) {
        CharacterEntity entity = repository.save(dtoToEntityMapper.mapDtoToEntity(characterDto));
        return entityToDtoMapper.mapEntityToDto(entity);
    }

    public void delete(Long id) {
        repository.delete(id);
    }

    private List<TraitEntity> getClassFeatures(DndClassEntity dndClass) {

        List<TraitEntity> classFeatures = new ArrayList<>();

        if (dndClass.getTraits() != null) {
            classFeatures.addAll(dndClass.getTraits());
        }

        for (ClassLevelEntity level : dndClass.getClassLevels()) {
            if (level.getFeatures() != null) {
                for (FeatureEntity feature : level.getFeatures()) {
                    TraitEntity trait = new TraitEntity();
                    trait.setName(feature.getName());
                    trait.setText(feature.getText());
                    classFeatures.add(trait);
                }
            }
        }

        return classFeatures;
    }

}
