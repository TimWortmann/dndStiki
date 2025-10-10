package de.dnd.stiki.domain.dndClass;

import de.dnd.stiki.domain.AbstractRepository;

import java.util.List;

public interface DndClassRepository extends AbstractRepository<DndClassEntity> {

    public DndClassEntity getByName(String name);

    public List<String> getSavingThrowProficiencies(List<String> mixedProficiencies);

    public List<String> getSkillProficiencies(List<String> mixedProficiencies);
}
