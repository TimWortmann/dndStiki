package de.dnd.stiki.plugins.persistence.dndClass;

import de.dnd.stiki.domain.dndClass.DndClassEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpa;
import de.dnd.stiki.plugins.persistence.basic.skill.SkillJpa;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.dndClass.classLevel.ClassLevelJpaToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class DndClassJpaToEntityMapper extends AbstractJpaToEntityMapper<DndClassJpa, DndClassEntity> {

    @Autowired
    private ClassLevelJpaToEntityMapper classLevelJpaToEntityMapper;

    @Autowired
    private TraitJpaToEntityMapper traitJpaToEntityMapper;

    @Override
    public DndClassEntity mapJpaToEntity(DndClassJpa jpa) {

        DndClassEntity entity = new DndClassEntity();
        entity.setName(jpa.getName());
        entity.setHitDice(jpa.getHitDice());

        List<String> entitySavingThrowProficiencies = new ArrayList<>();
        for (AbilityJpa ability : jpa.getSavingThrowProficiencies()) {
            entitySavingThrowProficiencies.add(ability.getName());
        }
        entity.setSavingThrowProficiencies(entitySavingThrowProficiencies);

        List<String> entitySkillProficiencies = new ArrayList<>();
        for (SkillJpa skill : jpa.getSkillProficiencies()) {
            entitySkillProficiencies.add(skill.getName());
        }
        entity.setSkillProficiencies(entitySkillProficiencies);

        entity.setNumberOfSkillProficiencies(jpa.getNumberOfSkillProficiencies());
        entity.setArmorProficiencies(getListFromString(jpa.getArmorProficiencies()));
        entity.setWeaponProficiencies(getListFromString(jpa.getWeaponProficiencies()));
        entity.setToolProficiencies(getListFromString(jpa.getToolProficiencies()));
        entity.setWealth(jpa.getWealth());

        if (jpa.getSpellAbility() != null) {
            entity.setSpellAbility(jpa.getSpellAbility().getName());
        }

        entity.setClassLevels(classLevelJpaToEntityMapper.mapJpasToEntities(jpa.getClassLevels()));

        entity.setTraits(traitJpaToEntityMapper.mapJpasToEntities(jpa.getTraits()));

        return entity;
    }
}
