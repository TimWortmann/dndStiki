package de.dnd.stiki.plugins.persistence.dndClass;

import de.dnd.stiki.domain.dndClass.DndClassEntity;
import de.dnd.stiki.domain.dndClass.DndClassRepository;
import de.dnd.stiki.plugins.persistence.AbstractRepositoryImpl;
import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpaRepository;
import de.dnd.stiki.plugins.persistence.basic.skill.SkillJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class DndClassRepositoryImpl extends AbstractRepositoryImpl<
        DndClassEntity,
        DndClassJpa,
        DndClassJpaRepository,
        DndClassJpaToEntityMapper,
        DndClassEntityToJpaMapper> implements DndClassRepository {

    @Autowired
    private AbilityJpaRepository abilityJpaRepository;

    @Autowired
    private SkillJpaRepository skillJpaRepository;

    @Override
    public DndClassEntity getByName(String name) {
        Optional<DndClassJpa> jpaOptional = jpaRepository.findById(name);

        return jpaOptional.map(backgroundJpa -> jpaToEntityMapper.mapJpaToEntity(backgroundJpa)).orElse(null);
    }

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
