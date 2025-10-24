package de.dnd.stiki.domain.character;

import de.dnd.stiki.domain.item.ItemEntity;

public class CharacterItemEntity extends ItemEntity {

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
