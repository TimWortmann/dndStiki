package de.dnd.stiki.plugins.persistence.dndClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DndClassJpaRepository extends JpaRepository<DndClassJpa, String> {
}
