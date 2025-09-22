package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.application.CompendiumService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/compendium")
public class CompendiumController {

    @Autowired
    CompendiumService service;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadCompendium(@RequestParam("file") MultipartFile file) throws IOException {

        return ResponseEntity.ok().body(service.uploadCompendium(file));
    }
}
