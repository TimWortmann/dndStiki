package de.dnd.stiki.plugins.persistence.feat;

import de.dnd.stiki.plugins.persistence.feat.modifier.ModifierJpa;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "FEAT", schema = "DND_STIKI")
public class FeatJpa {

    @Id
    @Column(name = "NAME")
    private String name;

    @Column(name = "PREREQUISITE")
    private String prerequisites;

    @Column(name = "TEXT")
    private String text;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "FEAT_NAME", referencedColumnName = "NAME")
    private List<ModifierJpa> modifiers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(String prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ModifierJpa> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<ModifierJpa> modifiers) {
        this.modifiers = modifiers;
    }
}
