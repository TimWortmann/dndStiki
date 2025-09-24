package de.dnd.stiki.domain.characterClass.feature;

import de.dnd.stiki.domain.characterClass.CharacterClassEntity;

import java.util.List;

public interface CharacterClassRepository {

    public List<CharacterClassEntity> getAllCharacterClasses();

    public List<CharacterClassEntity> createCharacterClasses(List<CharacterClassEntity> entities);

    public void deleteAllCharacterClasses();

    public List<String> getSavingThrowProficiencies(List<String> mixedProficiencies);

    public List<String> getSkillProficiencies(List<String> mixedProficiencies);
}
