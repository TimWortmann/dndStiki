package de.dnd.stiki.plugins.persistence.characterClass;

import de.dnd.stiki.domain.characterClass.CharacterClassEntity;
import de.dnd.stiki.domain.characterClass.CharacterClassRepository;
import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpaRepository;
import de.dnd.stiki.plugins.persistence.basic.skill.SkillJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CharacterClassRepositoryImpl implements CharacterClassRepository {

    @Autowired
    private CharacterClassJpaRepository jpaRepository;

    @Autowired
    private CharacterClassJpaToEntityMapper jpaToEntityMapper;

    @Autowired
    private CharacterClassEntityToJpaMapper entityToJpaMapper;

    @Autowired
    private AbilityJpaRepository abilityJpaRepository;

    @Autowired
    private SkillJpaRepository skillJpaRepository;

    @Override
    public List<CharacterClassEntity> getAll() {
        List<CharacterClassJpa> jpaList = jpaRepository.findAll();

        return jpaToEntityMapper.mapJpasToEntities(jpaList);
    }

    @Override
    public List<CharacterClassEntity> save(List<CharacterClassEntity> entities) {
        List<CharacterClassJpa> jpaList = entityToJpaMapper.mapEntitiesToJpa(entities);

        return jpaToEntityMapper.mapJpasToEntities(jpaRepository.saveAll(jpaList));
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
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
