package de.dnd.stiki.plugins.persistence.basic.trait;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TraitJpaRepository extends JpaRepository<TraitJpa, Long> {
}
