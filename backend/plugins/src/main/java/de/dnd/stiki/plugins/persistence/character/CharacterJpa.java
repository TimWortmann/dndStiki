package de.dnd.stiki.plugins.persistence.character;

import de.dnd.stiki.plugins.persistence.character.characterAbility.CharacterAbilityJpa;
import de.dnd.stiki.plugins.persistence.character.characterItem.CharacterItemJpa;
import de.dnd.stiki.plugins.persistence.character.characterSkill.CharacterSkillJpa;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CHARACTER", schema = "DND_STIKI")
public class CharacterJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "DND_CLASS")
    private String dndClass;

    @Column(name = "DND_SUBCLASS")
    private String dndSubclass;

    @Column(name = "SPELLCASTING_ABILITY")
    private String spellcastingAbility;

    @Column(name = "BACKGROUND")
    private String background;

    @Column(name = "RACE")
    private String race;

    @Column(name = "MAX_HEALTH")
    private Integer maxHealth;

    @Column(name = "CURRENT_HEALTH")
    private Integer currentHealth;

    @Column(name = "HIT_DICE")
    private Integer hitDice;

    @Column(name = "MAX_HIT_DICE")
    private Integer maxHitDice;

    @Column(name = "CURRENT_HIT_DICE")
    private Integer currentHitDice;

    @Column(name = "ARMOR_CLASS")
    private Integer armorClass;

    @Column(name = "SPEED")
    private Integer speed;

    @Column(name = "PASSIVE_PERCEPTION")
    private Integer passivePerception;

    @Column(name = "PROFICIENCY_BONUS")
    private Integer proficiencyBonus;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterItemJpa> items;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterTraitJpa> traits;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterAbilityJpa> abilities;

    @OneToMany(mappedBy = "character", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CharacterSkillJpa> skills;

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

    public List<CharacterItemJpa> getItems() {
        return items;
    }

    public void setItems(List<CharacterItemJpa> items) {
        this.items = items;
    }

    public List<CharacterTraitJpa> getTraits() {
        return traits;
    }

    public void setTraits(List<CharacterTraitJpa> traits) {
        this.traits = traits;
    }

    public List<CharacterAbilityJpa> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<CharacterAbilityJpa> abilities) {
        this.abilities = abilities;
    }

    public List<CharacterSkillJpa> getSkills() {
        return skills;
    }

    public void setSkills(List<CharacterSkillJpa> skills) {
        this.skills = skills;
    }
}
