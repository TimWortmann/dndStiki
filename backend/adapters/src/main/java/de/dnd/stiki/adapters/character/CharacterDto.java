package de.dnd.stiki.adapters.character;

import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityDto;
import de.dnd.stiki.adapters.character.characterArmor.CharacterArmorDto;
import de.dnd.stiki.adapters.character.characterAttack.CharacterAttackDto;
import de.dnd.stiki.adapters.character.characterItem.CharacterItemDto;
import de.dnd.stiki.adapters.character.characterShield.CharacterShieldDto;
import de.dnd.stiki.adapters.character.characterSkill.CharacterSkillDto;
import de.dnd.stiki.adapters.trait.TraitDto;

import java.util.List;

public class CharacterDto {

    private Long id;

    private String name;

    private Integer level;

    private String dndClass;

    private String dndSubclass;

    private String spellcastingAbility;

    private List<String> dndSubclasses;

    private String background;

    private String race;

    private Integer maxHealth;

    private Integer currentHealth;

    private Integer hitDice;

    private Integer maxHitDice;

    private Integer currentHitDice;

    private Integer basicArmorClass;

    private Integer realArmorClass;

    private boolean armorClassIsModified;

    private Integer speed;

    private Integer passivePerception;

    private Integer proficiencyBonus;

    private List<CharacterAbilityDto> abilities;

    private List<CharacterSkillDto> skills;

    private List<CharacterItemDto> items;
    private List<CharacterItemDto> equipment;

    private List<TraitDto> classFeatures;
    private List<TraitDto> backgroundTraits;
    private List<TraitDto> raceTraits;
    private List<TraitDto> feats;

    private CharacterShieldDto equippedShield;
    private CharacterArmorDto equippedArmor;

    private List<String> weaponProficiencies;
    private List<CharacterAttackDto> attacks;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getDndClass() {
        return dndClass;
    }

    public void setDndClass(String dndClass) {
        this.dndClass = dndClass;
    }

    public String getDndSubclass() {
        return dndSubclass;
    }

    public void setDndSubclass(String dndSubclass) {
        this.dndSubclass = dndSubclass;
    }

    public List<String> getDndSubclasses() {
        return dndSubclasses;
    }

    public void setDndSubclasses(List<String> dndSubclasses) {
        this.dndSubclasses = dndSubclasses;
    }

    public String getSpellcastingAbility() {
        return spellcastingAbility;
    }

    public void setSpellcastingAbility(String spellcastingAbility) {
        this.spellcastingAbility = spellcastingAbility;
    }

    public String getBackground() {
        return background;
    }

    public void setBackground(String background) {
        this.background = background;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public Integer getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(Integer maxHealth) {
        this.maxHealth = maxHealth;
    }

    public Integer getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(Integer currentHealth) {
        this.currentHealth = currentHealth;
    }

    public Integer getHitDice() {
        return hitDice;
    }

    public void setHitDice(Integer hitDice) {
        this.hitDice = hitDice;
    }

    public Integer getMaxHitDice() {
        return maxHitDice;
    }

    public void setMaxHitDice(Integer maxHitDice) {
        this.maxHitDice = maxHitDice;
    }

    public Integer getCurrentHitDice() {
        return currentHitDice;
    }

    public void setCurrentHitDice(Integer currentHitDice) {
        this.currentHitDice = currentHitDice;
    }

    public Integer getBasicArmorClass() {
        return basicArmorClass;
    }

    public void setBasicArmorClass(Integer basicArmorClass) {
        this.basicArmorClass = basicArmorClass;
    }

    public Integer getRealArmorClass() {
        return realArmorClass;
    }

    public void setRealArmorClass(Integer realArmorClass) {
        this.realArmorClass = realArmorClass;
    }

    public boolean isArmorClassIsModified() {
        return armorClassIsModified;
    }

    public void setArmorClassIsModified(boolean armorClassIsModified) {
        this.armorClassIsModified = armorClassIsModified;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public Integer getPassivePerception() {
        return passivePerception;
    }

    public void setPassivePerception(Integer passivePerception) {
        this.passivePerception = passivePerception;
    }

    public Integer getProficiencyBonus() {
        return proficiencyBonus;
    }

    public void setProficiencyBonus(Integer proficiencyBonus) {
        this.proficiencyBonus = proficiencyBonus;
    }

    public List<CharacterAbilityDto> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<CharacterAbilityDto> abilities) {
        this.abilities = abilities;
    }

    public List<CharacterSkillDto> getSkills() {
        return skills;
    }

    public void setSkills(List<CharacterSkillDto> skills) {
        this.skills = skills;
    }

    public List<CharacterItemDto> getItems() {
        return items;
    }

    public void setItems(List<CharacterItemDto> items) {
        this.items = items;
    }

    public List<CharacterItemDto> getEquipment() {
        return equipment;
    }

    public void setEquipment(List<CharacterItemDto> equipment) {
        this.equipment = equipment;
    }

    public List<TraitDto> getClassFeatures() {
        return classFeatures;
    }

    public void setClassFeatures(List<TraitDto> classFeatures) {
        this.classFeatures = classFeatures;
    }

    public List<TraitDto> getBackgroundTraits() {
        return backgroundTraits;
    }

    public void setBackgroundTraits(List<TraitDto> backgroundTraits) {
        this.backgroundTraits = backgroundTraits;
    }

    public List<TraitDto> getRaceTraits() {
        return raceTraits;
    }

    public void setRaceTraits(List<TraitDto> raceTraits) {
        this.raceTraits = raceTraits;
    }

    public List<TraitDto> getFeats() {
        return feats;
    }

    public void setFeats(List<TraitDto> feats) {
        this.feats = feats;
    }

    public CharacterShieldDto getEquippedShield() {
        return equippedShield;
    }

    public void setEquippedShield(CharacterShieldDto equippedShield) {
        this.equippedShield = equippedShield;
    }

    public CharacterArmorDto getEquippedArmor() {
        return equippedArmor;
    }

    public void setEquippedArmor(CharacterArmorDto equippedArmor) {
        this.equippedArmor = equippedArmor;
    }

    public List<String> getWeaponProficiencies() {
        return weaponProficiencies;
    }

    public void setWeaponProficiencies(List<String> weaponProficiencies) {
        this.weaponProficiencies = weaponProficiencies;
    }

    public List<CharacterAttackDto> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<CharacterAttackDto> attacks) {
        this.attacks = attacks;
    }
}
