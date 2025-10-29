package de.dnd.stiki.plugins.persistence.character;

import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.enums.CharacterTraitType;
import de.dnd.stiki.domain.trait.TraitEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.character.characterAbility.CharacterAbilityEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.character.characterArmor.CharacterArmorEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.character.characterItem.CharacterItemEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.character.characterShield.CharacterShieldEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.character.characterSkill.CharacterSkillEntityToJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static de.dnd.stiki.domain.enums.CharacterTraitType.*;

@Component
public class CharacterEntityToJpaMapper extends AbstractEntityToJpaMapper<CharacterEntity, CharacterJpa> {

    @Autowired
    private CharacterAbilityEntityToJpaMapper abilityEntityToJpaMapper;

    @Autowired
    private CharacterSkillEntityToJpaMapper skillEntityToJpaMapper;

    @Autowired
    private CharacterItemEntityToJpaMapper itemJpaToEntityMapper;

    @Autowired
    private TraitEntityToJpaMapper traitEntityToJpaMapper;

    @Autowired
    private CharacterShieldEntityToJpaMapper shieldEntityToJpaMapper;

    @Autowired
    private CharacterArmorEntityToJpaMapper armorEntityToJpaMapper;

    @Override
    public CharacterJpa mapEntityToJpa(CharacterEntity entity) {

        CharacterJpa jpa = new CharacterJpa();
        jpa.setId(entity.getId());
        jpa.setName(entity.getName());
        jpa.setLevel(entity.getLevel());
        jpa.setDndClass(entity.getDndClass());
        jpa.setDndSubclass(entity.getDndSubclass());
        if (entity.getSpellcastingAbility() != null) {
            jpa.setSpellcastingAbility(entity.getSpellcastingAbility().getName());
        }
        jpa.setBackground(entity.getBackground());
        jpa.setRace(entity.getRace());
        jpa.setMaxHealth(entity.getMaxHealth());
        jpa.setCurrentHealth(entity.getCurrentHealth());
        jpa.setHitDice(entity.getHitDice());
        jpa.setMaxHitDice(entity.getMaxHitDice());
        jpa.setCurrentHitDice(entity.getCurrentHitDice());
        jpa.setArmorClass(entity.getArmorClass());
        jpa.setModifiedArmorClass(entity.getModifiedArmorClass());
        jpa.setSpeed(entity.getSpeed());
        jpa.setPassivePerception(entity.getPassivePerception());
        jpa.setProficiencyBonus(entity.getProficiencyBonus());
        jpa.setAbilities(abilityEntityToJpaMapper.mapEntitiesToJpa(entity.getAbilities()));
        jpa.setSkills(skillEntityToJpaMapper.mapEntitiesToJpa(entity.getSkills()));
        jpa.setItems(itemJpaToEntityMapper.mapEntitiesToJpa(entity.getItems()));
        setCharacterTraits(entity, jpa);
        jpa.setEquippedShield(shieldEntityToJpaMapper.mapEntityToJpa(entity.getEquippedShield()));
        jpa.setEquippedArmor(armorEntityToJpaMapper.mapEntityToJpa(entity.getEquippedArmor()));
        jpa.setWeaponProficiencies(getStringFromList(entity.getWeaponProficiencies()));

        return jpa;
    }

    private void setCharacterTraits(CharacterEntity entity, CharacterJpa jpa) {
        List<CharacterTraitJpa> characterTraitJpaList = new ArrayList<>();

        if (entity.getClassFeatures() != null) {
            for (TraitEntity trait : entity.getClassFeatures()) {
                characterTraitJpaList.add(createCharacterTraitJpa(trait, DND_CLASS));
            }
        }

        if (entity.getBackgroundTraits() != null) {
            for (TraitEntity trait : entity.getBackgroundTraits()) {
                characterTraitJpaList.add(createCharacterTraitJpa(trait, BACKGROUND));
            }
        }

        if (entity.getRaceTraits() != null) {
            for (TraitEntity trait : entity.getRaceTraits()) {
                characterTraitJpaList.add(createCharacterTraitJpa(trait, RACE));
            }
        }

        if (entity.getFeats() != null) {
            for (TraitEntity trait : entity.getFeats()) {
                characterTraitJpaList.add(createCharacterTraitJpa(trait, FEAT));
            }
        }

        jpa.setTraits(characterTraitJpaList);
    }

    private CharacterTraitJpa createCharacterTraitJpa(TraitEntity trait, CharacterTraitType characterTraitType) {
        CharacterTraitJpa characterTraitJpa = new CharacterTraitJpa();
        characterTraitJpa.setTraitType(characterTraitType);
        characterTraitJpa.setTrait(traitEntityToJpaMapper.mapEntityToJpa(trait));
        return characterTraitJpa;
    }
}
