package de.dnd.stiki.plugins.persistence.basic.ability;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AbilityJpaRepository extends JpaRepository<AbilityJpa, String> {
}
