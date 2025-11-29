package de.dnd.stiki.plugins.persistence.character.characterSpell;

import de.dnd.stiki.domain.spell.SpellEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import de.dnd.stiki.plugins.persistence.spell.SpellJpaToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterSpellJpaToEntityMapper extends AbstractJpaToEntityMapper<CharacterSpellJpa, SpellEntity> {

    @Autowired
    private SpellJpaToEntityMapper spellJpaToEntityMapper;

    @Override
    public SpellEntity mapJpaToEntity(CharacterSpellJpa jpa) {
        SpellEntity spellEntity =  spellJpaToEntityMapper.mapJpaToEntity(jpa.getSpell());;
        return spellEntity;
    }
}
