package de.dnd.stiki.domain.enums;

public enum AbilityType {
    STRENGTH("Strength"),
    DEXTERITY("Dexterity"),
    CONSTITUTION("Constitution"),
    INTELLIGENCE("Intelligence"),
    WISDOM("Wisdom"),
    CHARISMA("Charisma");

    private final String name;

    AbilityType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    public static AbilityType fromName(String name) {
        for (AbilityType abilityType : values()) {
            if (abilityType.getName().equalsIgnoreCase(name)) {
                return abilityType;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + name);
    }
}
