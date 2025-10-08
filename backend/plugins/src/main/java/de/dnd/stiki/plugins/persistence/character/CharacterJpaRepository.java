package de.dnd.stiki.plugins.persistence.character;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CharacterJpaRepository extends JpaRepository<CharacterJpa,Long> {
}
