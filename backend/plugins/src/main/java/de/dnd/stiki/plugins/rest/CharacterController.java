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

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PostMapping("/{name}")
    public ResponseEntity<CharacterDto> create(@PathVariable String name) {
        return ResponseEntity.ok().body(service.create(name));
    }

    @PutMapping()
    public ResponseEntity<CharacterDto> save(@RequestBody CharacterDto characterDto) {
        return ResponseEntity.ok().body(service.save(characterDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> create(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }
}
