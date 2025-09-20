package de.dnd.stiki.plugins.persistence.trait;

import de.dnd.stiki.domain.trait.TraitEntity;
import de.dnd.stiki.domain.trait.TraitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TraitRepositoryImpl implements TraitRepository {

    @Autowired
    private TraitJpaRepository jpaRepository;

    @Autowired
    private TraitJpaToEntityMapper jpaToEntityMapper;

    @Autowired TraitEntityToJpaMapper entityToJpaMapper;

    @Override
    public List<TraitEntity> getAllTraits() {
        List<TraitJpa> jpaList = jpaRepository.findAll();

        return jpaToEntityMapper.mapJpasToEntities(jpaList);
    }

    @Override
    public TraitEntity createTrait(TraitEntity traitEntity) {
        TraitJpa jpa = entityToJpaMapper.mapEntityToJpa(traitEntity);

        return jpaToEntityMapper.mapJpaToEntity(jpaRepository.save(jpa));
    }

    @Override
    public void deleteTrait(Long id) {
        jpaRepository.deleteById(id);
    }
}
