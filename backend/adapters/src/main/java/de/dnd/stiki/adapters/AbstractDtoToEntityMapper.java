package de.dnd.stiki.adapters;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public abstract class AbstractDtoToEntityMapper<D, E> {

    public List<E> mapDtosToEntities(List<D> dtos) {
        if (dtos == null) {
            return null;
        }

        List<E> entities = new ArrayList<>();

        for (D dto : dtos) {
            entities.add(mapDtoToEntity(dto));
        }

        return entities;
    }

    public abstract E mapDtoToEntity(D dto);
}
