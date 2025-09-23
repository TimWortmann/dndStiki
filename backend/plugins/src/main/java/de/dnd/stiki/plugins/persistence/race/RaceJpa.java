package de.dnd.stiki.plugins.persistence.race;

import de.dnd.stiki.plugins.persistence.trait.TraitJpa;
import jakarta.persistence.*;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.List;

@Entity
@Table(name = "RACE", schema = "DND_STIKI")
public class RaceJpa {

    @Id
    @Column(name = "NAME")
    private String name;

    @Column(name = "SIZE")
    private String size;

    @Column(name = "SPEED")
    private Integer speed;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "RACE_TRAIT", // join table
            schema = "DND_STIKI",
            joinColumns = @JoinColumn(name = "RACE_NAME"),   // FK to Background
            inverseJoinColumns = @JoinColumn(name = "TRAIT_ID")    // FK to Trait
    )
    private List<TraitJpa> traits;

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

    public List<TraitJpa> getTraits() {
        return traits;
    }

    public void setTraits(List<TraitJpa> traits) {
        this.traits = traits;
    }
}
