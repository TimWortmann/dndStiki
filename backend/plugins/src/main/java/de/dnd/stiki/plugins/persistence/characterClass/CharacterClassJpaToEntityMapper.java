package de.dnd.stiki.plugins.persistence.characterClass;

import de.dnd.stiki.domain.characterClass.CharacterClassEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpa;
import de.dnd.stiki.plugins.persistence.basic.skill.SkillJpa;
import de.dnd.stiki.plugins.persistence.characterClass.classLevel.ClassLevelJpaToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterClassJpaToEntityMapper extends AbstractJpaToEntityMapper<CharacterClassJpa, CharacterClassEntity> {

    @Autowired
    private ClassLevelJpaToEntityMapper classLevelJpaToEntityMapper;

    @Override
    public CharacterClassEntity mapJpaToEntity(CharacterClassJpa jpa) {

        CharacterClassEntity entity = new CharacterClassEntity();
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
        entity.setArmorProficiencies(jpa.getArmorProficiencies());
        entity.setWeaponProficiencies(jpa.getWeaponProficiencies());
        entity.setToolProficiencies(jpa.getToolProficiencies());
        entity.setWealth(jpa.getWealth());
        entity.setSpellAbility(jpa.getSpellAbility().getName());

        entity.setClassLevels(classLevelJpaToEntityMapper.mapJpasToEntities(jpa.getClassLevels()));

        return entity;
    }
}
