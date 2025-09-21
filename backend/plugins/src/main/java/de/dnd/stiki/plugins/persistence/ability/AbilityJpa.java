package de.dnd.stiki.plugins.persistence.ability;

import jakarta.persistence.*;

@Entity
@Table(name = "ABILITY", schema = "DND_STIKI")
public class AbilityJpa {

    @Id
    @Column(name = "NAME")
    private String name;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
