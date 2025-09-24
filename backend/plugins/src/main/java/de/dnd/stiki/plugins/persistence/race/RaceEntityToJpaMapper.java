package de.dnd.stiki.plugins.persistence.race;

import de.dnd.stiki.domain.race.RaceEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpa;
import de.dnd.stiki.plugins.persistence.basic.ability.AbilityJpaRepository;
import de.dnd.stiki.plugins.persistence.basic.trait.TraitEntityToJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class RaceEntityToJpaMapper extends AbstractEntityToJpaMapper<RaceEntity, RaceJpa> {

    @Autowired
    private AbilityJpaRepository abilityJpaRepository;

    @Autowired
    private TraitEntityToJpaMapper traitEntityToJpaMapper;

    @Override
    public RaceJpa mapEntityToJpa(RaceEntity entity) {

        RaceJpa jpa = new RaceJpa();
        jpa.setName(entity.getName());
        jpa.setSize(entity.getSize());
        jpa.setSpeed(entity.getSpeed());
        jpa.setAbility(entity.getAbility());

        if (entity.getSpellAbility() != null) {

            Optional<AbilityJpa> optionalAbility = abilityJpaRepository.findById(entity.getSpellAbility());

            if (optionalAbility.isPresent()) {
                jpa.setSpellAbility(optionalAbility.get());
            }
            else {
                throw new RuntimeException("SpellAbility " + entity.getSpellAbility() + " not found");
            }
        }

        jpa.setTraits(traitEntityToJpaMapper.mapEntitiesToJpa(entity.getTraits()));

        return jpa;
    }
}
