package de.dnd.stiki.plugins.persistence.basic.skill;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillJpaRepository extends JpaRepository<SkillJpa, String> {
}
