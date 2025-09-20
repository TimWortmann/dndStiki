package de.dnd.stiki.plugins.persistence.trait;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraitJpaRepository extends JpaRepository<TraitJpa, Long> {
}
