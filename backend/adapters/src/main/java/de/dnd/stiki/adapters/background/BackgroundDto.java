package de.dnd.stiki.adapters.background;

import de.dnd.stiki.adapters.trait.TraitDto;

import java.util.List;

public class BackgroundDto {

    private String name;
    private List<String> proficiencies;
    private List<TraitDto> traits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(List<String> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public List<TraitDto> getTraits() {
        return traits;
    }

    public void setTraits(List<TraitDto> traits) {
        this.traits = traits;
    }
}
