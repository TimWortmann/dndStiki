package de.dnd.stiki.plugins.persistence.compendium;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompendiumJpaRepository extends JpaRepository<CompendiumJpa, Long> {
}
