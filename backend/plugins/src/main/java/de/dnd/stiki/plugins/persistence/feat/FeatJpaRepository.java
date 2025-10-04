package de.dnd.stiki.plugins.persistence.feat;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FeatJpaRepository extends JpaRepository<FeatJpa, String> {
}
