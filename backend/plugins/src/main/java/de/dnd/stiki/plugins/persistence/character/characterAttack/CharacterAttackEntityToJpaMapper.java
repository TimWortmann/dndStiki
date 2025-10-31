package de.dnd.stiki.plugins.persistence.character.characterAttack;

import de.dnd.stiki.domain.character.CharacterAttackEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterAttackEntityToJpaMapper extends AbstractEntityToJpaMapper<CharacterAttackEntity, CharacterAttackJpa> {

    @Override
    public CharacterAttackJpa mapEntityToJpa(CharacterAttackEntity entity) {
        CharacterAttackJpa jpa = new CharacterAttackJpa();
        jpa.setName(entity.getName());
        jpa.setBaseDamageRoll(entity.getBaseDamageRoll());
        jpa.setAbility(entity.getAbility().getName());
        jpa.setProficient(entity.isProficient());
        jpa.setModifiedHitBonus(entity.getModifiedHitBonus());
        jpa.setModifiedDamageRoll(entity.getModifiedDamageRoll());
        return jpa;
    }
}
