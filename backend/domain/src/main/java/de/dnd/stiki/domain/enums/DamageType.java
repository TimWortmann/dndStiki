package de.dnd.stiki.domain.enums;

public enum DamageType {

    BLUDGEONING("B", "Bludgeoning"),
    PIERCING("P", "Piercing"),
    SLASHING("S", "Slashing"),
    NECROTIC("N", "Necrotic"),
    RADIANT("R", "Radiant"),
    FIRE("F", "Fire"),
    COLD("C", "Cold"),
    LIGHTNING("L", "Lightning"),
    THUNDER("T", "Thunder"),
    PSYCHIC("PY", "Psychic"),
    FORCE("FC", "Force");

    private final String shortName;
    private final String displayName;

    DamageType(String shortName, String displayName) {
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

    public static DamageType fromShortName(String shortName) {
        for (DamageType damageType : values()) {
            if (damageType.getShortName().equalsIgnoreCase(shortName)) {
                return damageType;
            }
        }
        throw new IllegalArgumentException("Unknown shortName: " + shortName);
    }
}
