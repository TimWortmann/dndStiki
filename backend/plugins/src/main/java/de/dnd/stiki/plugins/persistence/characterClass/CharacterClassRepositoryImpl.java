package de.dnd.stiki.plugins.persistence.characterClass;

import de.dnd.stiki.domain.characterClass.CharacterClassEntity;
import de.dnd.stiki.domain.characterClass.CharacterClassRepository;
import de.dnd.stiki.plugins.persistence.AbstractRepositoryImpl;
import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpaRepository;
import de.dnd.stiki.plugins.persistence.basic.skill.SkillJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CharacterClassRepositoryImpl extends AbstractRepositoryImpl<
        CharacterClassEntity,
        CharacterClassJpa,
        CharacterClassJpaRepository,
        CharacterClassJpaToEntityMapper,
        CharacterClassEntityToJpaMapper> implements CharacterClassRepository {

    @Autowired
    private AbilityJpaRepository abilityJpaRepository;

    @Autowired
    private SkillJpaRepository skillJpaRepository;

    @Override
    public List<String> getSavingThrowProficiencies(List<String> mixedProficiencies) {

        List<String> savingThrowProficiencies = new ArrayList<>();
        for (String proficiency : mixedProficiencies) {
            if (abilityJpaRepository.existsById(proficiency)) {
                savingThrowProficiencies.add(proficiency);
            }
        }
        return savingThrowProficiencies;
    }

    @Override
    public List<String> getSkillProficiencies(List<String> mixedProficiencies) {

        List<String> skillProficiencies = new ArrayList<>();
        for (String proficiency : mixedProficiencies) {
            if (skillJpaRepository.existsById(proficiency)) {
                skillProficiencies.add(proficiency);
            }
        }
        return skillProficiencies;
    }
}
