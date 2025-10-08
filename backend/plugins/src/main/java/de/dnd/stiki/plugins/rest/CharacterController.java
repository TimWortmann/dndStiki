package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.character.CharacterDto;
import de.dnd.stiki.application.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/character")
public class CharacterController {

    @Autowired
    CharacterService service;

    @GetMapping()
    public ResponseEntity<List<CharacterDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @PostMapping()
    public ResponseEntity<CharacterDto> create(@RequestParam String name) {
        return ResponseEntity.ok().body(service.create(name));
    }

    @PutMapping()
    public ResponseEntity<CharacterDto> save(@RequestBody CharacterDto characterDto) {
        return ResponseEntity.ok().body(service.save(characterDto));
    }

    @DeleteMapping
    public ResponseEntity<Void> create(@RequestParam Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
