package de.dnd.stiki.plugins.persistence.character;

import de.dnd.stiki.domain.character.CharacterTraitType;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitJpa;
import jakarta.persistence.*;

@Entity
@Table(name = "CHARACTER_TRAIT", schema = "DND_STIKI")
public class CharacterTraitJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "TRAIT_ID", nullable = false)
    private TraitJpa trait;

    @Column(name = "TRAIT_TYPE")
    private CharacterTraitType traitType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public TraitJpa getTrait() {
        return trait;
    }

    public void setTrait(TraitJpa trait) {
        this.trait = trait;
    }

    public CharacterTraitType getTraitType() {
        return traitType;
    }

    public void setTraitType(CharacterTraitType traitType) {
        this.traitType = traitType;
    }
}

