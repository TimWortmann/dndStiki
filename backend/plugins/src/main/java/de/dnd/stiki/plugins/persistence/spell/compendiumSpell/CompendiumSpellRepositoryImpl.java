package de.dnd.stiki.plugins.persistence.spell.compendiumSpell;

import de.dnd.stiki.domain.spell.CompendiumSpellRepository;
import de.dnd.stiki.domain.spell.SpellEntity;
import de.dnd.stiki.plugins.persistence.spell.SpellEntityToJpaMapper;
import de.dnd.stiki.plugins.persistence.spell.SpellJpa;
import de.dnd.stiki.plugins.persistence.spell.SpellJpaToEntityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CompendiumSpellRepositoryImpl implements CompendiumSpellRepository {

    @Autowired
    private CompendiumSpellJpaRepository jpaRepository;

    @Autowired
    private SpellJpaToEntityMapper spellJpaToEntityMapper;

    @Autowired
    private SpellEntityToJpaMapper spellEntityToJpaMapper;

    @Override
    public List<SpellEntity> getAll() {
        List<CompendiumSpellJpa> compendiumSpellJpas = jpaRepository.findAll();
        List<SpellJpa> spellJpas = compendiumSpellJpas.stream().map(CompendiumSpellJpa::getSpell).toList();
        return spellJpaToEntityMapper.mapJpasToEntities(spellJpas);
    }

    @Override
    public List<SpellEntity> save(List<SpellEntity> entities) {

        List<CompendiumSpellJpa> compendiumSpellJpas = new ArrayList<>();

        for (SpellEntity spellEntity : entities) {
            SpellJpa spellJpa = spellEntityToJpaMapper.mapEntityToJpa(spellEntity);
            CompendiumSpellJpa compendiumSpellJpa = new CompendiumSpellJpa();
            compendiumSpellJpa.setSpell(spellJpa);
            compendiumSpellJpas.add(compendiumSpellJpa);
        }

        compendiumSpellJpas = jpaRepository.saveAll(compendiumSpellJpas);
        List<SpellJpa> spellJpas = compendiumSpellJpas.stream().map(CompendiumSpellJpa::getSpell).toList();
        return spellJpaToEntityMapper.mapJpasToEntities(spellJpas);
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }
}
