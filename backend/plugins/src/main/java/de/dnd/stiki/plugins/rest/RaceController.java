package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.race.RaceDto;
import de.dnd.stiki.application.RaceService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/race")
public class RaceController extends AbstractController<RaceDto, RaceService> {
}
