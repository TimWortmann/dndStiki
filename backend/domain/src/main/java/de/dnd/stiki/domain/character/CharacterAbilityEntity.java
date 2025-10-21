package de.dnd.stiki.domain.character;

import de.dnd.stiki.domain.enums.AbilityType;

public class CharacterAbilityEntity {

    private Long id;

    private AbilityType name;

    private int basicScore;

    private int bonus;

    private int savingThrowProficiency;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbilityType getName() {
        return name;
    }

    public void setName(AbilityType name) {
        this.name = name;
    }

    public int getBasicScore() {
        return basicScore;
    }

    public void setBasicScore(int basicScore) {
        this.basicScore = basicScore;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getSavingThrowProficiency() {
        return savingThrowProficiency;
    }

    public void setSavingThrowProficiency(int savingThrowProficiency) {
        this.savingThrowProficiency = savingThrowProficiency;
    }

    public Integer getScore() {
        return basicScore + bonus;
    }

    public Integer getModifier() {
        return (int) Math.floor((getScore() - 10) / 2.0);
    }

    public Integer getSavingThrowModifier(int proficiencyBonus) {
        return getModifier() + (savingThrowProficiency * proficiencyBonus);
    }
}
