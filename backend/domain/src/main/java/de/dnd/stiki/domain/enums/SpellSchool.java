package de.dnd.stiki.domain.enums;

public enum SpellSchool {
    ABJURATION("Abjuration", "A"),
    CONJURATION("Conjuration", "C"),
    DIVINATION("Divination", "D"),
    ENCHANTMENT("Enchantment", "EN"),
    EVOCATION("Evocation", "EV"),
    ILLUSION("Illusion", "I"),
    NECROMANCY("Necromancy", "N"),
    TRANSMUTATION("Transmutation", "T");

    private final String displayName;
    private final String shortName;

    SpellSchool(String displayName, String shortName) {
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

    public static SpellSchool fromShortName(String shortName) {
        for (SpellSchool school : values()) {
            if (school.getShortName().equalsIgnoreCase(shortName)) {
                return school;
            }
        }
        throw new IllegalArgumentException("Unknown shortName: " + shortName);
    }
}
