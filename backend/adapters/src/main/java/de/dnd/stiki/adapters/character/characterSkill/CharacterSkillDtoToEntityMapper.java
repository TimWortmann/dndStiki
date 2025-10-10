package de.dnd.stiki.adapters.character.characterSkill;

import de.dnd.stiki.adapters.AbstractDtoToEntityMapper;
import de.dnd.stiki.domain.character.CharacterSkillEntity;
import de.dnd.stiki.domain.enums.AbilityType;
import de.dnd.stiki.domain.enums.SkillType;
import org.springframework.stereotype.Component;

@Component
public class CharacterSkillDtoToEntityMapper extends AbstractDtoToEntityMapper<CharacterSkillDto, CharacterSkillEntity> {

    @Override
    public CharacterSkillEntity mapDtoToEntity(CharacterSkillDto dto) {
        CharacterSkillEntity entity = new CharacterSkillEntity();
        entity.setId(dto.getId());
        entity.setName(SkillType.fromName(dto.getName()));
        entity.setAbility(AbilityType.fromName(dto.getAbility()));
        entity.setBasicModifier(dto.getBasicModifier());
        entity.setProficiency(dto.getProficiency());
        return entity;
    }
}
