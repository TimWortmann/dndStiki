package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.feat.FeatDto;
import de.dnd.stiki.application.FeatService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/feat")
public class FeatController extends AbstractController<FeatDto, FeatService> {
}
