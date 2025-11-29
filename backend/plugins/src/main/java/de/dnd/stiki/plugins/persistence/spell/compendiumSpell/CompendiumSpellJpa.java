package de.dnd.stiki.plugins.persistence.spell.compendiumSpell;

import de.dnd.stiki.plugins.persistence.spell.SpellJpa;
import jakarta.persistence.*;

@Entity
@Table(name = "COMPENDIUM_SPELL", schema = "DND_STIKI")
public class CompendiumSpellJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="ID")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(
            name = "SPELL_ID",
            referencedColumnName = "ID",
            foreignKey = @ForeignKey(name = "FK_COMPENDIUM_SPELL")
    )
    private SpellJpa spell;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SpellJpa getSpell() {
        return spell;
    }

    public void setSpell(SpellJpa spell) {
        this.spell = spell;
    }
}
