package de.dnd.stiki.plugins.persistence.spell.compendiumSpell;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompendiumSpellJpaRepository extends JpaRepository<CompendiumSpellJpa, Long> {
}
