package de.dnd.stiki.plugins.persistence.character.characterAbility;

import de.dnd.stiki.domain.character.CharacterAbilityEntity;
import de.dnd.stiki.domain.enums.AbilityType;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterAbilityJpaToEntityMapper extends AbstractJpaToEntityMapper<CharacterAbilityJpa, CharacterAbilityEntity> {

    @Override
    public CharacterAbilityEntity mapJpaToEntity(CharacterAbilityJpa jpa) {

        CharacterAbilityEntity entity = new CharacterAbilityEntity();
        entity.setId(jpa.getId());
        entity.setName(AbilityType.fromName(jpa.getName()));
        entity.setBasicScore(jpa.getBasicScore());
        entity.setBonus(jpa.getBonus());
        entity.setSavingThrowProficiency(jpa.getSavingThrowProficiency());
        return entity;
    }
}
