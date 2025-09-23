package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.trait.TraitDto;
import de.dnd.stiki.application.TraitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
