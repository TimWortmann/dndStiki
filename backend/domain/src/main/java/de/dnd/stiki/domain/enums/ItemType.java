package de.dnd.stiki.domain.enums;

public enum ItemType {
    CURRENCY("Currency", "$"),
    WEAPON("Weapon", "W"),
    MELEE_WEAPON("Melee Weapon", "M"),
    RANGED_WEAPON("Ranged Weapon", "R"),
    STAFF("Staff", "ST"),
    ROD("Rod", "RD"),
    WAND("Wand", "WD"),
    SHIELD("Shield", "S"),
    POISON("Poison", "P"),
    SCROLL("Scroll", "SC"),
    ARMOR("Armor", "A"),
    GEAR("Gear", "G"),
    RING("Ring", "RG"),
    LIGHT_ARMOR("Light Armor", "LA"),
    MEDIUM_ARMOR("Medium Armor", "MA"),
    HEAVY_ARMOR("Heavy Armor", "HA");

    private final String displayName;
    private final String shortName;

    ItemType(String displayName, String shortName) {
        this.displayName = displayName;
        this.shortName = shortName;
    }

    public String getDisplayName() {
        return displayName;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static ItemType fromShortName(String shortName) {
        for (ItemType type : values()) {
            if (type.getShortName().equalsIgnoreCase(shortName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown shortName: " + shortName);
    }
}
