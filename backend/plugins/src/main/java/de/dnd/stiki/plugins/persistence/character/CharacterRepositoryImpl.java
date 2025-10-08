package de.dnd.stiki.plugins.persistence.character;

import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.character.CharacterRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CharacterRepositoryImpl implements CharacterRepository {

    @Autowired
    private CharacterJpaRepository jpaRepository;

    @Autowired
    private CharacterJpaToEntityMapper jpaToEntityMapper;

    @Autowired
    private CharacterEntityToJpaMapper entityToJpaMapper;

    @Override
    public List<CharacterEntity> getAll() {
        return jpaToEntityMapper.mapJpasToEntities(jpaRepository.findAll());
    }

    @Override
    public CharacterEntity create(String name) {

        CharacterJpa jpa = new CharacterJpa();
        jpa.setName(name);

        return jpaToEntityMapper.mapJpaToEntity(jpaRepository.save(jpa));
    }

    @Override
    public CharacterEntity save(CharacterEntity character) {

        CharacterJpa jpa = jpaRepository.save(entityToJpaMapper.mapEntityToJpa(character));

        return jpaToEntityMapper.mapJpaToEntity(jpa);
    }

    @Override
    public void delete(Long id) {
        jpaRepository.deleteById(id);
    }
}
