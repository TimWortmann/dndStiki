package de.dnd.stiki.adapters;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class AbstractEntityToDtoMapper<E, D> {

    public List<D> mapEntitiesToDtos(List<E> entities) {
        if (entities == null) {
            return null;
        }

        List<D> dtos = new ArrayList<>();

        for (E entity : entities) {
            dtos.add(mapEntityToDto(entity));
        }

        return dtos;
    }

    public abstract D mapEntityToDto(E entity);
}
