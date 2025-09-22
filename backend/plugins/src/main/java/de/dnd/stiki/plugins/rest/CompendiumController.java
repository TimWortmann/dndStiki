package de.dnd.stiki.plugins.rest;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/compendium")
public class CompendiumController {

    @PostMapping(consumes = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<Void> uploadCompendium(@RequestBody String xmlContent) {

        //System.out.println("XML content: " + xmlContent);

        return ResponseEntity.ok().build();
    }
}
