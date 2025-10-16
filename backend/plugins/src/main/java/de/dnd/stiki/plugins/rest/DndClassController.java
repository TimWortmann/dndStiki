package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.dndClass.DndClassDto;
import de.dnd.stiki.application.DndClassService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/class")
public class DndClassController extends AbstractController<DndClassDto, DndClassService> {

    @GetMapping("/{name}")
    public ResponseEntity<DndClassDto> getByName(@PathVariable String name) {
        return ResponseEntity.ok().body(service.getByName(name));
    }
}
