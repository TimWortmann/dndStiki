package de.dnd.stiki.adapters.character.characterAttack;

import de.dnd.stiki.domain.character.CharacterAbilityEntity;
import de.dnd.stiki.domain.character.CharacterAttackEntity;
import de.dnd.stiki.domain.helper.AttackHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterAttackEntityToDtoMapper {

    @Autowired
    private AttackHelper attackHelper;


    public CharacterAttackDto mapEntityToDto(CharacterAttackEntity entity, List<CharacterAbilityEntity> abilities, int proficiencyBonus) {
        CharacterAttackDto dto = new CharacterAttackDto();
        dto.setName(entity.getName());

        if (entity.getModifiedHitBonus() != null || entity.getModifiedDamageRoll() != null) {
            dto.setHitBonus(entity.getModifiedHitBonus());
            dto.setDamageRoll(entity.getModifiedDamageRoll());
            return dto;
        }

        dto.setHitBonus(attackHelper.getFinalHitBonus(entity, abilities, proficiencyBonus));
        dto.setDamageRoll(attackHelper.getFinalDamageRoll(entity, abilities));
        return dto;
    }

    public List<CharacterAttackDto> mapEntitiesToDtos(List<CharacterAttackEntity> entities, List<CharacterAbilityEntity> abilities, int proficiencyBonus) {
        if (entities == null) {
            return null;
        }

        List<CharacterAttackDto> dtos = new ArrayList<>();

        for (CharacterAttackEntity entity : entities) {
            dtos.add(mapEntityToDto(entity,  abilities, proficiencyBonus));
        }

        return dtos;
    }
}
