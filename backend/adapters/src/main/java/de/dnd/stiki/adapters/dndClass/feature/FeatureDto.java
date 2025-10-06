package de.dnd.stiki.adapters.dndClass.feature;

import de.dnd.stiki.adapters.trait.TraitDto;

public class FeatureDto extends TraitDto {

    private boolean optional;

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }
}
