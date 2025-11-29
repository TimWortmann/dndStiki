package de.dnd.stiki.domain.enums;

public enum ItemType {
    CURRENCY("Currency", "$"),
    WEAPON("Weapon", "W"),
    MELEE_WEAPON("Melee Weapon", "M"),
    RANGED_WEAPON("Ranged Weapon", "R"),
    STAFF("Staff", "ST"),
    ROD("Rod", "RD"),
    WAND("Wand", "WD"),
    LIGHT_ARMOR("Light Armor", "LA"),
    MEDIUM_ARMOR("Medium Armor", "MA"),
    HEAVY_ARMOR("Heavy Armor", "HA"),
    SHIELD("Shield", "S"),
    AMMUNITION("Ammunition", "A"),
    POISON("Poison", "P"),
    SCROLL("Scroll", "SC"),
    GEAR("Gear", "G"),
    RING("Ring", "RG");

    private final String name;
    private final String shortName;

    ItemType(String name, String shortName) {
        this.name = name;
        this.shortName = shortName;
    }

    public String getName() {
        return name;
    }

    public String getShortName() {
        return shortName;
    }

    @Override
    public String toString() {
        return name;
    }

    public static ItemType fromShortName(String shortName) {
        for (ItemType type : values()) {
            if (type.getShortName().equalsIgnoreCase(shortName)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown shortName: " + shortName);
    }

    public static ItemType fromName(String name) {
        for (ItemType type : values()) {
            if (type.getName().equalsIgnoreCase(name)) {
                return type;
            }
        }
        throw new IllegalArgumentException("Unknown shortName: " + name);
    }
}
