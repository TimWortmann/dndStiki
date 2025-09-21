package de.dnd.stiki.plugins.persistence.background;

import de.dnd.stiki.plugins.persistence.skill.SkillJpa;
import de.dnd.stiki.plugins.persistence.trait.TraitJpa;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "BACKGROUND", schema = "DND_STIKI")
public class BackgroundJpa {

    @Id
    @Column(name="NAME")
    private String name;

    @ManyToMany
    @JoinTable(
            name = "BACKGROUND_SKILL", // join table
            schema = "DND_STIKI",
            joinColumns = @JoinColumn(name = "BACKGROUND_NAME"),   // FK to Background
            inverseJoinColumns = @JoinColumn(name = "SKILL_NAME")    // FK to Trait
    )
    private List<SkillJpa> proficiencies;

    @ManyToMany
    @JoinTable(
            name = "BACKGROUND_TRAIT", // join table
            schema = "DND_STIKI",
            joinColumns = @JoinColumn(name = "BACKGROUND_NAME"),   // FK to Background
            inverseJoinColumns = @JoinColumn(name = "TRAIT_ID")    // FK to Trait
    )
    private List<TraitJpa> traits;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SkillJpa> getProficiencies() {
        return proficiencies;
    }

    public void setProficiencies(List<SkillJpa> proficiencies) {
        this.proficiencies = proficiencies;
    }

    public List<TraitJpa> getTraits() {
        return traits;
    }

    public void setTraits(List<TraitJpa> traits) {
        this.traits = traits;
    }
}
