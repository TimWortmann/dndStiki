package de.dnd.stiki.plugins.persistence.dndClass.counter;

import de.dnd.stiki.domain.dndClass.counter.CounterEntity;
import de.dnd.stiki.plugins.persistence.AbstractJpaToEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class CounterJpaToEntityMapper extends AbstractJpaToEntityMapper<CounterJpa, CounterEntity> {


    @Override
    public CounterEntity mapJpaToEntity(CounterJpa jpa) {

        CounterEntity entity = new CounterEntity();
        entity.setId(jpa.getId());
        entity.setName(jpa.getName());
        entity.setReset(jpa.getReset());

        return entity;
    }
}
