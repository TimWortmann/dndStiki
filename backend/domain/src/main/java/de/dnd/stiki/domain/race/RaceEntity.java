package de.dnd.stiki.domain.race;

import de.dnd.stiki.domain.trait.TraitEntity;

import java.util.List;

public class RaceEntity {

    private String name;

    private String size;

    private Integer speed;

    private List<TraitEntity> traits;

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

    public List<TraitEntity> getTraits() {
        return traits;
    }

    public void setTraits(List<TraitEntity> traits) {
        this.traits = traits;
    }
}
