package de.dnd.stiki.plugins.persistence.character.characterAttack;

import de.dnd.stiki.plugins.persistence.character.CharacterJpa;
import jakarta.persistence.*;

@Entity
@Table(name = "CHARACTER_Attack", schema = "DND_STIKI")
public class CharacterAttackJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BASE_DAMAGE_ROLL")
    private String baseDamageRoll;

    @Column(name = "ABILITY")
    private String ability;

    @Column(name = "PROFICIENT")
    private boolean proficient;

    @ManyToOne
    @JoinColumn(name = "CHARACTER_ID")
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

    public String getBaseDamageRoll() {
        return baseDamageRoll;
    }

    public void setBaseDamageRoll(String baseDamageRoll) {
        this.baseDamageRoll = baseDamageRoll;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }

    public boolean isProficient() {
        return proficient;
    }

    public void setProficient(boolean proficient) {
        this.proficient = proficient;
    }

    public CharacterJpa getCharacter() {
        return character;
    }

    public void setCharacter(CharacterJpa character) {
        this.character = character;
    }
}
