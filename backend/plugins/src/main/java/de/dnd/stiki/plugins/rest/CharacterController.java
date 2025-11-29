package de.dnd.stiki.plugins.rest;

import de.dnd.stiki.adapters.character.CharacterDto;
import de.dnd.stiki.adapters.character.characterAbility.CharacterAbilityDto;
import de.dnd.stiki.adapters.character.characterAttack.CharacterAttackDto;
import de.dnd.stiki.adapters.character.characterCreation.CharacterCreationDto;
import de.dnd.stiki.adapters.character.characterItem.CharacterItemDto;
import de.dnd.stiki.adapters.character.characterSkill.CharacterSkillDto;
import de.dnd.stiki.adapters.feat.FeatDto;
import de.dnd.stiki.adapters.item.ItemDto;
import de.dnd.stiki.adapters.spell.SpellDto;
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

    @PutMapping("/item/{characterId}/{itemName}/{quantity}")
    public ResponseEntity<CharacterDto> changeItemQuantity(@PathVariable Long characterId, @PathVariable String itemName, @PathVariable Integer quantity) {
        return ResponseEntity.ok().body(service.changeItemQuantity(characterId, itemName, quantity));
    }

    @PutMapping("/shield/{id}")
    public ResponseEntity<CharacterDto> equipShield(@PathVariable Long id, @RequestBody CharacterItemDto shieldItem) {
        return ResponseEntity.ok().body(service.equipShield(id, shieldItem));
    }

    @DeleteMapping("/shield/{id}")
    public ResponseEntity<CharacterDto> unequipShield(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.equipShield(id, null));
    }

    @PutMapping("/armor/{id}")
    public ResponseEntity<CharacterDto> equipArmor(@PathVariable Long id, @RequestBody CharacterItemDto armorItem) {
        return ResponseEntity.ok().body(service.equipArmor(id, armorItem));
    }

    @DeleteMapping("/armor/{id}")
    public ResponseEntity<CharacterDto> unequipArmor(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.equipArmor(id, null));
    }

    @PutMapping("/armorClass/{id}/{armorClass}")
    public ResponseEntity<CharacterDto> changeArmorClass(@PathVariable Long id, @PathVariable Integer armorClass) {
        return ResponseEntity.ok().body(service.changeArmorClass(id, armorClass));
    }

    @PutMapping("/armorClass/{id}")
    public ResponseEntity<CharacterDto> resetArmorClass(@PathVariable Long id) {
        return ResponseEntity.ok().body(service.changeArmorClass(id, null));
    }

    @PutMapping("/weapon/{id}")
    public ResponseEntity<CharacterDto> equipWeapon(@PathVariable Long id, @RequestBody CharacterItemDto weaponItem) {
        return ResponseEntity.ok().body(service.equipWeapon(id, weaponItem));
    }

    @DeleteMapping("/weapon/{characterId}/{weaponName}")
    public ResponseEntity<CharacterDto> unequipWeapon(@PathVariable Long characterId, @PathVariable String weaponName) {
        return ResponseEntity.ok().body(service.unequipWeapon(characterId, weaponName));
    }

    @DeleteMapping("/attack/{characterId}/{attackName}")
    public ResponseEntity<CharacterDto> removeAttack(@PathVariable Long characterId, @PathVariable String attackName) {
        return ResponseEntity.ok().body(service.removeAttack(characterId, attackName));
    }

    @PutMapping("/attack/{id}")
    public ResponseEntity<CharacterDto> modifyAttack(@PathVariable Long id, @RequestBody CharacterAttackDto modifiedAttack) {
        return ResponseEntity.ok().body(service.modifyAttack(id, modifiedAttack));
    }

    @PutMapping("/attack/{characterId}/{attackName}/{newProficiency}")
    public ResponseEntity<CharacterDto> changeAttackProficiency(@PathVariable Long characterId, @PathVariable String attackName, @PathVariable boolean newProficiency) {
        return ResponseEntity.ok().body(service.changeAttackProficiency(characterId,attackName, newProficiency));
    }

    @PutMapping("/spell/{id}")
    public ResponseEntity<CharacterDto> addSpell(@PathVariable Long id, @RequestBody SpellDto spell) {
        return ResponseEntity.ok().body(service.addSpell(id, spell));
    }
}
