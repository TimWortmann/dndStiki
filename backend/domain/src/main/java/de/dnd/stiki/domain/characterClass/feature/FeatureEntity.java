package de.dnd.stiki.domain.characterClass.feature;

import de.dnd.stiki.domain.trait.TraitEntity;

public class FeatureEntity extends TraitEntity {

    private boolean optional;

    public boolean isOptional() {
        return optional;
    }

    public void setOptional(boolean optional) {
        this.optional = optional;
    }
}
