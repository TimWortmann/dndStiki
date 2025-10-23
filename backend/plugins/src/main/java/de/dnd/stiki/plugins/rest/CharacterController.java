package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.character.CharacterDto;
import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityDto;
import de.dnd.stiki.adapters.character.characterCreation.CharacterCreationDto;
import de.dnd.stiki.adapters.character.characterSkill.CharacterSkillDto;
import de.dnd.stiki.adapters.feat.FeatDto;
import de.dnd.stiki.adapters.item.ItemDto;
import de.dnd.stiki.application.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/character")
public class CharacterController {

    @Autowired
    CharacterService service;

    @GetMapping()
    public ResponseEntity<List<CharacterDto>> getAll() {
        return ResponseEntity.ok().body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CharacterDto> get(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.get(id));
    }

    @PostMapping()
    public ResponseEntity<CharacterDto> create(@RequestBody CharacterCreationDto creationDto) {
        return ResponseEntity.ok().body(service.create(creationDto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/name/{id}/{name}")
    public ResponseEntity<CharacterDto> changeName(@PathVariable Long id, @PathVariable String name) {
        return ResponseEntity.ok().body(service.changeName(id, name));
    }

    @PutMapping("/level/{id}/{level}")
    public ResponseEntity<CharacterDto> changeLevel(@PathVariable Long id, @PathVariable Integer level) {
        return ResponseEntity.ok().body(service.changeLevel(id, level));
    }

    @PutMapping("/class/{id}")
    public ResponseEntity<CharacterDto> changeDndClass(@PathVariable Long id, @RequestBody String dndClass) {
        return ResponseEntity.ok().body(service.changeDndClass(id, dndClass));
    }

    @PutMapping("/subclass/{id}")
    public ResponseEntity<CharacterDto> changeSubclass(@PathVariable Long id, @RequestBody String subclass) {
        return ResponseEntity.ok().body(service.changeSubclass(id, subclass));
    }

    @PutMapping("/background/{id}")
    public ResponseEntity<CharacterDto> changeBackground(@PathVariable Long id, @RequestBody String background) {
        return ResponseEntity.ok().body(service.changeBackground(id, background));
    }

    @PutMapping("/race/{id}")
    public ResponseEntity<CharacterDto> changeRace(@PathVariable Long id, @RequestBody String race) {
        return ResponseEntity.ok().body(service.changeRace(id, race));
    }

    @PutMapping("/currentHealth/{id}/{currentHealth}")
    public ResponseEntity<CharacterDto> changeCurrentHealth(@PathVariable Long id, @PathVariable Integer currentHealth) {
        return ResponseEntity.ok().body(service.changeCurrentHealth(id, currentHealth));
    }

    @PutMapping("/maxHealth/{id}/{maxHealth}")
    public ResponseEntity<CharacterDto> changeMaxHealth(@PathVariable Long id, @PathVariable Integer maxHealth) {
        return ResponseEntity.ok().body(service.changeMaxHealth(id, maxHealth));
    }

    @PutMapping("/currentHitDice/{id}/{currentHitDice}")
    public ResponseEntity<CharacterDto> changeCurrentHitDice(@PathVariable Long id, @PathVariable Integer currentHitDice) {
        return ResponseEntity.ok().body(service.changeCurrentHitDice(id, currentHitDice));
    }

    @PutMapping("/maxHitDice/{id}/{maxHitDice}")
    public ResponseEntity<CharacterDto> changeMaxHitDice(@PathVariable Long id, @PathVariable Integer maxHitDice) {
        return ResponseEntity.ok().body(service.changeMaxHitDice(id, maxHitDice));
    }

    @PutMapping("/ability/{id}")
    public ResponseEntity<CharacterDto> changeAbilities(@PathVariable Long id, @RequestBody List<CharacterAbilityDto> abilities) {
        return ResponseEntity.ok().body(service.changeAbilities(id, abilities));
    }

    @PutMapping("/skill/{id}")
    public ResponseEntity<CharacterDto> changeSkillProficiencies(@PathVariable Long id, @RequestBody List<CharacterSkillDto> skills) {
        return ResponseEntity.ok().body(service.changeSkillProficiencies(id, skills));
    }

    @PutMapping("/feat/{id}")
    public ResponseEntity<CharacterDto> addFeat(@PathVariable Long id, @RequestBody FeatDto feat) {
        return ResponseEntity.ok().body(service.addFeat(id, feat));
    }

    @DeleteMapping("/feat/{id}/{featName}")
    public ResponseEntity<CharacterDto> removeFeat(@PathVariable Long id, @PathVariable String featName) {
        return ResponseEntity.ok().body(service.removeFeat(id, featName));
    }

    @PutMapping("/item/{id}")
    public ResponseEntity<CharacterDto> addItem(@PathVariable Long id, @RequestBody ItemDto item) {
        return ResponseEntity.ok().body(service.addItem(id, item));
    }
}
