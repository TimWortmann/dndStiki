package de.dnd.stiki.plugins.persistence.character.characterAbility;

import de.dnd.stiki.domain.character.AbilityType;
import de.dnd.stiki.plugins.persistence.character.CharacterJpa;
import jakarta.persistence.*;

@Entity
@Table(name = "CHARACTER_ABILITY", schema = "DND_STIKI")
public class CharacterAbilityJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "ABILITY")
    private AbilityType ability;

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

    public AbilityType getAbility() {
        return ability;
    }

    public void setAbility(AbilityType ability) {
        this.ability = ability;
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
