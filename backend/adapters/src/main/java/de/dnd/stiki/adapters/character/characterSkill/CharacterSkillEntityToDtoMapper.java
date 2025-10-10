package de.dnd.stiki.adapters.character.characterSkill;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.character.CharacterSkillEntity;
import org.springframework.stereotype.Component;

@Component
public class CharacterSkillEntityToDtoMapper extends AbstractEntityToDtoMapper<CharacterSkillEntity, CharacterSkillDto> {

    @Override
    public CharacterSkillDto mapEntityToDto(CharacterSkillEntity entity) {
        CharacterSkillDto dto = new CharacterSkillDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName().toString());
        dto.setAbility(entity.getAbility().toString());
        dto.setBasicModifier(entity.getBasicModifier());
        dto.setProficiency(entity.getProficiency());
        return dto;
    }
}
