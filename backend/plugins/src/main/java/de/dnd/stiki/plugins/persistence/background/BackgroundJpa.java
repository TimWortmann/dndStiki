package de.dnd.stiki.plugins.persistence.background;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "BACKGROUND", schema = "DND_STIKI")
public class BackgroundJpa {

    @Id
    @Column(name="NAME")
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
