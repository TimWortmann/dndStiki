package de.dnd.stiki.plugins.persistence.dndClass;

import de.dnd.stiki.domain.dndClass.DndClassEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpa;
import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpaRepository;
import de.dnd.stiki.plugins.persistence.basic.skill.SkillJpa;
import de.dnd.stiki.plugins.persistence.basic.skill.SkillJpaRepository;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.dndClass.classLevel.ClassLevelEntityToJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class DndClassEntityToJpaMapper extends AbstractEntityToJpaMapper<DndClassEntity, DndClassJpa> {

    @Autowired
    AbilityJpaRepository abilityJpaRepository;

    @Autowired
    SkillJpaRepository skillJpaRepository;

    @Autowired
    ClassLevelEntityToJpaMapper classLevelEntityToJpaMapper;

    @Autowired
    private TraitEntityToJpaMapper traitEntityToJpaMapper;

    @Override
    public DndClassJpa mapEntityToJpa(DndClassEntity entity) {

        DndClassJpa jpa = new DndClassJpa();
        jpa.setName(entity.getName());
        jpa.setHitDice(entity.getHitDice());

        List<AbilityJpa> jpaSavingThrowProficiencies = new ArrayList<>();
        for (String proficiency : entity.getSavingThrowProficiencies()) {
            Optional<AbilityJpa> optionalAbility = abilityJpaRepository.findById(proficiency);

            if (optionalAbility.isPresent()) {
                jpaSavingThrowProficiencies.add(optionalAbility.get());
            }
            else {
                throw new RuntimeException("Ability " + proficiency + " not found");
            }
        }
        jpa.setSavingThrowProficiencies(jpaSavingThrowProficiencies);

        List<SkillJpa> jpaSkillProficiencies = new ArrayList<>();
        for (String proficiency : entity.getSkillProficiencies()) {
            Optional<SkillJpa> optionalSkill = skillJpaRepository.findById(proficiency);
            if (optionalSkill.isPresent()) {
                jpaSkillProficiencies.add(optionalSkill.get());
            }
            else {
                throw new RuntimeException("Skill " + proficiency + " not found");
            }
        }
        jpa.setSkillProficiencies(jpaSkillProficiencies);

        jpa.setNumberOfSkillProficiencies(entity.getNumberOfSkillProficiencies());
        jpa.setArmorProficiencies(getStringFromList(entity.getArmorProficiencies()));
        jpa.setWeaponProficiencies(getStringFromList(entity.getWeaponProficiencies()));
        jpa.setToolProficiencies(getStringFromList(entity.getToolProficiencies()));
        jpa.setWealth(entity.getWealth());

        if (entity.getSpellAbility() != null) {

            Optional<AbilityJpa> optionalAbility = abilityJpaRepository.findById(entity.getSpellAbility());

            if (optionalAbility.isPresent()) {
                jpa.setSpellAbility(optionalAbility.get());
            }
            else {
                throw new RuntimeException("SpellAbility " + entity.getSpellAbility() + " not found");
            }
        }

        jpa.setClassLevels(classLevelEntityToJpaMapper.mapEntitiesToJpa(entity.getClassLevels()));

        jpa.setTraits(traitEntityToJpaMapper.mapEntitiesToJpa(entity.getTraits()));

        return jpa;
    }
}
