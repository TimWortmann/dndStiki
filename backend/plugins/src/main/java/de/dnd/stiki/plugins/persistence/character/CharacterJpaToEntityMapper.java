package de.dnd.stiki.plugins.persistence.character;

import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.trait.TraitEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitJpaToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterJpaToEntityMapper extends AbstractJpaToEntityMapper<CharacterJpa, CharacterEntity> {

    @Autowired
    private TraitJpaToEntityMapper traitJpaToEntityMapper;

    @Override
    public CharacterEntity mapJpaToEntity(CharacterJpa jpa) {

        CharacterEntity entity = new CharacterEntity();

        entity.setId(jpa.getId());
        entity.setName(jpa.getName());
        entity.setDndClass(jpa.getDndClass());
        entity.setBackground(jpa.getBackground());
        entity.setRace(jpa.getRace());
        entity.setMaxHealth(jpa.getMaxHealth());
        entity.setCurrentHealth(jpa.getCurrentHealth());
        entity.setHitDice(jpa.getHitDice());
        entity.setMaxHitDice(jpa.getMaxHitDice());
        entity.setCurrentHitDice(jpa.getCurrentHitDice());
        entity.setArmorClass(jpa.getArmorClass());
        entity.setSpeed(jpa.getSpeed());
        entity.setPassivePerception(jpa.getPassivePerception());
        entity.setProficiencyBonus(jpa.getProficiencyBonus());

        setEntityTraits(jpa, entity);

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
}
