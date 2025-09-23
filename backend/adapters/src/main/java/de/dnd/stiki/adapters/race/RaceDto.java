package de.dnd.stiki.adapters.race;

import de.dnd.stiki.adapters.trait.TraitDto;

import java.util.List;

public class RaceDto {

    private String name;

    private String size;

    private Integer speed;

    private String ability;

    private String spellAbility;

    private List<TraitDto> traits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public String getSpellAbility() {
        return spellAbility;
    }

    public void setSpellAbility(String spellAbility) {
        this.spellAbility = spellAbility;
    }

    public List<TraitDto> getTraits() {
        return traits;
    }

    public void setTraits(List<TraitDto> traits) {
        this.traits = traits;
    }
}
