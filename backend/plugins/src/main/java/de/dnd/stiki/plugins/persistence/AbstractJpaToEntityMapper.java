package de.dnd.stiki.plugins.persistence;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class AbstractJpaToEntityMapper <J, E> {

    public List<E> mapJpasToEntities(List<J> jpas) {
        if (jpas == null || jpas.isEmpty()) {
            return null;
        }

        List<E> entities = new ArrayList<>();

        for (J jpa : jpas) {
            entities.add(mapJpaToEntity(jpa));
        }

        return entities;
    }

    public abstract E mapJpaToEntity(J jpa);
}
