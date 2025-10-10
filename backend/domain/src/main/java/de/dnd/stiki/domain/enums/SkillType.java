package de.dnd.stiki.domain.enums;

import java.util.ArrayList;
import java.util.List;

public enum SkillType {
    ACROBATICS("Acrobatics", "Dexterity"),
    ANIMAL_HANDLING("Animal Handling", "Wisdom"),
    ARCANA("Arcana", "Intelligence"),
    ATHLETICS("Athletics", "Strength"),
    DECEPTION("Deception", "Charisma"),
    HISTORY("History", "Intelligence"),
    INSIGHT("Insight", "Wisdom"),
    INTIMIDATION("Intimidation", "Charisma"),
    INVESTIGATION("Investigation", "Intelligence"),
    MEDICINE("Medicine", "Wisdom"),
    NATURE("Nature", "Intelligence"),
    PERCEPTION("Perception", "Wisdom"),
    PERFORMANCE("Performance", "Charisma"),
    PERSUASION("Persuasion", "Charisma"),
    RELIGION("Religion", "Intelligence"),
    SLEIGHT_OF_HAND("Sleight of Hand", "Dexterity"),
    STEALTH("Stealth", "Dexterity"),
    SURVIVAL("Survival", "Wisdom");

    private final String name;
    private final String ability;

    SkillType(String name, String ability) {
        this.name = name;
        this.ability = ability;
    }

    public String getName() {
        return name;
    }

    public String getAbility() {
        return ability;
    }

    @Override
    public String toString() {
        return name;
    }

    public static SkillType fromName(String name) {
        for (SkillType skillType : values()) {
            if (skillType.getName().equalsIgnoreCase(name)) {
                return skillType;
            }
        }
        throw new IllegalArgumentException("Unknown name: " + name);
    }

    public static List<SkillType> fromAbility(String ability) {
        List<SkillType> skillTypes = new ArrayList<>();
        for (SkillType skillType : values()) {
            if (skillType.getAbility().equalsIgnoreCase(ability)) {
                skillTypes.add(skillType);
            }
        }
        return skillTypes;
    }
}
