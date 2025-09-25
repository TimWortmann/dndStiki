package de.dnd.stiki.plugins.persistence.characterClass.classLevel;

import de.dnd.stiki.plugins.persistence.characterClass.counter.CounterJpa;
import de.dnd.stiki.plugins.persistence.characterClass.feature.FeatureJpa;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "CLASS_LEVEL", schema = "DND_STIKI")
public class ClassLevelJpa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "LEVEL")
    private Integer level;

    @Column(name = "SCORE_IMPROVEMENT")
    private boolean scoreImprovement;

    @Column(name = "SPELL_SLOTS")
    private String spellSlots;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CLASS_LEVEL_ID", referencedColumnName = "ID")
    private List<FeatureJpa> features;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "CLASS_LEVEL_ID", referencedColumnName = "ID")
    private List<CounterJpa> counters;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public boolean isScoreImprovement() {
        return scoreImprovement;
    }

    public void setScoreImprovement(boolean scoreImprovement) {
        this.scoreImprovement = scoreImprovement;
    }

    public String getSpellSlots() {
        return spellSlots;
    }

    public void setSpellSlots(String spellSlots) {
        this.spellSlots = spellSlots;
    }

    public List<FeatureJpa> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureJpa> features) {
        this.features = features;
    }

    public List<CounterJpa> getCounters() {
        return counters;
    }

    public void setCounters(List<CounterJpa> counters) {
        this.counters = counters;
    }
}
