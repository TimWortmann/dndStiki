package de.dnd.stiki.adapters.dndClass;

import de.dnd.stiki.adapters.dndClass.classLevel.ClassLevelDto;
import de.dnd.stiki.adapters.trait.TraitDto;

import java.util.List;

public class DndClassDto {

    private String name;

    private List<String> subclasses;

    private Integer hitDice;

    private List<String> savingThrowProficiencies;

    private  List<String> skillProficiencies;

    private Integer numberOfSkillProficiencies;

    private List<String> armorProficiencies;

    private List<String> weaponProficiencies;

    private List<String> toolProficiencies;

    private String wealth;

    private String spellAbility;

    private List <ClassLevelDto> classLevels;

    private List <TraitDto> traits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSubclasses() {
        return subclasses;
    }

    public void setSubclasses(List<String> subclasses) {
        this.subclasses = subclasses;
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

    public List<ClassLevelDto> getClassLevels() {
        return classLevels;
    }

    public void setClassLevels(List<ClassLevelDto> classLevels) {
        this.classLevels = classLevels;
    }

    public List<TraitDto> getTraits() {
        return traits;
    }

    public void setTraits(List<TraitDto> traits) {
        this.traits = traits;
    }
}
