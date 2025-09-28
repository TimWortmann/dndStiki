package de.dnd.stiki.plugins.persistence.characterClass;

import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpa;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitJpa;
import de.dnd.stiki.plugins.persistence.characterClass.classLevel.ClassLevelJpa;
import de.dnd.stiki.plugins.persistence.basic.skill.SkillJpa;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CHARACTER_CLASS", schema = "DND_STIKI")
public class CharacterClassJpa {

    @Id
    @Column(name = "NAME")
    private String name;

    @Column(name = "HIT_DICE")
    private Integer hitDice;

    @ManyToMany
    @JoinTable(
            name = "CLASS_ABILITY",
            schema = "DND_STIKI",
            joinColumns = @JoinColumn(name = "CHARACTER_CLASS_NAME"),
            inverseJoinColumns = @JoinColumn(name = "ABILITY_NAME"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"CHARACTER_CLASS_NAME", "ABILITY_NAME"})
    )
    private List<AbilityJpa> savingThrowProficiencies;

    @ManyToMany
    @JoinTable(
            name = "CLASS_SKILL",
            schema = "DND_STIKI",
            joinColumns = @JoinColumn(name = "CHARACTER_CLASS_NAME"),
            inverseJoinColumns = @JoinColumn(name = "SKILL_NAME"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"CHARACTER_CLASS_NAME", "SKILL_NAME"})
    )
    private  List<SkillJpa> skillProficiencies;

    @Column(name = "NUMBER_SKILLS")
    private Integer numberOfSkillProficiencies;

    @Column(name = "ARMOR_PROFICIENCIES")
    private String armorProficiencies;

    @Column(name = "WEAPON_PROFICIENCIES")
    private String weaponProficiencies;

    @Column(name = "TOOL_PROFICIENCIES")
    private String toolProficiencies;

    @Column(name = "WEALTH")
    private String wealth;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SPELL_ABILITY")
    private AbilityJpa spellAbility;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CHARACTER_CLASS", referencedColumnName = "NAME")
    private List <ClassLevelJpa> classLevels;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "CLASS_TRAIT", // join table
            schema = "DND_STIKI",
            joinColumns = @JoinColumn(name = "CLASS_NAME"),   // FK to Background
            inverseJoinColumns = @JoinColumn(name = "TRAIT_ID")    // FK to Trait
    )
    private List<TraitJpa> traits;

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

    public List<AbilityJpa> getSavingThrowProficiencies() {
        return savingThrowProficiencies;
    }

    public void setSavingThrowProficiencies(List<AbilityJpa> savingThrowProficiencies) {
        this.savingThrowProficiencies = savingThrowProficiencies;
    }

    public List<SkillJpa> getSkillProficiencies() {
        return skillProficiencies;
    }

    public void setSkillProficiencies(List<SkillJpa> skillProficiencies) {
        this.skillProficiencies = skillProficiencies;
    }

    public Integer getNumberOfSkillProficiencies() {
        return numberOfSkillProficiencies;
    }

    public void setNumberOfSkillProficiencies(Integer numberOfSkillProficiencies) {
        this.numberOfSkillProficiencies = numberOfSkillProficiencies;
    }

    public String getArmorProficiencies() {
        return armorProficiencies;
    }

    public void setArmorProficiencies(String armorProficiencies) {
        this.armorProficiencies = armorProficiencies;
    }

    public String getWeaponProficiencies() {
        return weaponProficiencies;
    }

    public void setWeaponProficiencies(String weaponProficiencies) {
        this.weaponProficiencies = weaponProficiencies;
    }

    public String getToolProficiencies() {
        return toolProficiencies;
    }

    public void setToolProficiencies(String toolProficiencies) {
        this.toolProficiencies = toolProficiencies;
    }

    public String getWealth() {
        return wealth;
    }

    public void setWealth(String wealth) {
        this.wealth = wealth;
    }

    public AbilityJpa getSpellAbility() {
        return spellAbility;
    }

    public void setSpellAbility(AbilityJpa spellAbility) {
        this.spellAbility = spellAbility;
    }

    public List<ClassLevelJpa> getClassLevels() {
        return classLevels;
    }

    public void setClassLevels(List<ClassLevelJpa> classLevels) {
        this.classLevels = classLevels;
    }

    public List<TraitJpa> getTraits() {
        return traits;
    }

    public void setTraits(List<TraitJpa> traits) {
        this.traits = traits;
    }
}
