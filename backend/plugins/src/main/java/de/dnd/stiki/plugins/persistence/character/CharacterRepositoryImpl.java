package de.dnd.stiki.plugins.persistence.character;

import de.dnd.stiki.domain.character.CharacterEntity;
import de.dnd.stiki.domain.character.CharacterRepository;
import de.dnd.stiki.plugins.persistence.character.characterAbility.CharacterAbilityJpa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

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
    public CharacterEntity get(Long id) {
        Optional<CharacterJpa> jpa = jpaRepository.findById(id);
        return jpa.map(characterJpa -> jpaToEntityMapper.mapJpaToEntity(characterJpa)).orElse(null);

    }

    @Override
    public CharacterEntity create(CharacterEntity character) {

        CharacterJpa jpa = entityToJpaMapper.mapEntityToJpa(character);

        List<CharacterAbilityJpa> abilities = jpa.getAbilities();

        jpa.setAbilities(null);
        jpa = jpaRepository.save(jpa);

        jpa.setAbilities(abilities);
        for (CharacterAbilityJpa abilityJpa : abilities) {
            abilityJpa.setCharacter(jpa);
        }

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
