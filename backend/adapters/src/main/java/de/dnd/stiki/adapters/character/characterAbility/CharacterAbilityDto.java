package de.dnd.stiki.adapters.character.characterAbility;

public class CharacterAbilityDto {

    private Long id;

    private String name;

    private int basicScore;

    private int bonus;

    private int savingThrowProficiency;

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
}
