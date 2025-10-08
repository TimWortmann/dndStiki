package de.dnd.stiki.domain.character;

import java.util.List;

public interface CharacterRepository {

    public List<CharacterEntity> getAll();

    public CharacterEntity get(Long id);

    public CharacterEntity create(String name);

    public CharacterEntity save(CharacterEntity character);

    public void delete(Long id);
}
