package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.dndClass.DndClassDto;
import de.dnd.stiki.application.DndClassService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/class")
public class DndClassController extends AbstractController<DndClassDto, DndClassService> {
}
