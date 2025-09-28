package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.spell.SpellDto;
import de.dnd.stiki.application.SpellService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/spell")
public class SpellController extends AbstractController<SpellDto, SpellService> {
}
