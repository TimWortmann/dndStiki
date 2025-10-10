package de.dnd.stiki.plugins.persistence;

import de.dnd.stiki.domain.AbstractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository
public abstract class AbstractRepositoryImpl<
        ENTITY,
        JPA,
        JPA_REPOSITORY extends JpaRepository<JPA, ? extends Serializable>,
        JPA_TO_ENTITY_MAPPER extends AbstractJpaToEntityMapper<JPA, ENTITY>,
        ENTITY_TO_JPA_MAPPER extends AbstractEntityToJpaMapper<ENTITY, JPA>
        > implements AbstractRepository<ENTITY> {

    @Autowired
    protected JPA_REPOSITORY jpaRepository;

    @Autowired
    protected JPA_TO_ENTITY_MAPPER jpaToEntityMapper;

    @Autowired
    protected ENTITY_TO_JPA_MAPPER entityToJpaMapper;


    @Override
    public List<ENTITY> getAll() {
        List<JPA> jpaList = jpaRepository.findAll();
        return jpaToEntityMapper.mapJpasToEntities(jpaList);
    }

    @Override
    public List<ENTITY> save(List<ENTITY> entities) {
        List<JPA> jpaList = entityToJpaMapper.mapEntitiesToJpa(entities);
        return jpaToEntityMapper.mapJpasToEntities(jpaRepository.saveAll(jpaList));
    }

    @Override
    public void deleteAll() {
        jpaRepository.deleteAll();
    }
}
