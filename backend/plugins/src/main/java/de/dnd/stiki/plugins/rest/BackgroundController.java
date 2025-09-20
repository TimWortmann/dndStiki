package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.background.BackgroundDto;
import de.dnd.stiki.application.BackgroundService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BackgroundController {

    @Autowired
    BackgroundService service;

    @GetMapping("/background")
    public ResponseEntity<List<BackgroundDto>> getAllBackgrounds() {

        return ResponseEntity.ok().body(service.getAllBackgrounds());
    }
}
