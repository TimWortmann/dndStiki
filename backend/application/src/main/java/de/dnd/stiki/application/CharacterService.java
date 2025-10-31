package de.dnd.stiki.application;

import de.dnd.stiki.adapters.character.CharacterDto;
import de.dnd.stiki.adapters.character.CharacterEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityDto;
import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityDtoToEntityMapper;
import de.dnd.stiki.adapters.character.characterCreation.CharacterCreationDto;
import de.dnd.stiki.adapters.character.characterCreation.CharacterCreationDtoToEntityMapper;
import de.dnd.stiki.adapters.character.characterItem.CharacterItemDto;
import de.dnd.stiki.adapters.character.characterItem.CharacterItemDtoToEntityMapper;
import de.dnd.stiki.adapters.character.characterItem.CharacterItemEntityToDtoMapper;
import de.dnd.stiki.adapters.character.characterSkill.CharacterSkillDto;
import de.dnd.stiki.adapters.character.characterSkill.CharacterSkillDtoToEntityMapper;
import de.dnd.stiki.adapters.feat.FeatDto;
import de.dnd.stiki.adapters.item.ItemDto;
import de.dnd.stiki.adapters.item.ItemDtoToEnityMapper;
import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.domain.background.BackgroundRepository;
import de.dnd.stiki.domain.character.*;
import de.dnd.stiki.domain.dndClass.DndClassEntity;
import de.dnd.stiki.domain.dndClass.DndClassRepository;
import de.dnd.stiki.domain.dndClass.classLevel.ClassLevelEntity;
import de.dnd.stiki.domain.dndClass.feature.FeatureEntity;
import de.dnd.stiki.domain.enums.AbilityType;
import de.dnd.stiki.domain.enums.SkillType;
import de.dnd.stiki.domain.item.ItemEntity;
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

    private static final String TWO_HANDED = " (Two Handed)";
    private static final String FINESSE = " (Finesse)";

    @Autowired
    private CharacterRepository repository;

    @Autowired
    private CharacterEntityToDtoMapper entityToDtoMapper;

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

    @Autowired
    private ItemDtoToEnityMapper itemDtoToEnityMapper;

    @Autowired
    private CharacterItemDtoToEntityMapper characterItemDtoToEntityMapper;

    @Autowired
    private CharacterItemEntityToDtoMapper characterItemEntityToDtoMapper;

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
        characterEntity.setSpellcastingAbility(AbilityType.fromName(dndClass.getSpellAbility()));
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

        characterEntity.setWeaponProficiencies(dndClass.getWeaponProficiencies());
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

        return getBeginnerHealth(characterEntity) + ((characterEntity.getLevel()-1) * healthIncreasePerLevel);
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

        if (characterEntity.getCurrentHealth() != null && characterEntity.getCurrentHealth().equals(characterEntity.getMaxHealth())) {
            characterEntity.setCurrentHealth(maxHealth);
        }

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
        if (characterEntity.getCurrentHitDice() != null && characterEntity.getCurrentHitDice().equals(characterEntity.getMaxHitDice())) {
            characterEntity.setCurrentHitDice(maxHitDice);
        }

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

    public CharacterDto addItem(Long id, ItemDto itemDto) {
        CharacterEntity characterEntity = repository.get(id);

        ItemEntity item = itemDtoToEnityMapper.mapDtoToEntity(itemDto);

        if (characterEntity.getItems() == null) {
            characterEntity.setItems(new ArrayList<>());
        }

        CharacterItemEntity itemInInventory = getCharacterItemByName(characterEntity.getItems(), item.getName());

        if (itemInInventory != null) {
            itemInInventory.setQuantity(itemInInventory.getQuantity() + 1);
        }
        else {
            characterEntity.getItems().add(getCharacterItemFromItem(item));
        }

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    private CharacterItemEntity getCharacterItemByName(List<CharacterItemEntity> characterItems, String name) {
        for (CharacterItemEntity characterItem : characterItems) {
            if (characterItem.getName().equals(name)) {
                return characterItem;
            }
        }
        return null;
    }

    private CharacterItemEntity getCharacterItemFromItem(ItemEntity itemEntity) {
        CharacterItemEntity characterItem = new CharacterItemEntity();
        characterItem.setName(itemEntity.getName());
        characterItem.setDetail(itemEntity.getDetail());
        characterItem.setType(itemEntity.getType());
        characterItem.setWeight(itemEntity.getWeight());
        characterItem.setValue(itemEntity.getValue());
        characterItem.setProperties(itemEntity.getProperties());
        characterItem.setDmg1(itemEntity.getDmg1());
        characterItem.setDmg2(itemEntity.getDmg2());
        characterItem.setDmgType(itemEntity.getDmgType());
        characterItem.setRange(itemEntity.getRange());
        characterItem.setAc(itemEntity.getAc());
        characterItem.setStealth(itemEntity.getStealth());
        characterItem.setMagic(itemEntity.getMagic());
        characterItem.setStrength(itemEntity.getStrength());
        characterItem.setText(itemEntity.getText());
        characterItem.setQuantity(1);
        return characterItem;
    }

    public CharacterDto changeItemQuantity(Long characterId, String itemName, Integer quantity) {
        CharacterEntity characterEntity = repository.get(characterId);

        CharacterItemEntity characterItem = getCharacterItemByName(characterEntity.getItems(), itemName);

        if (characterItem != null) {
            if (quantity < 1) {
                characterEntity.getItems().remove(characterItem);

                if (characterEntity.getEquippedShield() != null && characterEntity.getEquippedShield().getName().equals(itemName)) {
                    characterEntity.setEquippedShield(null);
                }
                else if (characterEntity.getEquippedArmor() != null && characterEntity.getEquippedArmor().getName().equals(itemName)) {
                    characterEntity.setEquippedArmor(null);
                }

            } else {
                characterItem.setQuantity(quantity);
            }
        }

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    public CharacterDto equipShield(Long id, CharacterItemDto shieldItem) {
        CharacterEntity characterEntity = repository.get(id);

        if (shieldItem != null) {
            CharacterItemEntity shieldItemEntity = characterItemDtoToEntityMapper.mapDtoToEntity(shieldItem);
            equipShieldEntity(shieldItemEntity, characterEntity);
        } else {
            characterEntity.setEquippedShield(null);
        }
        characterEntity.setModifiedArmorClass(null);

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    private void equipShieldEntity(CharacterItemEntity shieldItem, CharacterEntity characterEntity) {
        CharacterShieldEntity shield = new CharacterShieldEntity();
        shield.setName(shieldItem.getName());
        shield.setAc(shieldItem.getAc());
        characterEntity.setEquippedShield(shield);
    }

    public CharacterDto equipArmor(Long id, CharacterItemDto armorItem) {
        CharacterEntity characterEntity = repository.get(id);

        if (armorItem != null) {
            CharacterItemEntity armorItemEntity = characterItemDtoToEntityMapper.mapDtoToEntity(armorItem);
            equipArmorEntity(armorItemEntity, characterEntity);
        } else {
            characterEntity.setEquippedArmor(null);
        }
        characterEntity.setModifiedArmorClass(null);

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    private void equipArmorEntity(CharacterItemEntity armorItem, CharacterEntity characterEntity) {
        CharacterArmorEntity armor = new CharacterArmorEntity();
        armor.setName(armorItem.getName());
        armor.setAc(armorItem.getAc());
        armor.setType(armorItem.getType());
        characterEntity.setEquippedArmor(armor);
    }

    public CharacterDto changeArmorClass(Long id, Integer armorClass) {
        CharacterEntity characterEntity = repository.get(id);
        characterEntity.setModifiedArmorClass(armorClass);
        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    public CharacterDto equipWeapon(Long id, CharacterItemDto weaponItem) {
        CharacterEntity characterEntity = repository.get(id);

        ItemEntity weaponItemEntity = itemDtoToEnityMapper.mapDtoToEntity(weaponItem);

        if (characterEntity.getAttacks() == null) {
            characterEntity.setAttacks(new ArrayList<>());
        }

        CharacterAttackEntity attackEntity = createCharacterAttackEntity(weaponItemEntity, characterEntity, weaponItemEntity.getDmg1(), STRENGTH);
        characterEntity.getAttacks().add(attackEntity);

        if (weaponItemEntity.getDmg2() != null) {
            CharacterAttackEntity attackEntity2 = createCharacterAttackEntity(weaponItemEntity, characterEntity, weaponItemEntity.getDmg2(), STRENGTH);
            attackEntity2.setName(attackEntity2.getName() + TWO_HANDED);
            characterEntity.getAttacks().add(attackEntity2);
        }

        for (String weaponProperty : weaponItemEntity.getProperties()) {
            if (weaponProperty.equalsIgnoreCase("finesse")) {
                CharacterAttackEntity attackEntity3 = createCharacterAttackEntity(weaponItemEntity, characterEntity, weaponItemEntity.getDmg1(), DEXTERITY);
                attackEntity3.setName(attackEntity3.getName() + FINESSE);
                characterEntity.getAttacks().add(attackEntity3);
            }
        }

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    private CharacterAttackEntity createCharacterAttackEntity(ItemEntity weaponItemEntity, CharacterEntity characterEntity, String baseDamage, AbilityType ability) {
        CharacterAttackEntity attackEntity = new CharacterAttackEntity();
        attackEntity.setName(weaponItemEntity.getName());
        attackEntity.setBaseDamageRoll(baseDamage);
        attackEntity.setAbility(ability);

        attackEntity.setProficient(false);
        for (String weaponProficiency : characterEntity.getWeaponProficiencies()) {
            for (String weaponProperty : weaponItemEntity.getProperties()) {
                if (weaponProficiency.toLowerCase().contains(weaponProperty.toLowerCase())) {
                    attackEntity.setProficient(true);
                }
            }
        }
        return attackEntity;
    }

    public CharacterDto unequipWeapon (Long id, String weaponName) {
        CharacterEntity characterEntity = repository.get(id);

        characterEntity.getAttacks().removeIf(attack -> attack.getName().equals(weaponName)
                || attack.getName().equals(weaponName + TWO_HANDED)
                || attack.getName().equals(weaponName + FINESSE));

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }

    public CharacterDto removeAttack(Long id, String attackName) {
        CharacterEntity characterEntity = repository.get(id);

        characterEntity.getAttacks().removeIf(attack -> attack.getName().equals(attackName));

        return entityToDtoMapper.mapEntityToDto(repository.save(characterEntity));
    }
}
