package de.dnd.stiki.domain.trait;

import java.util.List;

public interface TraitRepository {

    public List<TraitEntity> getAllTraits();

    public void deleteAllTraits();
}
