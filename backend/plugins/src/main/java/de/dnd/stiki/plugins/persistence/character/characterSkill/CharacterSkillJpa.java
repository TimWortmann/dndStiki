package de.dnd.stiki.plugins.persistence.character.characterSkill;

import de.dnd.stiki.plugins.persistence.character.CharacterJpa;
import jakarta.persistence.*;

@Entity
@Table(name = "CHARACTER_SKILL", schema = "DND_STIKI")
public class CharacterSkillJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "ABILITY")
    private String ability;

    @Column(name = "BASIC_MODIFIER")
    private int basicModifier;

    @Column(name = "PROFICIENCY")
    private int proficiency;

    @ManyToOne
    @JoinColumn(name = "CHARACTER_ID", nullable = false)
    private CharacterJpa character;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public int getProficiency() {
        return proficiency;
    }

    public void setProficiency(int proficiency) {
        this.proficiency = proficiency;
    }

    public CharacterJpa getCharacter() {
        return character;
    }

    public void setCharacter(CharacterJpa character) {
        this.character = character;
    }

    public int getBasicModifier() {
        return basicModifier;
    }

    public void setBasicModifier(int basicModifier) {
        this.basicModifier = basicModifier;
    }
}
