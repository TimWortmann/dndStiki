package de.dnd.stiki.plugins.persistence.characterClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterClassJpaRepository extends JpaRepository<CharacterClassJpa, String> {
}
