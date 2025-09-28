package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.characterClass.CharacterClassDto;
import de.dnd.stiki.application.CharacterClassService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/class")
public class CharacterClassController extends AbstractController<CharacterClassDto, CharacterClassService> {
}
