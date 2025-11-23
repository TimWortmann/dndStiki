package de.dnd.stiki.plugins.persistence;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class AbstractEntityToJpaMapper<E, J> {

    public List<J> mapEntitiesToJpa(List<E> entities) {
        if (entities == null) {
            return null;
        }

        List<J> jpas = new ArrayList<>();

        for (E entity : entities) {
            jpas.add(mapEntityToJpa(entity));
        }

        return jpas;
    }

    public abstract J mapEntityToJpa(E entity);

    protected <T> String getStringFromList(List<T> list) {

        if (list == null || list.isEmpty()) {
            return null;
        }

        StringBuilder sb = new StringBuilder();
        for (T element : list) {
            if (sb.isEmpty()) {
                sb.append(element);
            } else {
                sb.append(",").append(element);
            }
        }
        return sb.toString();
    }
}
