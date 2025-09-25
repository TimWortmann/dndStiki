package de.dnd.stiki.adapters.characterClass.classLevel;

import de.dnd.stiki.adapters.characterClass.counter.CounterDto;
import de.dnd.stiki.adapters.characterClass.feature.FeatureDto;

import java.util.List;

public class ClassLevelDto {

    private Long id;

    private Integer level;

    private boolean scoreImprovement;

    private String spellSlots;

    private List<FeatureDto> features;

    private List<CounterDto> counters;

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

    public List<FeatureDto> getFeatures() {
        return features;
    }

    public void setFeatures(List<FeatureDto> features) {
        this.features = features;
    }

    public List<CounterDto> getCounters() {
        return counters;
    }

    public void setCounters(List<CounterDto> counters) {
        this.counters = counters;
    }
}
