package de.dnd.stiki.adapters.character.characterSkill;

public class CharacterSkillDto {

    private Long id;

    private String name;

    private String ability;

    private int basicModifier;

    private int proficiency;

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

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
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
}
