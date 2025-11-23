package de.dnd.stiki.domain.dndClass.classLevel;

import de.dnd.stiki.domain.dndClass.counter.CounterEntity;
import de.dnd.stiki.domain.dndClass.feature.FeatureEntity;

import java.util.List;

public class ClassLevelEntity {

    private Long id;

    private Integer level;

    private boolean scoreImprovement;

    private List<Integer> spellSlots;

    private List<FeatureEntity> features;

    private List<CounterEntity> counters;

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

    public List<Integer> getSpellSlots() {
        return spellSlots;
    }

    public void setSpellSlots(List<Integer> spellSlots) {
        this.spellSlots = spellSlots;
    }

    public List<FeatureEntity> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureEntity> features) {
        this.features = features;
    }

    public List<CounterEntity> getCounters() {
        return counters;
    }

    public void setCounters(List<CounterEntity> counters) {
        this.counters = counters;
    }
}
