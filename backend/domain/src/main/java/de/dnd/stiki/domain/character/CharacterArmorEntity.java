package de.dnd.stiki.domain.character;

import de.dnd.stiki.domain.enums.ItemType;

public class CharacterArmorEntity {

    private String name;
    private Integer ac;
    private ItemType type;

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

    public ItemType getType() {
        return type;
    }

    public void setType(ItemType type) {
        this.type = type;
    }
}
