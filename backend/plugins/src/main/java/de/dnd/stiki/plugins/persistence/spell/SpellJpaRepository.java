package de.dnd.stiki.plugins.persistence.spell;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpellJpaRepository extends JpaRepository<SpellJpa, String> {
}
