package de.dnd.stiki.adapters.character.characterCreation;

import de.dnd.stiki.adapters.AbstractDtoToEntityMapper;
import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityDtoToEntityMapper;
import de.dnd.stiki.domain.character.CharacterEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterCreationDtoToEntityMapper extends AbstractDtoToEntityMapper<CharacterCreationDto, CharacterEntity> {

    @Autowired
    private CharacterAbilityDtoToEntityMapper abilityDtoToEntityMapper;

    @Override
    public CharacterEntity mapDtoToEntity(CharacterCreationDto dto) {
        CharacterEntity entity = new CharacterEntity();
        entity.setName(dto.getName());
        entity.setDndClass(dto.getDndClass());
        entity.setDndSubclass("No Subclass");
        entity.setBackground(dto.getBackground());
        entity.setRace(dto.getRace());
        entity.setAbilities(abilityDtoToEntityMapper.mapDtosToEntities(dto.getAbilities()));
        return entity;
    }
}
