package de.dnd.stiki.plugins.persistence.character.characterAttack;

import de.dnd.stiki.domain.character.CharacterAttackEntity;
import de.dnd.stiki.domain.enums.AbilityType;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterAttackJpaToEntityMapper extends AbstractJpaToEntityMapper<CharacterAttackJpa, CharacterAttackEntity> {

    @Override
    public CharacterAttackEntity mapJpaToEntity(CharacterAttackJpa jpa) {
        CharacterAttackEntity entity = new CharacterAttackEntity();
        entity.setName(jpa.getName());
        entity.setBaseDamageRoll(jpa.getBaseDamageRoll());
        entity.setAbility(AbilityType.fromName(jpa.getAbility()));
        entity.setProficient(jpa.isProficient());
        return entity;
    }
}
