package de.dnd.stiki.domain.character;

import java.util.List;

public class CharacterSpellSlotsEntity {

    private List<Integer> spellSlots;

    private Integer level;

    public List<Integer> getSpellSlots() {
        return spellSlots;
    }

    public void setSpellSlots(List<Integer> spellSlots) {
        this.spellSlots = spellSlots;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }
}
