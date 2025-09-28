package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.background.BackgroundDto;
import de.dnd.stiki.application.BackgroundService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/background")
public class BackgroundController extends AbstractController<BackgroundDto, BackgroundService> {
}
