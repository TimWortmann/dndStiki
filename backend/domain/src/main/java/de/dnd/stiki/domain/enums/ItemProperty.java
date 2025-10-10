package de.dnd.stiki.domain.enums;

public enum ItemProperty {
    AMMUNITION("A", "Ammunition"),
    MARTIAL("M", "Martial"),
    SIMPLE("S", "Simple"),
    FINESSE("F", "Finesse"),
    LIGHT("L", "Light"),
    HEAVY("H", "Heavy"),
    VERSATILE("V", "Versatile"),
    RANGED("R", "Reach"),
    THROWN("T", "Thrown"),
    TWO_HANDED("2H", "Two Handed"),
    LOADING("LD", "Loading"),
    RELOAD("RLD", "Reload");

    private final String shortName;
    private final String displayName;

    ItemProperty(String shortName, String displayName) {
        this.shortName = shortName;
        this.displayName = displayName;
    }

    public String getShortName() {
        return shortName;
    }

    public String getDisplayName() {
        return displayName;
    }

    @Override
    public String toString() {
        return displayName;
    }

    public static ItemProperty fromShortName(String shortName) {
        for (ItemProperty prop : values()) {
            if (prop.getShortName().equalsIgnoreCase(shortName)) {
                return prop;
            }
        }
        throw new IllegalArgumentException("Unknown shortName: " + shortName);
    }
}

