package de.dnd.stiki.domain.enums;

public enum SubclassPrefix {

    SUBCLASS("Subclass"),
    ARCHETYPE("Archetype"),
    COLLEGE("College"),
    PATRON("Patron");

    private final String name;

    SubclassPrefix(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
