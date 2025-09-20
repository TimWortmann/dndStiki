package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.trait.TraitDto;
import de.dnd.stiki.adapters.trait.TraitLightDto;
import de.dnd.stiki.application.TraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/trait")
public class TraitController {

    @Autowired
    TraitService service;

    @GetMapping()
    public ResponseEntity<List<TraitDto>> getAllTraits() {

        return ResponseEntity.ok().body(service.getAllTraits());
    }

    @PostMapping
    public ResponseEntity<TraitDto> createTrait(@RequestBody TraitLightDto traitLightDto) {
        return ResponseEntity.ok().body(service.createTrait(traitLightDto));
    }

    @DeleteMapping
    public ResponseEntity deleteTrait(@RequestParam Long id) {
        service.deleteTrait(id);
        return ResponseEntity.ok().build();
    }
}
