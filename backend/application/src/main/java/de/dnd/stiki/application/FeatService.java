package de.dnd.stiki.application;

import de.dnd.stiki.adapters.feat.FeatDto;
import de.dnd.stiki.adapters.feat.FeatEntityToDtoMapper;
import de.dnd.stiki.domain.feat.FeatEntity;
import de.dnd.stiki.domain.feat.FeatRepository;
import org.springframework.stereotype.Service;

@Service
public class FeatService extends AbstractService<FeatEntity, FeatDto, FeatRepository, FeatEntityToDtoMapper> {
}
