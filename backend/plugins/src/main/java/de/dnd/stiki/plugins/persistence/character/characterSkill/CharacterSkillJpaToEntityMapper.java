package de.dnd.stiki.plugins.persistence.character.characterSkill;

import de.dnd.stiki.domain.character.CharacterSkillEntity;
import de.dnd.stiki.domain.enums.AbilityType;
import de.dnd.stiki.domain.enums.SkillType;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterSkillJpaToEntityMapper extends AbstractJpaToEntityMapper<CharacterSkillJpa, CharacterSkillEntity> {

    @Override
    public CharacterSkillEntity mapJpaToEntity(CharacterSkillJpa jpa) {
        CharacterSkillEntity entity = new CharacterSkillEntity();
        entity.setId(jpa.getId());
        entity.setName(SkillType.fromName(jpa.getName()));
        entity.setAbility(AbilityType.fromName(jpa.getAbility()));
        entity.setBasicModifier(jpa.getBasicModifier());
        entity.setProficiency(jpa.getProficiency());
        return entity;
    }
}
