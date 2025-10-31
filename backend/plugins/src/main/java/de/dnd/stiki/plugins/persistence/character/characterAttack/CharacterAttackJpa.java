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

    @Column(name = "MODIFIED_HIT_BONUS")
    private String modifiedHitBonus;

    @Column(name = "MODIFIED_DAMAGE_ROLL")
    private String modifiedDamageRoll;

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

    public String getModifiedHitBonus() {
        return modifiedHitBonus;
    }

    public void setModifiedHitBonus(String modifiedHitBonus) {
        this.modifiedHitBonus = modifiedHitBonus;
    }

    public String getModifiedDamageRoll() {
        return modifiedDamageRoll;
    }

    public void setModifiedDamageRoll(String modifiedDamageRoll) {
        this.modifiedDamageRoll = modifiedDamageRoll;
    }

    public CharacterJpa getCharacter() {
        return character;
    }

    public void setCharacter(CharacterJpa character) {
        this.character = character;
    }
}
