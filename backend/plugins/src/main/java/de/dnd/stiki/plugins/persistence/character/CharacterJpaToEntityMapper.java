package de.dnd.stiki.plugins.persistence.character;

import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.enums.AbilityType;
import de.dnd.stiki.domain.reader.SubclassReader;
import de.dnd.stiki.domain.trait.TraitEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.character.characterAbility.CharacterAbilityJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.character.characterArmor.CharacterArmorJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.character.characterAttack.CharacterAttackJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.character.characterItem.CharacterItemJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.character.characterShield.CharacterShieldJpaToEntiyMapper;
import de.dnd.stiki.plugins.persistence.character.characterSkill.CharacterSkillJpaToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterJpaToEntityMapper extends AbstractJpaToEntityMapper<CharacterJpa, CharacterEntity> {

    @Autowired
    private CharacterAbilityJpaToEntityMapper  abilityJpaToEntityMapper;

    @Autowired
    private CharacterSkillJpaToEntityMapper skillJpaToEntityMapper;

    @Autowired
    private CharacterItemJpaToEntityMapper itemJpaToEntityMapper;

    @Autowired
    private TraitJpaToEntityMapper traitJpaToEntityMapper;

    @Autowired
    private SubclassReader subclassReader;

    @Autowired
    private CharacterShieldJpaToEntiyMapper shieldJpaToEntiyMapper;

    @Autowired
    private CharacterArmorJpaToEntityMapper armorJpaToEntityMapper;

    @Autowired
    private CharacterAttackJpaToEntityMapper attackJpaToEntityMapper;

    @Override
    public CharacterEntity mapJpaToEntity(CharacterJpa jpa) {

        CharacterEntity entity = new CharacterEntity();

        entity.setId(jpa.getId());
        entity.setName(jpa.getName());
        entity.setLevel(jpa.getLevel());
        entity.setDndClass(jpa.getDndClass());
        entity.setDndSubclass(jpa.getDndSubclass());
        entity.setSpellcastingAbility(AbilityType.fromName(jpa.getSpellcastingAbility()));
        entity.setBackground(jpa.getBackground());
        entity.setRace(jpa.getRace());
        entity.setMaxHealth(jpa.getMaxHealth());
        entity.setCurrentHealth(jpa.getCurrentHealth());
        entity.setHitDice(jpa.getHitDice());
        entity.setMaxHitDice(jpa.getMaxHitDice());
        entity.setCurrentHitDice(jpa.getCurrentHitDice());
        entity.setArmorClass(jpa.getArmorClass());
        entity.setModifiedArmorClass(jpa.getModifiedArmorClass());
        entity.setSpeed(jpa.getSpeed());
        entity.setPassivePerception(jpa.getPassivePerception());
        entity.setProficiencyBonus(jpa.getProficiencyBonus());
        entity.setAbilities(abilityJpaToEntityMapper.mapJpasToEntities(jpa.getAbilities()));
        entity.setSkills(skillJpaToEntityMapper.mapJpasToEntities(jpa.getSkills()));
        entity.setItems(itemJpaToEntityMapper.mapJpasToEntities(jpa.getItems()));
        setEntityTraits(jpa, entity);
        entity.setEquippedShield(shieldJpaToEntiyMapper.mapJpaToEntity(jpa.getEquippedShield()));
        entity.setEquippedArmor(armorJpaToEntityMapper.mapJpaToEntity(jpa.getEquippedArmor()));
        entity.setWeaponProficiencies(getListFromString(jpa.getWeaponProficiencies()));
        entity.setAttacks(attackJpaToEntityMapper.mapJpasToEntities(jpa.getAttacks()));

        entity.setDndSubclasses(getDndSubclasses(entity.getClassFeatures()));

        return entity;
    }

    private void setEntityTraits(CharacterJpa jpa, CharacterEntity entity) {
        List<TraitEntity> classFeatures = new ArrayList<>();
        List<TraitEntity> backgroundTraits= new ArrayList<>();
        List<TraitEntity> raceTraits= new ArrayList<>();
        List<TraitEntity> feats= new ArrayList<>();

        if (jpa.getTraits() != null) {
            for (CharacterTraitJpa characterTraitJpa : jpa.getTraits()) {
                TraitEntity traitEntity = traitJpaToEntityMapper.mapJpaToEntity(characterTraitJpa.getTrait());
                switch (characterTraitJpa.getTraitType()) {
                    case DND_CLASS:
                        classFeatures.add(traitEntity);
                        break;
                    case BACKGROUND:
                        backgroundTraits.add(traitEntity);
                        break;
                    case RACE:
                        raceTraits.add(traitEntity);
                        break;
                    case FEAT:
                        feats.add(traitEntity);
                        break;
                    default:
                        throw new IllegalArgumentException("Unknown trait type: " + characterTraitJpa.getTraitType());
                }
            }
        }
        entity.setClassFeatures(classFeatures);
        entity.setBackgroundTraits(backgroundTraits);
        entity.setRaceTraits(raceTraits);
        entity.setFeats(feats);
    }

    private List<String> getDndSubclasses(List<TraitEntity> classFeatures) {
        // Extract all feature names
        List<String> featureNames = classFeatures.stream()
                .map(TraitEntity::getName)
                .toList();

        // Delegate parsing to the shared method
        return subclassReader.getDndSubclasses(featureNames);
    }
}
