package de.dnd.stiki.plugins.persistence.race;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RaceJpaRepository extends JpaRepository<RaceJpa, String> {
}
