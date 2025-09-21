package de.dnd.stiki.plugins.persistence.skill;

import de.dnd.stiki.plugins.persistence.ability.AbilityJpa;
import jakarta.persistence.*;

@Entity
@Table(name = "SKILL", schema = "DND_STIKI")
public class SkillJpa {

    @Id
    @Column(name = "Name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ABILITY")
    private AbilityJpa ability;

    @Column(name = "DESCRIPTION")
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbilityJpa getAbility() {
        return ability;
    }

    public void setAbility(AbilityJpa ability) {
        this.ability = ability;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
