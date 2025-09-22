package de.dnd.stiki.plugins.persistence.compendium;

import de.dnd.stiki.domain.compendium.CompendiumEntity;
import org.springframework.stereotype.Component;

@Component
public class CompendiumEntityToJpaMapper {

    public CompendiumJpa mapEntityToJpa(CompendiumEntity entity) {
        CompendiumJpa jpa = new CompendiumJpa();
        jpa.setId(entity.getId());
        jpa.setFileName(entity.getFileName());
        jpa.setXmlContent(entity.getXmlContent());

        return jpa;
    }
}
