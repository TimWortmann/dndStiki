package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.spell.SpellDto;
import de.dnd.stiki.application.SpellService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/spell")
public class SpellController {

    @Autowired
    SpellService service;

    @GetMapping()
    public ResponseEntity<List<SpellDto>> getAllSpells() {

        return ResponseEntity.ok().body(service.getAll());
    }
}
