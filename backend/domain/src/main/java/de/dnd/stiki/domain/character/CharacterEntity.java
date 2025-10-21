package de.dnd.stiki.domain.character;

import de.dnd.stiki.domain.enums.AbilityType;
import de.dnd.stiki.domain.enums.SkillType;
import de.dnd.stiki.domain.trait.TraitEntity;

import java.util.ArrayList;
import java.util.List;

public class CharacterEntity {

    private Long id;

    private String name;

    private Integer level;

    private String dndClass;

    private String dndSubclass;

    private AbilityType spellcastingAbility;

    private List<String> dndSubclasses;

    private String background;

    private String race;

    private Integer maxHealth;

    private Integer currentHealth;

    private Integer hitDice;

    private Integer maxHitDice;

    private Integer currentHitDice;

    private Integer armorClass;

    private Integer speed;

    private Integer passivePerception;

    private Integer proficiencyBonus;

    private List<CharacterAbilityEntity> abilities;
    private List<CharacterSkillEntity> skills;

    private List<TraitEntity> classFeatures;
    private List<TraitEntity> backgroundTraits;
    private List<TraitEntity> raceTraits;
    private List<TraitEntity> feats;

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

    public AbilityType getSpellcastingAbility() {
        return spellcastingAbility;
    }

    public void setSpellcastingAbility(AbilityType spellcastingAbility) {
        this.spellcastingAbility = spellcastingAbility;
    }

    public List<String> getDndSubclasses() {
        return dndSubclasses;
    }

    public void setDndSubclasses(List<String> dndSubclasses) {
        this.dndSubclasses = dndSubclasses;
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

    public Integer getArmorClass() {
        return armorClass;
    }

    public void setArmorClass(Integer armorClass) {
        this.armorClass = armorClass;
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

    public List<CharacterAbilityEntity> getAbilities() {
        return abilities;
    }

    public CharacterAbilityEntity getAbility(AbilityType abilityType) {
        if (abilities == null) {
            return null;
        }

        for (CharacterAbilityEntity ability : abilities) {
            if (ability.getName() == abilityType) {
                return ability;
            }
        }
        return null;
    }

    public void setAbilities(List<CharacterAbilityEntity> abilities) {
        this.abilities = abilities;
    }

    public List<CharacterSkillEntity> getSkills() {
        return skills;
    }

    public CharacterSkillEntity getSkill(SkillType skillType) {
        for (CharacterSkillEntity skill : skills) {
            if (skill.getName() == skillType) {
                return skill;
            }
        }
        return null;
    }

    public List<CharacterSkillEntity> getSkillsByAbility(AbilityType abilityType) {
        List<CharacterSkillEntity> skillEntities = new ArrayList<>();
        for (CharacterSkillEntity skill : skills) {
            if (skill.getAbility() == abilityType) {
                skillEntities.add(skill);
            }
        }
        return skillEntities;
    }

    public void setSkills(List<CharacterSkillEntity> skills) {
        this.skills = skills;
    }

    public List<TraitEntity> getClassFeatures() {
        return classFeatures;
    }

    public void setClassFeatures(List<TraitEntity> classFeatures) {
        this.classFeatures = classFeatures;
    }

    public List<TraitEntity> getBackgroundTraits() {
        return backgroundTraits;
    }

    public void setBackgroundTraits(List<TraitEntity> backgroundTraits) {
        this.backgroundTraits = backgroundTraits;
    }

    public List<TraitEntity> getRaceTraits() {
        return raceTraits;
    }

    public void setRaceTraits(List<TraitEntity> raceTraits) {
        this.raceTraits = raceTraits;
    }

    public List<TraitEntity> getFeats() {
        return feats;
    }

    public void setFeats(List<TraitEntity> feats) {
        this.feats = feats;
    }
}
