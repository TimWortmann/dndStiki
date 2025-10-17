package de.dnd.stiki.application;

import de.dnd.stiki.adapters.character.CharacterDto;
import de.dnd.stiki.adapters.character.CharacterDtoToEntityMapper;
import de.dnd.stiki.adapters.character.CharacterEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityDto;
import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityDtoToEntityMapper;
import de.dnd.stiki.adapters.character.characterCreation.CharacterCreationDto;
import de.dnd.stiki.adapters.character.characterCreation.CharacterCreationDtoToEntityMapper;
import de.dnd.stiki.adapters.character.characterSkill.CharacterSkillDto;
import de.dnd.stiki.adapters.character.characterSkill.CharacterSkillDtoToEntityMapper;
import de.dnd.stiki.adapters.feat.FeatDto;
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

import static de.dnd.stiki.domain.enums.AbilityType.CONSTITUTION;
import static de.dnd.stiki.domain.enums.AbilityType.DEXTERITY;

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

    @Autowired
    private CharacterAbilityDtoToEntityMapper abilityDtoToEntityMapper;

    @Autowired
    private CharacterSkillDtoToEntityMapper skillDtoToEntityMapper;

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
        characterEntity.setProficiencyBonus(getProficiencyBonus(1));

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
            setDndClass(characterEntity,dndClass);
        }

        BackgroundEntity background = backgroundRepository.getByName(characterEntity.getBackground());
        if (background != null) {
            setBackground(characterEntity, background);
        }

        RaceEntity race = raceRepository.getByName(characterEntity.getRace());
        if (race != null) {
            setRace(characterEntity, race);
        }

        CharacterEntity entity = repository.save(characterEntity);
        return entityToDtoMapper.mapEntityToDto(entity);
    }

    private void setRace(CharacterEntity characterEntity, RaceEntity race) {
        characterEntity.setRace(race.getName());
        characterEntity.setSpeed(race.getSpeed());
        characterEntity.setRaceTraits(race.getTraits());
    }

    private void setBackground(CharacterEntity characterEntity, BackgroundEntity background) {
        characterEntity.setBackground(background.getName());
        characterEntity.setBackgroundTraits(background.getTraits());
    }

    private void setDndClass(CharacterEntity characterEntity, DndClassEntity dndClass) {
        characterEntity.setDndClass(dndClass.getName());
        characterEntity.setDndSubclass("No Subclass");
        characterEntity.setHitDice(dndClass.getHitDice());

        setMaxHealth(characterEntity, getLevelHealth(characterEntity));
        characterEntity.setClassFeatures(getClassFeatures(dndClass));

        for (CharacterAbilityEntity ability : characterEntity.getAbilities()) {
            for (String classSavingThrowProficiency : dndClass.getSavingThrowProficiencies()) {
                if (ability.getName() == AbilityType.fromName(classSavingThrowProficiency)) {
                    ability.setSavingThrowProficiency(1);
                }
            }
        }
    }

    public CharacterDto changeName (Long id, String name) {
        CharacterEntity characterEntity = repository.get(id);
        characterEntity.setName(name);
        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
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

    private Integer getBeginnerHealth(CharacterEntity characterEntity) {
        return characterEntity.getHitDice()+ characterEntity.getAbility(CONSTITUTION).getModifier();
    }

    public CharacterDto changeLevel(Long id, Integer level) {
        CharacterEntity characterEntity = repository.get(id);

        characterEntity.setLevel(level);

        if (level < 3) {
            characterEntity.setDndSubclass("No Subclass");
        }

        setMaxHitDice(characterEntity, level);
        setMaxHealth(characterEntity, getLevelHealth(characterEntity));

        characterEntity.setProficiencyBonus(getProficiencyBonus(level));

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    private Integer getLevelHealth(CharacterEntity characterEntity) {
        int fixedHitpoints = (characterEntity.getHitDice() + 1) / 2;

        int healthIncreasePerLevel = fixedHitpoints + characterEntity.getAbility(CONSTITUTION).getModifier();

        return getBeginnerHealth(characterEntity) + (characterEntity.getLevel() * healthIncreasePerLevel);
    }

    private Integer getProficiencyBonus(Integer level) {
        return (int) Math.ceil(level / 4.0) + 1;
    }

    public CharacterDto changeDndClass(Long id, String dndClassName) {
        CharacterEntity characterEntity = repository.get(id);

        DndClassEntity dndClass = dndClassRepository.getByName(dndClassName);
        if (dndClass != null) {
            for (CharacterAbilityEntity ability : characterEntity.getAbilities()) {
                ability.setSavingThrowProficiency(0);
            }
            setDndClass(characterEntity, dndClass);
        }

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    public CharacterDto changeSubclass(Long id, String subclass) {
        CharacterEntity characterEntity = repository.get(id);

        if(characterEntity.getLevel() >= 3) {
            characterEntity.setDndSubclass(subclass);
        }

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    public CharacterDto changeBackground(Long id, String backgroundName) {
        CharacterEntity characterEntity = repository.get(id);

        BackgroundEntity background = backgroundRepository.getByName(backgroundName);
        if (background != null) {
            setBackground(characterEntity, background);
        }

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    public CharacterDto changeRace(Long id, String raceName) {
        CharacterEntity characterEntity = repository.get(id);

        RaceEntity race = raceRepository.getByName(raceName);
        if (race != null) {
            setRace(characterEntity, race);
        }

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    public CharacterDto changeCurrentHealth(Long id, Integer currentHealth) {
        CharacterEntity characterEntity = repository.get(id);
        characterEntity.setCurrentHealth(currentHealth);
        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    public CharacterDto changeMaxHealth(Long id, Integer maxHealth) {
        CharacterEntity characterEntity = repository.get(id);
        setMaxHealth(characterEntity, maxHealth);
        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    private void setMaxHealth(CharacterEntity characterEntity, Integer maxHealth) {
        characterEntity.setMaxHealth(maxHealth);

        if (characterEntity.getCurrentHealth() == null || characterEntity.getCurrentHealth() > characterEntity.getMaxHealth()) {
            characterEntity.setCurrentHealth(characterEntity.getMaxHealth());
        }
    }

    public CharacterDto changeCurrentHitDice(Long id, Integer currentHitDice) {
        CharacterEntity characterEntity = repository.get(id);
        characterEntity.setCurrentHitDice(currentHitDice);
        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    public CharacterDto changeMaxHitDice(Long id, Integer maxHitDice) {
        CharacterEntity characterEntity = repository.get(id);
        setMaxHitDice(characterEntity, maxHitDice);
        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    private void setMaxHitDice(CharacterEntity characterEntity, Integer maxHitDice) {
        characterEntity.setMaxHitDice(maxHitDice);

        if (characterEntity.getCurrentHitDice() == null || characterEntity.getCurrentHitDice() > characterEntity.getMaxHitDice()) {
            characterEntity.setCurrentHitDice(characterEntity.getMaxHitDice());
        }
    }

    public CharacterDto changeAbilities(Long id, List<CharacterAbilityDto> abilities) {
        CharacterEntity characterEntity = repository.get(id);

        List<CharacterAbilityEntity> abilityEntities = abilityDtoToEntityMapper.mapDtosToEntities(abilities);
        keepSavingThrowProficiencies(characterEntity, abilityEntities);
        characterEntity.setAbilities(abilityEntities);

        characterEntity.setArmorClass(10 + characterEntity.getAbility(DEXTERITY).getModifier());
        setMaxHealth(characterEntity, getLevelHealth(characterEntity));

        updateSkillBasicModifiers(abilityEntities, characterEntity);
        characterEntity.setPassivePerception(10 + characterEntity.getSkill(SkillType.PERCEPTION).getModifierWithProficiency(characterEntity.getProficiencyBonus()));

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    private static void updateSkillBasicModifiers(List<CharacterAbilityEntity> abilityEntities, CharacterEntity characterEntity) {
        for (CharacterAbilityEntity ability : abilityEntities) {
            for (CharacterSkillEntity skill : characterEntity.getSkills()) {
                if (ability.getName().equals(skill.getAbility())) {
                    skill.setBasicModifier(ability.getModifier());
                }
            }
        }
    }

    private static void keepSavingThrowProficiencies(CharacterEntity characterEntity, List<CharacterAbilityEntity> abilityEntities) {
        for (CharacterAbilityEntity oldAbility : characterEntity.getAbilities()) {
            for (CharacterAbilityEntity newAbility : abilityEntities) {
                if (oldAbility.getName().equals(newAbility.getName())) {
                    newAbility.setSavingThrowProficiency(oldAbility.getSavingThrowProficiency());
                }
            }
        }
    }

    public CharacterDto changeSkillProficiencies(Long id, List<CharacterSkillDto> skillDtos) {
        CharacterEntity characterEntity = repository.get(id);

        List<CharacterSkillEntity> skillEntities = skillDtoToEntityMapper.mapDtosToEntities(skillDtos);

        for (CharacterSkillEntity oldSkill : characterEntity.getSkills()) {
            for (CharacterSkillEntity newSkill : skillEntities) {
                if (oldSkill.getName().equals(newSkill.getName())) {
                    oldSkill.setProficiency(newSkill.getProficiency());
                }
            }
        }
        characterEntity.setPassivePerception(10 + characterEntity.getSkill(SkillType.PERCEPTION).getModifierWithProficiency(characterEntity.getProficiencyBonus()));

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    public CharacterDto addFeat(Long id, FeatDto featDto) {
        CharacterEntity characterEntity = repository.get(id);

        TraitEntity trait = new TraitEntity();
        trait.setName(featDto.getName());

        String text = featDto.getText();
        if (featDto.getPrerequisites() != null && !featDto.getPrerequisites().isEmpty()) {
            String joinedPrerequisites = String.join(", ", featDto.getPrerequisites());
            text =  "[Prerequisites: " + joinedPrerequisites + "]\n\n" + text;
        }
        trait.setText(text);

        characterEntity.getFeats().add(trait);

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    public CharacterDto removeFeat(Long id, String featName) {
        CharacterEntity characterEntity = repository.get(id);

        characterEntity.getFeats().removeIf(trait -> trait.getName().equals(featName));

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }
}
