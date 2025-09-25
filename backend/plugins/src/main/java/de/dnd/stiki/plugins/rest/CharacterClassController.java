package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.characterClass.CharacterClassDto;
import de.dnd.stiki.application.CharacterClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/class")
public class CharacterClassController {

    @Autowired
    CharacterClassService service;

    @GetMapping()
    public ResponseEntity<List<CharacterClassDto>> getAllCharacterClasses() {

        List<CharacterClassDto> list = service.getAllCharacterClasses();

        return ResponseEntity.ok().body(service.getAllCharacterClasses());
    }
}
