package de.dnd.stiki.domain.feat;

import java.util.List;

public class FeatEntity {

    private String name;

    private List<String> prerequisites;

    private String text;

    private List<ModifierEntity> modifiers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public void setPrerequisites(List<String> prerequisites) {
        this.prerequisites = prerequisites;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public List<ModifierEntity> getModifiers() {
        return modifiers;
    }

    public void setModifiers(List<ModifierEntity> modifiers) {
        this.modifiers = modifiers;
    }
}
