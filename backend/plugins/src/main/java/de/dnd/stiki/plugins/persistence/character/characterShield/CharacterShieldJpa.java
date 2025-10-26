package de.dnd.stiki.plugins.persistence.character.characterShield;

import jakarta.persistence.*;

@Entity
@Table(name = "CHARACTER_SHIELD", schema = "DND_STIKI")
public class CharacterShieldJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME")
    private String name;

    @Column(name = "AC")
    private Integer ac;

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

    public Integer getAc() {
        return ac;
    }

    public void setAc(Integer ac) {
        this.ac = ac;
    }
}
