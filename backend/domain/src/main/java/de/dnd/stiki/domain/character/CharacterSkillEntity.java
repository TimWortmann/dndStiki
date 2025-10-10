package de.dnd.stiki.domain.character;

import de.dnd.stiki.domain.enums.AbilityType;
import de.dnd.stiki.domain.enums.SkillType;

public class CharacterSkillEntity {

    private Long id;

    private SkillType name;

    private AbilityType ability;

    private int basicModifier;

    private int proficiency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SkillType getName() {
        return name;
    }

    public void setName(SkillType name) {
        this.name = name;
    }

    public AbilityType getAbility() {
        return ability;
    }

    public void setAbility(AbilityType ability) {
        this.ability = ability;
    }

    public int getBasicModifier() {
        return basicModifier;
    }

    public void setBasicModifier(int basicModifier) {
        this.basicModifier = basicModifier;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public Integer getModifierWithProficiency(Integer proficiencyBonus) {
        if (proficiencyBonus == null) {
            return basicModifier ;
        }

        return basicModifier + (proficiency * proficiencyBonus);
    }
}
