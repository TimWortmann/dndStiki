package de.dnd.stiki.adapters.character.characterAbility;

import de.dnd.stiki.domain.character.AbilityType;

public class CharacterAbilityDto {

    private Long id;

    private AbilityType ability;

    private int basicScore;

    private int bonus;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbilityType getAbility() {
        return ability;
    }

    public void setAbility(AbilityType ability) {
        this.ability = ability;
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
}
