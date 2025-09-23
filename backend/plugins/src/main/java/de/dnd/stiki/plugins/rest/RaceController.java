package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.race.RaceDto;
import de.dnd.stiki.application.RaceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/race")
public class RaceController {

    @Autowired
    RaceService service;

    @GetMapping()
    public ResponseEntity<List<RaceDto>> getAllRaces() {

        return ResponseEntity.ok().body(service.getAllRaces());
    }
}
