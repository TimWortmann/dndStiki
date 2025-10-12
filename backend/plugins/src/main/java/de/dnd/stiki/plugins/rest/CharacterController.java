package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.character.CharacterDto;
import de.dnd.stiki.adapters.character.characterCreation.CharacterCreationDto;
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

    @PostMapping()
    public ResponseEntity<CharacterDto> create(@RequestBody CharacterCreationDto creationDto) {
        return ResponseEntity.ok().body(service.create(creationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/level/{id}/{level}")
    public ResponseEntity<CharacterDto> changeLevel(@PathVariable Long id, @PathVariable Integer level) {
        return ResponseEntity.ok().body(service.changeLevel(id, level));
    }

    @PutMapping("/subclass/{id}/{subclass}")
    public ResponseEntity<CharacterDto> changeSubclass(@PathVariable Long id, @PathVariable String subclass) {
        return ResponseEntity.ok().body(service.changeSubclass(id, subclass));
    }

    @PutMapping("/currentHealth/{id}/{currentHealth}")
    public ResponseEntity<CharacterDto> changeCurrentHealth(@PathVariable Long id, @PathVariable Integer currentHealth) {
        return ResponseEntity.ok().body(service.changeCurrentHealth(id, currentHealth));
    }

    @PutMapping("/maxHealth/{id}/{maxHealth}")
    public ResponseEntity<CharacterDto> changeMaxHealth(@PathVariable Long id, @PathVariable Integer maxHealth) {
        return ResponseEntity.ok().body(service.changeMaxHealth(id, maxHealth));
    }

}
