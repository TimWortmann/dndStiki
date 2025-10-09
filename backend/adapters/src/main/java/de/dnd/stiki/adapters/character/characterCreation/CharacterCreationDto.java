package de.dnd.stiki.adapters.character.characterCreation;

import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityDto;

import java.util.List;

public class CharacterCreationDto {

    private String name;

    private String dndClass;

    private String background;

    private String race;

    private List<CharacterAbilityDto> abilities;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDndClass() {
        return dndClass;
    }

    public void setDndClass(String dndClass) {
        this.dndClass = dndClass;
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

    public List<CharacterAbilityDto> getAbilities() {
        return abilities;
    }

    public void setAbilities(List<CharacterAbilityDto> abilities) {
        this.abilities = abilities;
    }
}
