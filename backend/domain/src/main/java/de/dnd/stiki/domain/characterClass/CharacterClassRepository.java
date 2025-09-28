package de.dnd.stiki.domain.characterClass;

import de.dnd.stiki.domain.AbstractRepository;

import java.util.List;

public interface CharacterClassRepository extends AbstractRepository<CharacterClassEntity> {

    public List<String> getSavingThrowProficiencies(List<String> mixedProficiencies);

    public List<String> getSkillProficiencies(List<String> mixedProficiencies);
}
