package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.background.BackgroundDto;
import de.dnd.stiki.application.BackgroundService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/background")
public class BackgroundController extends AbstractController<BackgroundDto, BackgroundService> {

    @GetMapping("/{name}")
    public ResponseEntity<BackgroundDto> getByName(@PathVariable String name) {
        return ResponseEntity.ok().body(service.getByName(name));
    }
}
