package de.dnd.stiki.plugins.persistence.compendium;

import de.dnd.stiki.domain.compendium.CompendiumEntity;
import org.springframework.stereotype.Component;

@Component
public class CompendiumJpaToEntityMapper {

    public CompendiumEntity mapJpaToEntity(CompendiumJpa jpa) {
       CompendiumEntity entity = new CompendiumEntity();
       entity.setId(jpa.getId());
       entity.setFileName(jpa.getFileName());
       entity.setXmlContent(jpa.getXmlContent());

       return entity;
    }
}
