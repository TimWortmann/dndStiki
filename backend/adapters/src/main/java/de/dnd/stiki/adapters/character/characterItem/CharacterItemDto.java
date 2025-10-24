package de.dnd.stiki.adapters.character.characterItem;

import de.dnd.stiki.adapters.item.ItemDto;

public class CharacterItemDto extends ItemDto {

    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
