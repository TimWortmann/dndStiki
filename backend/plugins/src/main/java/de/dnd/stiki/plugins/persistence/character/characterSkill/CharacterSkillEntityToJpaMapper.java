package de.dnd.stiki.plugins.persistence.character.characterSkill;

import de.dnd.stiki.domain.character.CharacterSkillEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterSkillEntityToJpaMapper extends AbstractEntityToJpaMapper<CharacterSkillEntity, CharacterSkillJpa> {

    @Override
    public CharacterSkillJpa mapEntityToJpa(CharacterSkillEntity entity) {
        CharacterSkillJpa jpa = new CharacterSkillJpa();
        jpa.setId(entity.getId());
        jpa.setName(entity.getName().toString());
        jpa.setAbility(entity.getAbility().toString());
        jpa.setBasicModifier(entity.getBasicModifier());
        jpa.setProficiency(entity.getProficiency());
        return jpa;
    }
}
