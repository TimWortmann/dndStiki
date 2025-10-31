package de.dnd.stiki.adapters.character.characterAttack;

public class CharacterAttackDto {

    private String name;
    private String hitBonus;
    private String damageRoll;
    private boolean proficient;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHitBonus() {
        return hitBonus;
    }

    public void setHitBonus(String hitBonus) {
        this.hitBonus = hitBonus;
    }

    public String getDamageRoll() {
        return damageRoll;
    }

    public void setDamageRoll(String damageRoll) {
        this.damageRoll = damageRoll;
    }

    public boolean isProficient() {
        return proficient;
    }

    public void setProficient(boolean proficient) {
        this.proficient = proficient;
    }
}
