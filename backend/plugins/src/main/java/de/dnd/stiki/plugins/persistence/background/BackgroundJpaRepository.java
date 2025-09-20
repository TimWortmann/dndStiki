package de.dnd.stiki.plugins.persistence.background;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BackgroundJpaRepository extends JpaRepository<BackgroundJpa,String> {
}
