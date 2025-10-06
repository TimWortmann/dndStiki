package de.dnd.stiki.domain.dndClass;

import de.dnd.stiki.domain.dndClass.classLevel.ClassLevelEntity;
import de.dnd.stiki.domain.trait.TraitEntity;

import java.util.List;

public class DndClassEntity {

    private String name;

    private Integer hitDice;

    private List<String> savingThrowProficiencies;

    private  List<String> skillProficiencies;

    private Integer numberOfSkillProficiencies;

    private List<String> armorProficiencies;

    private List<String> weaponProficiencies;

    private List<String> toolProficiencies;

    private String wealth;

    private String spellAbility;

    private List <ClassLevelEntity> classLevels;

    private List<TraitEntity> traits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getHitDice() {
        return hitDice;
    }

    public void setHitDice(Integer hitDice) {
        this.hitDice = hitDice;
    }

    public List<String> getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    public void setSavingThrowProficiencies(List<String> savingThrowProficiencies) {
        this.savingThrowProficiencies = savingThrowProficiencies;
    }

    public List<String> getSkillProficiencies() {
        return skillProficiencies;
    }

    public void setSkillProficiencies(List<String> skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }

    public Integer getNumberOfSkillProficiencies() {
        return numberOfSkillProficiencies;
    }

    public void setNumberOfSkillProficiencies(Integer numberOfSkillProficiencies) {
        this.numberOfSkillProficiencies = numberOfSkillProficiencies;
    }

    public List<String> getArmorProficiencies() {
        return armorProficiencies;
    }

    public void setArmorProficiencies(List<String> armorProficiencies) {
        this.armorProficiencies = armorProficiencies;
    }

    public List<String> getWeaponProficiencies() {
        return weaponProficiencies;
    }

    public void setWeaponProficiencies(List<String> weaponProficiencies) {
        this.weaponProficiencies = weaponProficiencies;
    }

    public List<String> getToolProficiencies() {
        return toolProficiencies;
    }

    public void setToolProficiencies(List<String> toolProficiencies) {
        this.toolProficiencies = toolProficiencies;
    }

    public String getWealth() {
        return wealth;
    }

    public void setWealth(String wealth) {
        this.wealth = wealth;
    }

    public String getSpellAbility() {
        return spellAbility;
    }

    public void setSpellAbility(String spellAbility) {
        this.spellAbility = spellAbility;
    }

    public List<ClassLevelEntity> getClassLevels() {
        return classLevels;
    }

    public void setClassLevels(List<ClassLevelEntity> classLevels) {
        this.classLevels = classLevels;
    }

    public List<TraitEntity> getTraits() {
        return traits;
    }

    public void setTraits(List<TraitEntity> traits) {
        this.traits = traits;
    }
}
