package de.dnd.stiki.adapters.character.characterItem;

import de.dnd.stiki.adapters.item.ItemDto;

public class CharacterItemDto extends ItemDto {

    private Long id;
    private int quantity;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
