package de.dnd.stiki.domain.spell;

import java.util.List;

public interface SpellRepository {

    public List<SpellEntity> getAllSpells();

    public List<SpellEntity> createSpells(List<SpellEntity> entities);

    public void deleteAllSpells();
}
