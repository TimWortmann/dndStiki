package de.dnd.stiki.plugins.persistence.character.characterSpell;

import de.dnd.stiki.plugins.persistence.character.CharacterJpa;
import de.dnd.stiki.plugins.persistence.spell.SpellJpa;
import jakarta.persistence.*;

@Entity
@Table(name = "CHARACTER_SPELL", schema = "DND_STIKI")
public class CharacterSpellJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "SPELL_ID")
    private SpellJpa spell;

    @ManyToOne
    @JoinColumn(name = "CHARACTER_ID")
    private CharacterJpa character;

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

    public CharacterJpa getCharacter() {
        return character;
    }

    public void setCharacter(CharacterJpa character) {
        this.character = character;
    }
}
