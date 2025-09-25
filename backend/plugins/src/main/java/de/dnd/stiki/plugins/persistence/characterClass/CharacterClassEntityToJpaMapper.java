package de.dnd.stiki.plugins.persistence.characterClass;

import de.dnd.stiki.domain.characterClass.CharacterClassEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpa;
import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpaRepository;
import de.dnd.stiki.plugins.persistence.basic.skill.SkillJpa;
import de.dnd.stiki.plugins.persistence.basic.skill.SkillJpaRepository;
import de.dnd.stiki.plugins.persistence.characterClass.classLevel.ClassLevelEntityToJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class CharacterClassEntityToJpaMapper extends AbstractEntityToJpaMapper<CharacterClassEntity, CharacterClassJpa> {

    @Autowired
    AbilityJpaRepository abilityJpaRepository;

    @Autowired
    SkillJpaRepository skillJpaRepository;

    @Autowired
    ClassLevelEntityToJpaMapper classLevelEntityToJpaMapper;

    @Override
    public CharacterClassJpa mapEntityToJpa(CharacterClassEntity entity) {

        CharacterClassJpa jpa = new CharacterClassJpa();
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
        jpa.setArmorProficiencies(getConcatinatedProficiencies(entity.getArmorProficiencies()));
        jpa.setWeaponProficiencies(getConcatinatedProficiencies(entity.getWeaponProficiencies()));
        jpa.setToolProficiencies(getConcatinatedProficiencies(entity.getToolProficiencies()));
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

        return jpa;
    }

    private String getConcatinatedProficiencies(List<String> proficiencies) {
        StringBuilder sb = new StringBuilder();
        for (String proficiency : proficiencies) {
            if (sb.isEmpty()) {
                sb.append(proficiency);
            }
            else {
                sb.append(",").append(proficiency);
            }
        }
        return sb.toString();
    }
}
