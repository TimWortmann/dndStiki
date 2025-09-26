package de.dnd.stiki.plugins.persistence.spell;

import de.dnd.stiki.domain.spell.SpellEntity;
import de.dnd.stiki.domain.spell.SpellRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SpellRepositoryImpl implements SpellRepository {

    @Autowired
    private SpellJpaRepository jpaRepository;

    @Autowired
    private SpellJpaToEntityMapper jpaToEntityMapper;

    @Autowired
    private SpellEntityToJpaMapper entityToJpaMapper;

    @Override
    public List<SpellEntity> getAllSpells() {
        List<SpellJpa> jpaList = jpaRepository.findAll();

        return jpaToEntityMapper.mapJpasToEntities(jpaList);
    }

    @Override
    public List<SpellEntity> createSpells(List<SpellEntity> entities) {
        List<SpellJpa> jpaList = entityToJpaMapper.mapEntitiesToJpa(entities);

        return jpaToEntityMapper.mapJpasToEntities(jpaRepository.saveAll(jpaList));
    }

    @Override
    public void deleteAllSpells() {
        jpaRepository.deleteAll();
    }
}
