package de.dnd.stiki.domain;

import java.util.List;

public abstract interface AbstractRepository<E> {

    public List<E> getAll();

    public List<E> save(List<E> entities);

    public void deleteAll();
}
