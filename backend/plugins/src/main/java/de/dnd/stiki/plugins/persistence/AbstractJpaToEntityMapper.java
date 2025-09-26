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


    protected List<String> getListFromString(String concatinatedList) {
        if (concatinatedList == null) {
            return new ArrayList<>();
        }

        String[] stringArray = concatinatedList.split("\\s*,\\s*");
        return List.of(stringArray);
    }
}
