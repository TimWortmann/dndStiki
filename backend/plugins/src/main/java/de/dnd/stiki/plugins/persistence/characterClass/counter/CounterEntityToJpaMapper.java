package de.dnd.stiki.plugins.persistence.characterClass.counter;

import de.dnd.stiki.domain.characterClass.counter.CounterEntity;
import de.dnd.stiki.plugins.persistence.AbstractEntityToJpaMapper;
import org.springframework.stereotype.Component;

@Component
public class CounterEntityToJpaMapper extends AbstractEntityToJpaMapper<CounterEntity, CounterJpa> {

    @Override
    public CounterJpa mapEntityToJpa(CounterEntity entity) {

        CounterJpa jpa = new CounterJpa();
        jpa.setId(entity.getId());
        jpa.setName(entity.getName());
        jpa.setReset(entity.getReset());

        return jpa;
    }
}
