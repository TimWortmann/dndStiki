package de.dnd.stiki.domain.background;

import de.dnd.stiki.domain.trait.TraitEntity;

import java.util.List;

public class BackgroundEntity {

    private String name;
    private List<String> proficiencies;
    private List<TraitEntity> traits;

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

    public List<TraitEntity> getTraits() {
        return traits;
    }

    public void setTraits(List<TraitEntity> traits) {
        this.traits = traits;
    }
}
