package de.dnd.stiki.plugins.persistence.characterClass.feature;

import de.dnd.stiki.plugins.persistence.basic.trait.TraitJpa;
import jakarta.persistence.*;

@Entity
@Table(name = "FEATURE", schema = "DND_STIKI")
public class FeatureJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "OPTIONAL")
    private boolean optional;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAIT_ID")
    private TraitJpa trait;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }

    public TraitJpa getTrait() {
        return trait;
    }

    public void setTrait(TraitJpa trait) {
        this.trait = trait;
    }
}
