package de.dnd.stiki.plugins.persistence;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
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


    protected List<String> getStringListFromString(String concatenatedList) {
        if (concatenatedList == null) {
            return new ArrayList<>();
        }

        // Split on commas that are NOT inside parentheses
        return Arrays.stream(concatenatedList.split(",(?![^()]*\\))"))
                .map(String::trim)
                .toList();
    }

    protected List<Integer> getIntegerListFromString(String concatenatedList) {

        if (concatenatedList == null || concatenatedList.isEmpty()) {
            return new ArrayList<>();
        }

        return Arrays.stream(concatenatedList.split(",(?![^()]*\\))"))
                .map(String::trim)
                .map(Integer::valueOf)
                .toList();
    }



}
