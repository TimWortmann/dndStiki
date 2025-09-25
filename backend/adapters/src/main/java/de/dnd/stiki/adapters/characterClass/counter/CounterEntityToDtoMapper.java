package de.dnd.stiki.adapters.characterClass.counter;

import de.dnd.stiki.adapters.AbstractEntityToDtoMapper;
import de.dnd.stiki.domain.characterClass.counter.CounterEntity;
import org.springframework.stereotype.Component;

@Component
public class CounterEntityToDtoMapper extends AbstractEntityToDtoMapper<CounterEntity, CounterDto> {

    @Override
    public CounterDto mapEntityToDto(CounterEntity entity) {

        CounterDto dto = new CounterDto();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setReset(entity.getReset());

        return dto;
    }
}
