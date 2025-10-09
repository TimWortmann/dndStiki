package de.dnd.stiki.plugins.persistence.character.characterAbility;

import de.dnd.stiki.domain.character.CharacterAbilityEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class CharacterAbilityEntityToJpaMapper extends AbstractEntityToJpaMapper<CharacterAbilityEntity, CharacterAbilityJpa> {

    @Override
    public CharacterAbilityJpa mapEntityToJpa(CharacterAbilityEntity entity) {
        CharacterAbilityJpa jpa = new CharacterAbilityJpa();
        jpa.setId(entity.getId());
        jpa.setAbility(entity.getAbility());
        jpa.setBasicScore(entity.getBasicScore());
        jpa.setBonus(entity.getBonus());
        return jpa;
    }
}
