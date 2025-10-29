package de.dnd.stiki.domain.character;

import de.dnd.stiki.domain.enums.AbilityType;

public class CharacterAttackEntity {

    private String name;
    private String baseDamageRoll;
    private AbilityType ability;
    private boolean proficient;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBaseDamageRoll() {
        return baseDamageRoll;
    }

    public void setBaseDamageRoll(String baseDamageRoll) {
        this.baseDamageRoll = baseDamageRoll;
    }

    public AbilityType getAbility() {
        return ability;
    }

    public void setAbility(AbilityType ability) {
        this.ability = ability;
    }

    public boolean isProficient() {
        return proficient;
    }

    public void setProficient(boolean proficient) {
        this.proficient = proficient;
    }
}
