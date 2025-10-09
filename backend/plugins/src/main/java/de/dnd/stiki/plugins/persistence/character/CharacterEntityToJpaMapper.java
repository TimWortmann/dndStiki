package de.dnd.stiki.plugins.persistence.character;

import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.character.CharacterTraitType;
import de.dnd.stiki.domain.trait.TraitEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.character.characterAbility.CharacterAbilityEntityToJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static de.dnd.stiki.domain.character.CharacterTraitType.*;

@Component
public class CharacterEntityToJpaMapper extends AbstractEntityToJpaMapper<CharacterEntity, CharacterJpa> {

    @Autowired
    private CharacterAbilityEntityToJpaMapper abilityEntityToJpaMapper;

    @Autowired
    private TraitEntityToJpaMapper traitEntityToJpaMapper;

    @Override
    public CharacterJpa mapEntityToJpa(CharacterEntity entity) {

        CharacterJpa jpa = new CharacterJpa();
        jpa.setId(entity.getId());
        jpa.setName(entity.getName());
        jpa.setLevel(entity.getLevel());
        jpa.setDndClass(entity.getDndClass());
        jpa.setBackground(entity.getBackground());
        jpa.setRace(entity.getRace());
        jpa.setMaxHealth(entity.getMaxHealth());
        jpa.setCurrentHealth(entity.getCurrentHealth());
        jpa.setHitDice(entity.getHitDice());
        jpa.setMaxHitDice(entity.getMaxHitDice());
        jpa.setCurrentHitDice(entity.getCurrentHitDice());
        jpa.setArmorClass(entity.getArmorClass());
        jpa.setSpeed(entity.getSpeed());
        jpa.setPassivePerception(entity.getPassivePerception());
        jpa.setProficiencyBonus(entity.getProficiencyBonus());
        jpa.setAbilities(abilityEntityToJpaMapper.mapEntitiesToJpa(entity.getAbilities()));

        setCharacterTraits(entity, jpa);

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
