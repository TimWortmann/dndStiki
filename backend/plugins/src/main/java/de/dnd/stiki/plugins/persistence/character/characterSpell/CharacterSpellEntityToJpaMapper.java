package de.dnd.stiki.plugins.persistence.character.characterSpell;

import de.dnd.stiki.domain.spell.SpellEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.spell.SpellEntityToJpaMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CharacterSpellEntityToJpaMapper extends AbstractEntityToJpaMapper<SpellEntity, CharacterSpellJpa> {

    @Autowired
    private SpellEntityToJpaMapper spellEntityToJpaMapper;

    @Override
    public CharacterSpellJpa mapEntityToJpa(SpellEntity entity) {
        CharacterSpellJpa spellJpa = new CharacterSpellJpa();
        spellJpa.setSpell(spellEntityToJpaMapper.mapEntityToJpa(entity));
        return spellJpa;
    }
}
