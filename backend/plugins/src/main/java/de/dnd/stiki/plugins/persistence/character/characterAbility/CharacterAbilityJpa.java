package de.dnd.stiki.plugins.persistence.character.characterAbility;

import de.dnd.stiki.plugins.persistence.character.CharacterJpa;
import jakarta.persistence.*;

@Entity
@Table(name = "CHARACTER_ABILITY", schema = "DND_STIKI")
public class CharacterAbilityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BASIC_SCORE")
    private int basicScore;

    @Column(name = "BONUS")
    private int bonus;

    @Column(name = "SAVING_THROW_PROFICIENCY")
    private int savingThrowProficiency;

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

    public int getBasicScore() {
        return basicScore;
    }

    public void setBasicScore(int basicScore) {
        this.basicScore = basicScore;
    }

    public int getBonus() {
        return bonus;
    }

    public void setBonus(int bonus) {
        this.bonus = bonus;
    }

    public int getSavingThrowProficiency() {
        return savingThrowProficiency;
    }

    public void setSavingThrowProficiency(int savingThrowProficiency) {
        this.savingThrowProficiency = savingThrowProficiency;
    }

    public CharacterJpa getCharacter() {
        return character;
    }

    public void setCharacter(CharacterJpa character) {
        this.character = character;
    }
}
