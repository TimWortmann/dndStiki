package de.dnd.stiki.plugins.persistence.background;

import de.dnd.stiki.domain.background.BackgroundEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.skill.SkillJpa;
import de.dnd.stiki.plugins.persistence.skill.SkillJpaRepository;
import de.dnd.stiki.plugins.persistence.trait.TraitEntityToJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BackgroundEntityToJpaMapper extends AbstractEntityToJpaMapper<BackgroundEntity, BackgroundJpa> {

    @Autowired
    private SkillJpaRepository skillJpaRepository;

    @Autowired
    private TraitEntityToJpaMapper traitEntityToJpaMapper;

    @Override
    public BackgroundJpa mapEntityToJpa(BackgroundEntity entity) {

        BackgroundJpa jpa = new BackgroundJpa();
        jpa.setName(entity.getName());

        List<SkillJpa> proficiencies = new ArrayList<>();
        if (entity.getProficiencies() != null) {
            for (String proficiency : entity.getProficiencies()) {

                Optional<SkillJpa> optionalSkill = skillJpaRepository.findById(proficiency);
                if (optionalSkill.isPresent()) {
                    proficiencies.add(optionalSkill.get());
                }
                else {
                    throw new RuntimeException("Skill " + proficiency + " not found");
                }
            }
            jpa.setProficiencies(proficiencies);
        }

        jpa.setTraits(traitEntityToJpaMapper.mapEntitiesToJpa(entity.getTraits()));

        return jpa;
    }
}
